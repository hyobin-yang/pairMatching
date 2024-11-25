package pairmatching.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum LevelAndMissions {
    LEVEL1("레벨1", List.of("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", List.of("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", null),
    LEVEL4("레벨4", List.of("성능개선", "배포")),
    LEVEL5("레벨5", null);

    private final String level;
    private final List<String> missions;
    private static final Map<String, LevelAndMissions> missionStorage = new HashMap<>();

    static {
        for (LevelAndMissions levelAndMission : values()){
            missionStorage.put(levelAndMission.level, levelAndMission);
        }
    }

    LevelAndMissions(String level, List<String> missions) {
        this.level = level;
        this.missions = missions;
    }

    public String getLevel(){
        return level;
    }

    public List<String> getMissions(){
        return missions;
    }

    //TODO: 체크
    public boolean isProperLevelAndMission(String level, String mission){
        LevelAndMissions levelAndMission = missionStorage.get(level);
        return levelAndMission.getMissions().contains(mission);
    }

}
