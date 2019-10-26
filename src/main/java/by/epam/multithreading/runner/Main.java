package by.epam.multithreading.runner;

import by.epam.multithreading.entity.Truck;
import by.epam.multithreading.exception.InaccessibleFileException;
import by.epam.multithreading.exception.TruckException;
import by.epam.multithreading.parser.ParametersStringToTruckParser;
import by.epam.multithreading.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static String DATA_FILE_PATH = ClassLoader.getSystemResource("data/input_data.txt").toString()
            .replace("file:", "");

    public static void main(String[] args) {
        createTruckList().forEach(Thread::start);
    }

    private static List<Truck> createTruckList() {
        DataReader reader = new DataReader();
        ParametersStringToTruckParser stringToTruckParser = new ParametersStringToTruckParser();
        List<String> lines = new ArrayList<>();
        try {
            lines = reader.readAllLines(DATA_FILE_PATH);
        } catch (InaccessibleFileException e) {
            LOGGER.error(e);
        }
        List<Truck> trucks = new ArrayList<>();
        for (String line : lines) {
            try {
                Truck truck = stringToTruckParser.parse(line);
                trucks.add(truck);
            } catch (TruckException e) {
                LOGGER.error(e);
            }
        }
        return trucks;
    }
}
