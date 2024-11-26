package pairmatching.handler;

public enum ExceptionMessages {
    NOT_EXIST_MATCHING_INFORMATION("매칭 이력이 없습니다."),
    MATCHING_FAILED("매칭이 실패하였습니다."),
    INVALID_MENU_NUMBER_INPUT("잘못된 기능 번호 입력값입니다."),
    INVALID_OPTION_INPUT("잘못된 기능 종류 입력값입니다."),
    INVALID_ANSWER_INPUT("잘못된 대답 입력값입니다.");

    private final String message;

    ExceptionMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return String.format("[ERROR] %s", message);
    }
}
