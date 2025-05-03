package com.tkachenkopetr.spring.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Around("execution(* com.tkachenkopetr.spring.TaskManager.*(..))")
    public Object aroundTaskManagerMethodsLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String methodName = proceedingJoinPoint.getSignature().getName();
        boolean isVoidMethod = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName().equals("void");

        System.out.println("aroundTaskManagerMethodsLoggingAdvice: сообщение выводится до выполнения метода " + methodName + " из TaskManager");

        Object targetMethodResult = null;

        try {
            targetMethodResult = proceedingJoinPoint.proceed();
            if(!isVoidMethod){
                System.out.println("Метод " + methodName + " вернул " + targetMethodResult);
            }

        } catch (Exception e) {
            System.out.println("aroundTaskManagerMethodsLoggingAdvice: поймано исключение " + e + " из TaskManager");
            throw e;
        }

        System.out.println("aroundTaskManagerMethodsLoggingAdvice: сообщение выводится после успешного выполнения метода " + methodName + " из TaskManager");
        System.out.println("-----------------------------------"); //Separator line for better readability
        return targetMethodResult;
    }

    @Around("execution(public void com.tkachenkopetr.spring.Task.check*(..))")
    public void aroundCheckMethodsLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aroundCheckMethodsLoggingAdvice: сообщение выводится до выполнения проверочного метода " + proceedingJoinPoint.getSignature().getName() + " из Task");

        try{
            proceedingJoinPoint.proceed();
        } catch(Exception e){
            System.out.println("aroundCheckMethodsLoggingAdvice: поймано исключение " + e + " после выполнения проверочного метода " + proceedingJoinPoint.getSignature().getName() + " из Task");
            throw e;
        }

        System.out.println("aroundCheckMethodsLoggingAdvice: сообщение выводится после успешного выполнения проверочного метода " + proceedingJoinPoint.getSignature().getName() + " из Task");
        System.out.println("-----------------------------------"); //Separator line for better readability

    }

    @AfterThrowing(pointcut = "execution(public void com.tkachenkopetr.spring.User.*(..))", throwing = "e")
    public void afterThrowingUserMethodsLoggingAdvice(JoinPoint joinPoint, Throwable e) throws Throwable {
        System.out.println("afterThrowingUserMethodsLoggingAdvice: выброшено исключение в методе " + joinPoint.getSignature().getName() + " из класса User");
        System.out.println("Исключение: " + e.getMessage());
    }
}