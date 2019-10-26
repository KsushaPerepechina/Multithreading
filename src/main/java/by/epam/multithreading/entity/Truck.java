package by.epam.multithreading.entity;

import by.epam.multithreading.exception.TruckException;
import by.epam.multithreading.singleton.LogisticBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class Truck extends Thread {
    private static final Logger LOGGER = LogManager.getLogger();
    private int number;
    private TruckCondition condition;
    private boolean shortLivedContent;

    public Truck(TruckCondition condition, boolean shortLivedContent) {
        this.number = Integer.parseInt(this.getName().substring(7));
        this.condition = condition;
        this.shortLivedContent = shortLivedContent;
    }

    @Override
    public void run() {
        LogisticBase logisticBase = LogisticBase.getInstance();
        try {
            logisticBase.serveTruck(this);
        } catch (TruckException e) {
            LOGGER.error(e);
        }
    }

    public int getNumber() {
        return number;
    }

    public TruckCondition getCondition() {
        return condition;
    }

    public void setCondition(TruckCondition condition) {
        this.condition = condition;
    }

    public void load() throws InterruptedException {
        LOGGER.info("Loading truck #" + getNumber() + ".");
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        setCondition(TruckCondition.LOADED);
        LOGGER.info("Truck #" + getNumber() + " is loaded.");
    }

    public void unload() throws InterruptedException {
        LOGGER.info("Unloading truck #" + getNumber() + ".");
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        setCondition(TruckCondition.UNLOADED);
        LOGGER.info("Truck #" + getNumber() + " is unloaded.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return shortLivedContent == truck.shortLivedContent &&
                condition != null && condition == truck.condition;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(shortLivedContent);
        result = prime * result + condition.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Truck.class.getSimpleName() + "[", "]")
                .add("number=" + number)
                .add("condition=" + condition)
                .add("shortLivedContent=" + shortLivedContent)
                .add("priority=" + getPriority())
                .toString();
    }
}
