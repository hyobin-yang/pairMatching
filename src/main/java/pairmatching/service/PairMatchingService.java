package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.dto.PairMatchingOptionDTO;
import pairmatching.handler.ExceptionMessages;
import pairmatching.model.Pairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PairMatchingService {

    public PairMatchingOptionDTO getPairMatchingOptionDTO(String rawChosenOption){
        List<String> optionInput = Arrays.stream(rawChosenOption.split(",")).toList();
        if (optionInput.size() != 3){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_OPTION_INPUT.getMessage());
        }

        return new PairMatchingOptionDTO(
                optionInput.get(0).trim(),
                optionInput.get(1).trim(),
                optionInput.get(2).trim()
        );
    }

    public List<List<String>> match(List<String> crews, List<Pairs> pairsInSameLevel){
        int matchTryingCount = 1;
        while (matchTryingCount <= 3) {
            List<String> mixedCrews = Randoms.shuffle(crews);
            List<List<String>> matchingResult = getMatchingResult(mixedCrews, pairsInSameLevel);
            if (!matchingResult.isEmpty()) {
                return matchingResult;
            }
            matchTryingCount++;
        }
        throw new IllegalArgumentException(ExceptionMessages.MATCHING_FAILED.getMessage());
    }

    private List<List<String>> getMatchingResult(List<String> mixedCrews, List<Pairs> pairsInSameLevel){
        int crewsCount = mixedCrews.size();
        List<List<String>> matchingResult = new ArrayList<>();

        for (int i = 0; i < crewsCount; i += 2) {
            if (i + 3 == crewsCount) {
                List<String> newPair = mixedCrews.subList(i, crewsCount);
                if (isDuplicatedPair(pairsInSameLevel, newPair)){
                    return new ArrayList<>();
                }
                matchingResult.add(newPair);
            }

            List<String> newPair = mixedCrews.subList(i, Math.min(i + 2, crewsCount));
            if (isDuplicatedPair(pairsInSameLevel, newPair)){
                return new ArrayList<>();
            }
            matchingResult.add(newPair);
        }
        return matchingResult;
    }

    private boolean isDuplicatedPair(List<Pairs> pairsInSameLevel, List<String> newPair){
        for (Pairs pairs : pairsInSameLevel){
            for (List<String> pair : pairs.getPairs()){
                long matchingCount = pair.stream()
                        .filter(newPair::contains)
                        .count();
                if (matchingCount > 0){
                    return true;
                }
            }
        }
        return false;
    }

}
