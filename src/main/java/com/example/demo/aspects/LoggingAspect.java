package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

// Defining the LoggingAspect class and marking it as an aspect and a component
@Aspect
@Component
public class LoggingAspect {

    // Defining a pointcut for the getPosts method in the controllers package
    @Pointcut("execution(* com.example.pdf.demo.controllers.*.getPosts(..))")
    public void getPostsPointcut(){}

    // Defining a pointcut for any method in the repositories package
    @Pointcut("execution(* com.example.pdf.demo.repositories.*.*(..))")
    public void repositoryLayer(){}

    // Defining an advice to be executed before the getPosts method is called
    @Before("getPostsPointcut()")
    public void beforeGetPosts(JoinPoint joinPoint){
        System.out.println("Before fetching posts: "+joinPoint.getSignature()+", search ="+joinPoint.getArgs()[0]);
    }

    // Defining an advice to be executed after the getPosts method is called
    @After("getPostsPointcut()")
    public void afterGetPosts(JoinPoint joinPoint){
        System.out.println("After fetching posts: "+joinPoint.getSignature()+", search ="+joinPoint.getArgs()[0]);
    }

    // Defining an advice to be executed after the getPosts method returns successfully
    @AfterReturning(pointcut = "getPostsPointcut()", returning = "result")
    public void afterGetPostsReturning(JoinPoint joinPoint, Object result){
        System.out.println("After fetching posts: "+joinPoint.getSignature()+", result ="+result);
    }

    // Defining an advice to be executed if the getPosts method throws an exception
    @AfterThrowing(pointcut = "getPostsPointcut()", throwing =  "ex")
    public void afterGetPostsThrowing(JoinPoint joinPoint, Exception ex){
        System.out.println("Exception thrown while fetching posts: "+joinPoint.getSignature()+", ex ="+ex.getMessage());
    }

    // Defining an advice to be executed around the getPost method in the controllers package
    @Around("execution(* com.example.pdf.demo.controllers.*.getPost(..))")
    public Object aroundGetPost(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Before fetching post by id: "+joinPoint.getArgs()[0]);
        Object result = joinPoint.proceed();
        // Object result = new ResponseEntity<>("Oops..",HttpStatus.ALREADY_REPORTED);
        System.out.println("After fetching post by id. result="+result);
        return result;
    }

    // Defining an advice to be executed after any method in the controllers package is called
    @After("within(com.example.pdf.demo.controllers.*)")
    public void afterControllersInGeneral(JoinPoint joinPoint){
        System.out.println("After controller method: "+joinPoint.getSignature());
    }

    // Defining an advice to be executed before any method with the @PostMapping annotation is called
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void beforeAnyPostRequest(JoinPoint joinPoint){
        System.out.println("Before any POST request: "+joinPoint.getSignature());
    }

    // This method is executed before any method annotated with the custom annotation "MyCustomAnnotation"
    @Before("@annotation(com.example.pdf.demo.annotations.MyCustomAnnotation)")
    public void beforeMyCustomAnnotation(JoinPoint joinPoint){
        System.out.println("Before my MyCustomAnnotation: "+joinPoint.getSignature());
    }

    // This method is executed before any method in the repository layer
    @Before("repositoryLayer()")
    public void beforeRepoLayer(JoinPoint joinPoint){
        System.out.println("Before repo layer: "+joinPoint.getSignature());
    }

    // This method measures the time taken for the execution of any method in the controllers package and prints it
    @Around("within(com.example.pdf.demo.controllers.*)")
    public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long timeTakeninMs = endTime - startTime;
        System.out.println("Time taken by "+joinPoint.getSignature()+" is "+timeTakeninMs+"ms");
        return result;
    }

}
