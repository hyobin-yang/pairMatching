package pairmatching.dto;

import java.util.ArrayList;
import java.util.List;

public class LevelPairsDTO {
    private final List<List<String>> levelPairs;

    public LevelPairsDTO(){
        this.levelPairs = new ArrayList<>();
    }

    public void addPairs(List<String> levelPair){
        levelPairs.add(levelPair);
    }

    public List<List<String>> getLevelPairs(){
        return levelPairs;
    }
}
