package pairmatching.model;

import java.util.List;

public class Pairs {
    private final List<List<String>> pairs;

    public Pairs(List<List<String>> pairs){
        this.pairs = pairs;
    }

    public List<List<String>> getPairs(){
        return pairs;
    }

}
