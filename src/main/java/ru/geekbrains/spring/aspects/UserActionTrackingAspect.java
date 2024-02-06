package ru.geekbrains.spring.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
public class UserActionTrackingAspect {
    private final Map<String, String> mapAction = Map.of(
            "getAllNotes", "Запрос всех записей(getAllNotes)",
            "addNote", "Добавление записи(addNote)",
            "getNoteById", "Получение записи по ID(getNoteById)",
            "updateNote","Обновление записи по ID(updateNote)",
            "deleteNote","Удаление записи по ID(deleteNote)"
    );

    @Around("@annotation(TrackUserAction)")
    public Object trackUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = mapAction.get(joinPoint.getSignature().getName());
        String arguments = Arrays.toString(args);
        System.out.print(methodName+", аргументы: " + arguments);
        long runtime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        runtime = System.currentTimeMillis()-runtime;
        System.out.printf(", выполнено за :%d мс\n",runtime);
        return result;
    }
}
