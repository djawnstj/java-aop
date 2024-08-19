package com.interface21.beans.factory.proxy;

import java.lang.reflect.Method;

@FunctionalInterface
public interface Pointcut {
    Pointcut TRUE = TruePointcut.INSTANCE;
    boolean matches(final Method method, final Class<?> targetClass);
}