package pairmatching.model;

public enum Option {
    FIRST("1", "페어 매칭"),
    SECOND("2", "페어 조회"),
    THIRD("3", "페어 초기화"),
    QUIT("Q", "종료");

    private final String optionNumber;
    private final String optionContent;

    Option(String optionNumber, String optionContent) {
        this.optionNumber = optionNumber;
        this.optionContent = optionContent;
    }
}
