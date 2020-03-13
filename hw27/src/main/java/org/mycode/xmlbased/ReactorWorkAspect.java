package org.mycode.xmlbased;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public class ReactorWorkAspect {
    private int countOfCall = 0;

    public void countOfCallSetEnergyMethod() {
        countOfCall++;
    }

    public void showCountOfCallAfterStartStop(JoinPoint joinPoint) {
        System.out.println(">Aspect says: " + countOfCall + " calls of setting energy in " + joinPoint.getSignature().getName());
        countOfCall = 0;
    }
}
