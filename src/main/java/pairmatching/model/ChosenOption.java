package pairmatching.model;

public class ChosenOption {
    private final String part;
    private final String level;
    private final String mission;

    public ChosenOption(String part, String level, String mission){
        this.part = part;
        this.level = level;
        this.mission = mission;
    }

    public String getPart(){
        return part;
    }

    public String getLevel(){
        return level;
    }

    public String getMission(){
        return mission;
    }

    public boolean hasParingInformation(ChosenOption chosenOption){
        return (chosenOption.getPart().equals(part) &&
                chosenOption.getLevel().equals(level) &&
                chosenOption.getMission().equals(mission));
    }

}
