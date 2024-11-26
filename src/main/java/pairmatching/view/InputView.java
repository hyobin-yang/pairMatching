package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.handler.ExceptionMessages;

public class InputView {

    public String chooseMenuOption(){
        System.out.println("기능을 선택하세요.\n" +
                "1. 페어 매칭\n" +
                "2. 페어 조회\n" +
                "3. 페어 초기화\n" +
                "Q. 종료");
        return Console.readLine().trim();
    }

    public String chooseOptionToMatch(){
        System.out.println("과정, 레벨, 미션을 선택하세요.\n" +
                "ex) 백엔드, 레벨1, 자동차경주");
        return Console.readLine();
    }

    public boolean askRetryMatchingOrNot(){
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n" + "네 | 아니오");

        String answer = Console.readLine().trim();
        if (answer.equals("네")){
            return true;
        }
        if (answer.equals("아니오")){
            return false;
        }
        throw new IllegalArgumentException(ExceptionMessages.INVALID_ANSWER_INPUT.getMessage());
    }

}
