package pairmatching.model;

public enum MenuOption {
    FIRST("1", "페어 매칭"),
    SECOND("2", "페어 조회"),
    THIRD("3", "페어 초기화"),
    QUIT("Q", "종료");

    private final String optionNumber;
    private final String optionContent;

    MenuOption(String optionNumber, String optionContent) {
        this.optionNumber = optionNumber;
        this.optionContent = optionContent;
    }

    public static boolean isValidMenuOption(String optionNumber){
        for (MenuOption menuOption : values()){
            if (optionNumber.equals(menuOption.optionNumber)){
                return true;
            }
        }
        return false;
    }

    public String getOptionNumber() {
        return optionNumber;
    }
}
