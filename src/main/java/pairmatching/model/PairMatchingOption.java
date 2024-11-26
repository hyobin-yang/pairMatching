package pairmatching.model;

public class PairMatchingOption {
    private final Part part;
    private final LevelAndMissions levelAndMissions;
    private final String mission;

    public PairMatchingOption(Part part, LevelAndMissions levelAndMissions, String mission){
        this.part = part;
        this.levelAndMissions = levelAndMissions;
        this.mission = mission;
    }

    public String getPart(){
        return part.getPart();
    }

    public LevelAndMissions getLevelAndMissions(){
        return levelAndMissions;
    }

    public String getMission(){
        return mission;
    }

}
