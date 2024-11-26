package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.handler.ExceptionMessages;

import java.util.ArrayList;
import java.util.List;


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
                List<String> list = new ArrayList<>();
                list.add(shuffledCrew.get(i));
                list.add(shuffledCrew.get(i+1));
                matchingResult.add(list);
            }
        }

        if (countCrew % 2 != 0){
            for (int i = 0; i < shuffledCrew.size()-1; i+=2){
                List<String> list = new ArrayList<>();
                list.add(shuffledCrew.get(i));
                list.add(shuffledCrew.get(i+1));
                matchingResult.add(list);
            }
            matchingResult.getLast().add(shuffledCrew.get(countCrew - 1));
        }
        return matchingResult;
    }
}
