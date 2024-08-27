package com.interface21.web.exception;

import com.interface21.beans.factory.BeanFactory;
import com.interface21.beans.factory.config.BeanFactoryBeanPostProcessor;
import com.interface21.web.bind.annotation.ControllerAdvice;

public class ControllerAdviceBeanPostProcessor extends BeanFactoryBeanPostProcessor {

    private BeanFactory beanFactory;

    @Override
    public Object postInitialization(final Object bean) {
        final Class<?> clazz = bean.getClass();

        if (!isControllerAdvice(clazz)) {
            return bean;
        }

        final ExceptionHandlerExceptionResolver handlerExceptionResolver = beanFactory.getBean(ExceptionHandlerExceptionResolver.class);
        handlerExceptionResolver.appendAdvice(clazz, bean);

        return bean;
    }

    @Override
    public void injectBeanFactory(final Object beanFactory) {
        this.beanFactory = (BeanFactory) beanFactory;
    }

    private boolean isControllerAdvice(final Class<?> clazz) {
        return clazz.isAnnotationPresent(ControllerAdvice.class);
    }
}