package org.mycode.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("reactor.properties")
public class Reactor {
    private long startTime;
    private double energy;
    private double fuelVolume;
    private boolean isRun;

    public Reactor(@Value("${reactor.fuel.volume}") double fuelVolume) {
        this.fuelVolume = fuelVolume;
        this.energy = 0;
        this.isRun = false;
        this.startTime = -1;
    }

    public double getEnergy() {
        if (fuelVolume <= 0) {
            fuelVolume = 0;
            setRun(false);
        }
        return energy;
    }

    public void setEnergy(double energy) {
        if (energy > 0) {
            this.energy = energy;
        }
    }

    public double getFuelVolume() {
        if (fuelVolume <= 0) {
            fuelVolume = 0;
            setRun(false);
        }
        if (startTime >= 0) {
            long workTime = (System.currentTimeMillis() - startTime) / 1000;
            fuelVolume = fuelVolume - (workTime * energy / 1000);
        }
        return fuelVolume;
    }

    public void setFuelVolume(double fuelVolume) {
        if (!isRun && fuelVolume > 0) {
            this.fuelVolume = fuelVolume;
        }
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        if (!isRun && run) {
            startTime = System.currentTimeMillis();
            energy = 1;
        } else if (isRun && !run) {
            startTime = -1;
            energy = 0;
        }
        isRun = run;
    }
}
