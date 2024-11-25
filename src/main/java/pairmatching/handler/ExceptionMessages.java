package pairmatching.handler;

public enum ExceptionMessages {
    NOT_EXIST_MATCHING_INFORMATION("매칭 이력이 없습니다."),
    MATCHING_FAILED("매칭이 실패하였습니다.");

    private final String message;

    ExceptionMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return String.format("[ERROR] %s", message);
    }
}
