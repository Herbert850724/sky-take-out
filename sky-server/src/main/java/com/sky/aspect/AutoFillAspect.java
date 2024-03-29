package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//自定義切面類，實現自動填充邏輯
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    //切入點
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    //前置通知，在通知中進行公共字段的賦值
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("開始進行公共字段自動填充");

        //獲取到當前被攔截的方法上的數據庫操作類型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法簽名對象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//獲得方法的註解對象
        OperationType operationType = autoFill.value();//獲得數據庫操作類型

        //獲取到當前被攔截的方法的參數--實體對象
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            return;
        }

        Object entity = args[0];

        //準備賦職的數據
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //根據當前不同的操作類型，為對應的屬性通過反射來賦值

    }
}
