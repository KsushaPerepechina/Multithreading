package by.epam.multithreading.singleton;

import by.epam.multithreading.entity.Truck;
import by.epam.multithreading.entity.TruckCondition;
import by.epam.multithreading.exception.TruckException;
import by.epam.multithreading.configurator.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    private static final Logger LOGGER = LogManager.getLogger();
    private static LogisticBase instance;
    private static final int TERMINAL_AMOUNT = Integer.parseInt(ConfigurationManager.getProperty("configuration" ,
            "number_of_terminals"));
    private final Semaphore semaphore = new Semaphore(TERMINAL_AMOUNT, false);
    private static ReentrantLock lock = new ReentrantLock();

    public static LogisticBase getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null)
                    instance = new LogisticBase();
                return instance;
            }
            finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void serveTruck(Truck truck) throws TruckException {
        try {
            semaphore.acquire();
            LOGGER.info("Truck #" + truck.getNumber() + " occupied the terminal.");
            if (truck.getCondition() == TruckCondition.EMPTY) {
                truck.load();
            } else if (truck.getCondition() == TruckCondition.FULL) {
                truck.unload();
            } else {
                throw new TruckException("There are some logistic problem: truck #" + truck.getId() +
                        " is already " + truck.getState().toString().toLowerCase() + ".");
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        } finally {
            semaphore.release();
            LOGGER.info("Truck #" + truck.getNumber() + " released the terminal.");
        }
    }
}
