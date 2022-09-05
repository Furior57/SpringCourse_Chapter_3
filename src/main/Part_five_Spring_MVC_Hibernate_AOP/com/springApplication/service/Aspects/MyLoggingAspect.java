package com.springApplication.service.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

// Здесь мы прописали что работаем со всеми классами и всеми их методами в пакете DAO
@Component
@Aspect
public class MyLoggingAspect {
    @Around("execution(* com.springApplication.service.DAO.*.*(..))")
    // Вспоминаем, что возвращаемый тип у нас Object, так как тип адвайса Around, то мы должны
    // сами запустить метод внутри аспекта, делается это с помощью объекта ProceedingJoinPoint.
    // Должны указать, что этот аспект может выбросить ошибку.
    // Результат выполнения получаем с помощью joinPoint.proceed() и его же возвращаем.
    // На этом написание нашего приложения объединяющего SpringMVC-Hibernate_AOP закончено.
    public Object aroundAllRepositoryMethodsAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String name = methodSignature.getName();
        System.out.println("Begin of "+ name);
        Object proceed = joinPoint.proceed();
        System.out.println("End of "+ name);
        return proceed;

    }
}
