package validation;

import by.epam.multithreading.validation.TxtFilePathValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TxtFilePathValidatorTest {
    private TxtFilePathValidator txtFilePathValidator;

    @BeforeClass
    public void setUp(){
        txtFilePathValidator = new TxtFilePathValidator();
    }

    @Test
    public void validFilePathTest(){
        Assert.assertTrue(txtFilePathValidator.isCorrect("folder/file.txt"));
    }

    @Test
    public void filePathWithoutExtensionTest(){
        Assert.assertFalse(txtFilePathValidator.isCorrect("folder/file"));
    }

    @Test
    public void filePathWithOtherExtensionTest(){
        Assert.assertFalse(txtFilePathValidator.isCorrect("folder/file.doc"));
    }
}
