package pairmatching.dto;

import pairmatching.model.LevelAndMissions;
import pairmatching.model.Part;

public class PairMatchingOptionDTO {
    private final Part part;
    private final LevelAndMissions levelAndMissions;
    private final String mission;

    public PairMatchingOptionDTO(String part, String level, String mission){
        Part.validateProperPart(part);
        this.part = Part.getPart(part);
        LevelAndMissions.validateProperLevelAndMission(level, mission);
        this.levelAndMissions = LevelAndMissions.getLevelAndMissions(level);
        this.mission = mission;
    }

    public Part getPart(){
        return part;
    }

    public LevelAndMissions getLevelAndMissions(){
        return levelAndMissions;
    }

    public String getMission(){
        return mission;
    }

}
