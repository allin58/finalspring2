package by.training.cryptomarket.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    static final Logger LOGGER = LogManager.getLogger("by.training.final.ServletLogger");


   /* @Pointcut("execution(* *.call(..))")
    private void pointCutForCall() {
        System.out.println("pointCutForCall");
    }

    @Before("pointCutForCall()")
    public void adviceForCall(JoinPoint joinPoint) {
        System.out.println("adviceForCall");
    }*/

    long start;

    @Before("execution(* *.execute(..))")
    public void beforeForCall(JoinPoint joinPoint) {


        start = System.currentTimeMillis();




    }

   @After("execution(* *.execute(..))")
    public void afterForCall(JoinPoint joinPoint) {

       LOGGER.info("Method of "+joinPoint.getTarget() + " executed in " + (System.currentTimeMillis() - start) +" milliseconds");
    }

/*
    @AfterReturning(value = "execution(* *.call(..))", returning = "retVal")
    public void afterReturningForCall(Object retVal) {

        System.out.println("afterReturningForCall " + (Integer)retVal );
    }

    @AfterThrowing("execution(* *.call(..))")
    public void afterThrowing(JoinPoint joinPoint) {

        System.out.println("afterThrowing ");
    }
*/


   /* @Around(value = "execution(* *.call(..))")
    public void aroundReturningForCall(ProceedingJoinPoint pjp) {
        System.out.println("aroundReturningForCall");

    }*/

}
