package ru.geekbrains.spring.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 Аннотация TrackUserAction используется для отслеживания действий пользователя в программе.
 Она может быть применена к методам, которые выполняют определенные действия пользователя,
 и затем эти данные могут быть использованы для создания отчетов или других целей.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TrackUserAction {
}
