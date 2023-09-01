package Errors;

public abstract class Errors extends Throwable {
    private final String errorMessage;

    public Errors(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
