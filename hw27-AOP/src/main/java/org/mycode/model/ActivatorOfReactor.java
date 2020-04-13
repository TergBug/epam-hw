package org.mycode.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivatorOfReactor {
    private Reactor reactor;

    @Autowired
    public ActivatorOfReactor(Reactor reactor) {
        this.reactor = reactor;
    }

    public void runReactor() {
        reactor.setRun(true);
        for (int i = 0; i < 20; i++) {
            reactor.setEnergy(reactor.getEnergy() + 5);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        System.out.println("Current energy: " + reactor.getEnergy());
        System.out.println("Current fuel volume: " + reactor.getFuelVolume());
    }

    public void stopReactor() {
        while (reactor.getEnergy() > 2) {
            reactor.setEnergy(reactor.getEnergy() - 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        reactor.setRun(false);
        System.out.println("Energy after stop: " + reactor.getEnergy());
        System.out.println("Fuel volume after stop: " + reactor.getFuelVolume());
    }
}
