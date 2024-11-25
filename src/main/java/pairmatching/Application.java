package pairmatching;

import pairmatching.controller.PairMatchingController;
import pairmatching.io.DataProvider;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    private static final String BACKEND_CREW_FILE = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_FILE = "src/main/resources/frontend-crew.md";

    public static void main(String[] args) {
        DataProvider dataProvider = new DataProvider();

        PairMatchingController controller = new PairMatchingController(new InputView(), new OutputView(),
                dataProvider.provideCrewData(BACKEND_CREW_FILE), dataProvider.provideCrewData(FRONTEND_CREW_FILE));

        controller.run();
    }
}
