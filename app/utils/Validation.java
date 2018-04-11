package utils;

/**
 * Simple utility class that holds a validation
 */
public class Validation {

    private boolean validationPredicate;
    private String errorMessage;


    public Validation(boolean validationPredicate, String errorMessage) {
        this.validationPredicate = validationPredicate;
        this.errorMessage = errorMessage;
    }


    public static Validation with(boolean predicate, String errorMessage) {
        return new Validation(predicate, errorMessage);
    }


    public boolean fails() {
        return !validationPredicate;
    }


    public String getErrorMessage() {
        return errorMessage;
    }
}


