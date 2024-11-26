package pairmatching;

import pairmatching.controller.PairMatchingController;
import pairmatching.io.DataProvider;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private static final String BACKEND_CREW_FILE = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_FILE = "src/main/resources/frontend-crew.md";

    public static void main(String[] args) {
        DataProvider dataProvider = new DataProvider();
        Map<String, List<String>> crewsOfEachPart = new HashMap<>();
        crewsOfEachPart.put("백엔드", dataProvider.provideCrewData(BACKEND_CREW_FILE));
        crewsOfEachPart.put("프론트엔드", dataProvider.provideCrewData(FRONTEND_CREW_FILE));


        PairMatchingController controller = new PairMatchingController(new InputView(), new OutputView(),
                crewsOfEachPart);

        controller.run();
    }
}
