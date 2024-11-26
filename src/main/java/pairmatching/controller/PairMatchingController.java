package pairmatching.controller;
import pairmatching.dto.PairMatchingOptionDTO;
import pairmatching.handler.Errorhandler;
import pairmatching.handler.ExceptionMessages;
import pairmatching.handler.RetryHandler;
import pairmatching.model.*;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.*;

public class PairMatchingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<String, List<String>> crewsOfEachPart;
    private Map<PairMatchingOption, Pairs> pairMatchingResults = new HashMap<>();

    public PairMatchingController(InputView inputView, OutputView outputView, Map<String, List<String>> crewsOfEachPart){
        this.inputView = inputView;
        this.outputView = outputView;
        this.crewsOfEachPart = crewsOfEachPart;
    }

    public void run(){
        while(true){
            String menu = RetryHandler.retryOnInvalidInput(this::chooseMenu);
            if (menu.equals(MenuOption.QUIT.getOptionNumber())){
                break;
            }
            findAndExecuteMenu(menu);
        }
    }

    private String chooseMenu(){
        String menu = inputView.chooseMenuOption();
        if (!MenuOption.isValidMenuOption(menu)){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MENU_NUMBER_INPUT.getMessage());
        }
        return menu;
    }

    private void findAndExecuteMenu(String menu){
        if (menu.equals(MenuOption.FIRST.getOptionNumber())){
            matchPair();
        }
        if (menu.equals(MenuOption.SECOND.getOptionNumber())){
            showPairMatchingResult();
        }
        if (menu.equals(MenuOption.THIRD.getOptionNumber())){
            initializePairMatching();
        }
    }

    private void matchPair(){
        while(true){
            try{
                PairMatchingOptionDTO pairMatchingOptionDTO = RetryHandler.retryOnInvalidInput(this::getChosenOption);
                PairMatchingOption option = RetryHandler.retryOnInvalidInput(() -> getOptionIfMatchingExist(
                        pairMatchingOptionDTO.getPart(),
                        pairMatchingOptionDTO.getLevelAndMissions().getLevel(),
                        pairMatchingOptionDTO.getMission()));
                if (option == null){
                    option = new PairMatchingOption(pairMatchingOptionDTO.getPart(), pairMatchingOptionDTO.getLevelAndMissions(), pairMatchingOptionDTO.getMission());
                    matchPairs(option);
                    break;
                }
                boolean willRetryMatching = RetryHandler.retryOnInvalidInput(inputView::askRetryMatchingOrNot);
                if (willRetryMatching){
                    matchPairs(option);
                    break;
                }
            } catch (IllegalArgumentException e){
                Errorhandler.handleError(e);
            }
        }
    }

    private List<Pairs> getPairsInSameLevel(String level){
        List<Pairs> pairsInSameLevel = new ArrayList<>();
        for (PairMatchingOption option : pairMatchingResults.keySet()){
            if (option.getLevelAndMissions().getLevel().equals(level)){
                pairsInSameLevel.add(pairMatchingResults.get(option));
            }
        }
        return pairsInSameLevel;
    }

    private void matchPairs(PairMatchingOption option){
        PairMatchingService service = new PairMatchingService();
        String level = option.getLevelAndMissions().getLevel();
        List<Pairs> pairsInSameLevel = getPairsInSameLevel(level);
        List<List<String>> matchingResult = service.match(crewsOfEachPart.get(option.getPart()), pairsInSameLevel);
        outputView.showResultOfPairMatching(matchingResult);
        pairMatchingResults.put(option, new Pairs(matchingResult));
    }

    private PairMatchingOptionDTO getChosenOption(){
        String chosenOption = inputView.chooseOptionToMatch();
        PairMatchingService service = new PairMatchingService();
        return service.getPairMatchingOptionDTO(chosenOption);
    }

    private PairMatchingOption getOptionIfMatchingExist(Part part, String level, String mission){
        for (PairMatchingOption option : pairMatchingResults.keySet()){
            if (option.getPart().equals(part.getPart())
                    && option.getLevelAndMissions().getLevel().equals(level)
                    && option.getMission().equals(mission)){
            }
            return option;
        }
        return null;
    }

    private void showPairMatchingResult(){
        while(true){
            try{
                PairMatchingOptionDTO pairMatchingOptionDTO = getChosenOption();
                PairMatchingOption option = RetryHandler.retryOnInvalidInput(() -> getOptionIfMatchingExist(
                        pairMatchingOptionDTO.getPart(),
                        pairMatchingOptionDTO.getLevelAndMissions().getLevel(),
                        pairMatchingOptionDTO.getMission()));
                if (option == null){
                    throw new IllegalArgumentException(ExceptionMessages.NOT_EXIST_MATCHING_INFORMATION.getMessage());
                }
                outputView.showResultOfPairMatching(pairMatchingResults.get(option).getPairs());
                break;
            } catch (IllegalArgumentException e){
                Errorhandler.handleError(e);
            }
        }
    }

    private void initializePairMatching(){
        pairMatchingResults.clear();
        outputView.initializeParingInformation();
    }


}
