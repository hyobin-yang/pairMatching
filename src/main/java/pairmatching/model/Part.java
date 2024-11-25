package pairmatching.model;

import pairmatching.handler.ExceptionMessages;

public enum Part {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String part;

    Part(String part) {
        this.part = part;
    }

    public static void validateProperPart(String part){
        if (!part.equals(BACKEND.part) && !part.equals(FRONTEND.part)){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_OPTION_INPUT.getMessage());
        }
    }

    public String getPart(){
        return part;
    }
}