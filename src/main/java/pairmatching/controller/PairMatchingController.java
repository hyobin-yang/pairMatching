package pairmatching.controller;

import pairmatching.handler.ExceptionMessages;
import pairmatching.handler.RetryHandler;
import pairmatching.model.ChosenOption;
import pairmatching.model.LevelAndMissions;
import pairmatching.model.Option;
import pairmatching.model.Part;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.*;

public class PairMatchingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final List<String> backendCrews;
    private final List<String> frontendCrews;
    private Map<ChosenOption, List<List<String>>> paringStorage = new HashMap<>();

    public PairMatchingController(InputView inputView, OutputView outputView,
                                  List<String> backendCrews, List<String> frontendCrews){
        this.inputView = inputView;
        this.outputView = outputView;
        this.backendCrews = backendCrews;
        this.frontendCrews = frontendCrews;
    }


    public void run(){
        while(true){
            String optionNumber = RetryHandler.retryOnInvalidInput(inputView::chooseNumber);
            if (optionNumber.equals(Option.QUIT.getOptionNumber())){
                break;
            }
            forwardOption(optionNumber);
        }
    }

    private void forwardOption(String optionNumber){
        if (optionNumber.equals(Option.FIRST.getOptionNumber())){
            startPairMatching();
        }
        if (optionNumber.equals(Option.SECOND.getOptionNumber())){
            showParingInformation();
        }
        if (optionNumber.equals(Option.THIRD.getOptionNumber())){
            initializeParing();
        }
    }

    private void startPairMatching(){
        ChosenOption chosenOption = RetryHandler.retryOnInvalidInput(this::getChosenOption);
        if (checkParingAlreadyExist(chosenOption)){
            matchExistPairMatching(chosenOption);
        }
        if (!checkParingAlreadyExist(chosenOption)){
            matchNewPairMatching(chosenOption);
        }
    }

    private ChosenOption getChosenOption(){
        outputView.showParingOptions();
        return parseOptionInput(inputView.chooseOptionToMatch());
    }

    private ChosenOption parseOptionInput(String input){
        List<String> optionInput = Arrays.stream(input.split(",")).toList();
        return validateOptionInput(optionInput);
    }

    private ChosenOption validateOptionInput(List<String> optionInput){
        Part.validateProperPart(optionInput.get(0).trim());
        if (!LevelAndMissions.validateProperLevelAndMission(
                optionInput.get(1).trim(),
                optionInput.get(2).trim())){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_OPTION_INPUT.getMessage());
        }
        return new ChosenOption(optionInput.get(0).trim(), optionInput.get(1).trim(), optionInput.get(2).trim());
    }

    private boolean checkParingAlreadyExist(ChosenOption chosenOption){
        for (ChosenOption option : paringStorage.keySet()){
            if (option.hasParingInformation(chosenOption)){
                return true;
            }
        }
        return false;
    }

    private void matchNewPairMatching(ChosenOption chosenOption){
        PairMatchingService pairMatchingService = new PairMatchingService();
        List<String> crews = getCrewsOfPart(chosenOption.getPart());
        while(true){
            try{
                List<List<String>> matchingResult = pairMatchingService.getMatchingResult(crews);
                paringStorage.put(chosenOption, matchingResult);
                return;
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e);
            }
        }
    }

    private void matchExistPairMatching(ChosenOption chosenOption){
        boolean willRetry = RetryHandler.retryOnInvalidInput(inputView::askRetryMatchingOrNot);
        if (willRetry){
            retryPairMatching(chosenOption);
        }
        if (!willRetry){
            startPairMatching();
        }
    }

    private void retryPairMatching(ChosenOption chosenOption){
        PairMatchingService pairMatchingService = new PairMatchingService();
        for (ChosenOption option : paringStorage.keySet()){
            if (option.hasParingInformation(chosenOption)){
                paringStorage.remove(option);
            }
        }

        List<String> crews = getCrewsOfPart(chosenOption.getPart());
        while(true){
            try{
                paringStorage.put(chosenOption, pairMatchingService.getMatchingResult(crews));
                return;
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e);
            }
        }
    }

    private List<String> getCrewsOfPart(String part){
        if (part.equals(Part.BACKEND.getPart())){
            return backendCrews;
        }
        return frontendCrews;
    }

    private void showParingInformation(){
        while(true){
            ChosenOption chosenOption = RetryHandler.retryOnInvalidInput(this::getChosenOption);
            if (checkParingAlreadyExist(chosenOption)){
                outputView.showResultOfPairMatching(getParingInformationOfOption(chosenOption));
                break;
            }
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXIST_MATCHING_INFORMATION.getMessage());
        }
    }

    private void initializeParing(){
        paringStorage.clear();
        outputView.initializeParingInformation();
    }

    private List<List<String>> getParingInformationOfOption(ChosenOption chosenOption){
        for (ChosenOption option : paringStorage.keySet()){
            if (option.hasParingInformation(chosenOption)){
                return paringStorage.get(option);
            }
        }
        return null;
    }

}
