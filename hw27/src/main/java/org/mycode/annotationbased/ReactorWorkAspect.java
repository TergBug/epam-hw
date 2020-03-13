package org.mycode.annotationbased;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReactorWorkAspect {
    private int countOfCall = 0;

    @Pointcut("execution(* org.mycode.model.Reactor.setEnergy(..))")
    private void setupEnergy() {
    }

    @Pointcut("execution(* org.mycode.model.ActivatorOfReactor.*Reactor())")
    private void startStopReactor() {
    }

    @After("setupEnergy()")
    public void countOfCallSetEnergyMethod() {
        countOfCall++;
    }

    @After("startStopReactor()")
    public void showCountOfCallAfterStartStop(JoinPoint joinPoint) {
        System.out.println(">Aspect says: " + countOfCall + " calls of setting energy in " + joinPoint.getSignature().getName());
        countOfCall = 0;
    }
}
