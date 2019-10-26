package by.epam.multithreading.validation;

public class TxtFilePathValidator {
    private static final String VALID_FILE_PATH_PATTERN = "[^\\:*?\"<>@!%|+]*[^\\:*?\"<>@!%|+. ].txt";

    public boolean isCorrect(String inputString) {
        return inputString.matches(VALID_FILE_PATH_PATTERN);
    }
}
