package pairmatching.handler;

import java.util.function.Supplier;

public class RetryHandler {

    public static <T> T retryOnInvalidInput(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                Errorhandler.handleError(e);
            }
        }
    }
}
