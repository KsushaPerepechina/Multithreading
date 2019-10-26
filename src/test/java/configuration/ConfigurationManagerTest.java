package configuration;

import by.epam.multithreading.configurator.ConfigurationManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ConfigurationManagerTest {
    private static final String BASE_NAME = "test_configuration";

    @DataProvider(name = "propertyData")
    public Object[][] createHeightData() {
        return new Object[][]{{"name", "John"}, {"surname", "Lennon"}, {"country", "UK"}};
    }

    @Test(dataProvider = "propertyData")
    public void getPropertyPositiveTest(String key, String expected) {
        String actual = ConfigurationManager.getProperty(BASE_NAME, key);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "propertyData")
    public void getPropertyNegativeTest(String key, String expected) {
        String actual = ConfigurationManager.getProperty(BASE_NAME, key + ".");
        Assert.assertNull(actual);
    }
}
