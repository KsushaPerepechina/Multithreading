package parser;

import by.epam.multithreading.entity.Truck;
import by.epam.multithreading.entity.TruckCondition;
import by.epam.multithreading.exception.TruckException;
import by.epam.multithreading.parser.ParametersStringToTruckParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ParametersStringToTruckParserTest {
    private ParametersStringToTruckParser parametersStringToTruckParser;
    private static final String TRUCK_DATA = "empty, short-lived = false";
    private Truck expected;

    @BeforeClass
    public void setUp(){
        parametersStringToTruckParser = new ParametersStringToTruckParser();
        expected = new Truck(TruckCondition.EMPTY, false);
    }

    @Test
    public void parseTest() throws TruckException {
        Truck actual = parametersStringToTruckParser.parse(TRUCK_DATA);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = TruckException.class)
    public void parseUnmatchedPositionTest() throws TruckException {
        parametersStringToTruckParser.parse(TRUCK_DATA + ", short-lived = true");
    }
}