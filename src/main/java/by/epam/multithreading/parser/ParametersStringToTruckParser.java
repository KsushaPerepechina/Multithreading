package by.epam.multithreading.parser;

import by.epam.multithreading.entity.Truck;
import by.epam.multithreading.entity.TruckCondition;
import by.epam.multithreading.exception.TruckException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametersStringToTruckParser {
    private static final String TRUCK_PARAMETERS_REGEXP = "(full|empty)\\u0020*,\\u0020*short-lived\\u0020*=\\u0020*(true|false)";

    public Truck parse(String stringOfParameters) throws TruckException {
        Pattern pattern = Pattern.compile(TRUCK_PARAMETERS_REGEXP);
        Matcher matcher = pattern.matcher(stringOfParameters);
        if (!matcher.matches()) {
            throw new TruckException("Unable to create truck: string \"" + stringOfParameters +
                    "\" doesn't match the required pattern.");
        }
        TruckCondition state = TruckCondition.valueOf(matcher.group(1).toUpperCase());
        boolean containsShortLivedProducts = Boolean.parseBoolean(matcher.group(2));
        Truck truck = new Truck(state, containsShortLivedProducts);
        if (containsShortLivedProducts) {
            truck.setPriority(Thread.MAX_PRIORITY);
        } else {
            truck.setPriority(Thread.MIN_PRIORITY);
        }
        return truck;
    }
}
