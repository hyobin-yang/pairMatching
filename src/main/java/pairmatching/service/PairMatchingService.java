package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.handler.ExceptionMessages;

import java.util.ArrayList;
import java.util.List;

// TODO: 페어로 만난 적 있는 크루 제외

public class PairMatchingService {
    private int tryCount = 0;

    public List<List<String>> getMatchingResult(List<String> crews){
        tryCount ++;
        if (tryCount > 3){
            throw new IllegalArgumentException(ExceptionMessages.MATCHING_FAILED.getMessage());
        }

        List<String> shuffledCrew = Randoms.shuffle(crews);
        List<List<String>> matchingResult = new ArrayList<>();
        int countCrew = shuffledCrew.size();

        if (countCrew % 2 == 0){
            for (int i = 0; i < shuffledCrew.size(); i+=2){
                matchingResult.add(List.of(shuffledCrew.get(i), shuffledCrew.get(i+1)));
            }
        }

        if (countCrew % 2 != 0){
            for (int i = 0; i < shuffledCrew.size()-1; i+=2){
                matchingResult.add(List.of(shuffledCrew.get(i), shuffledCrew.get(i+1)));
            }
            matchingResult.getLast().add(shuffledCrew.getLast());
        }
        return matchingResult;
    }
}
