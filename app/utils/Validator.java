package utils;

import com.google.common.base.Joiner;
import exceptions.ValidationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple utility class to make checks in the business logic
 */
public class Validator {

    private List<Validation> validations;
    private List<String> accummulatedErrors;


    private Validator(List<Validation> validations) {
        this.validations = validations;
        this.accummulatedErrors = new ArrayList<>();
    }


    public void add(Validation validation) {
        this.validations.add(validation);
    }


    public void add(Validation... validations) {
        this.validations.addAll(Arrays.asList(validations));
    }


    public static Validator apply(Validation... validations) {
        return new Validator(new ArrayList<>(Arrays.asList(validations)));
    }


    public static Validator apply(List<Validation> validations) {
        return new Validator(validations);
    }


    public void validate() {
        accummulatedErrors = validations.stream()
                .filter(Validation::fails)
                .map(Validation::getErrorMessage)
                .collect(Collectors.toList());

        if (!accummulatedErrors.isEmpty()) {
            throw new ValidationException(Joiner.on("\n").join(accummulatedErrors));
        }
    }

}
