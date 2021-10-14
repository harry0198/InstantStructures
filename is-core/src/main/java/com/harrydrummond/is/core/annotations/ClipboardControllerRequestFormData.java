package com.harrydrummond.is.core.annotations;

import com.harrydrummond.is.core.schematic.ClipboardController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClipboardControllerRequestFormData {

    private final Method method;
    private final Object instance;
    private final int priority;

    public ClipboardControllerRequestFormData(Method method, Object instance, int priority) {
        this.method = method;
        this.instance = instance;
        this.priority = priority;
    }

    public Method getMethod() {
        return method;
    }

    public Object getInstance() {
        return instance;
    }

    public int getPriority() {
        return priority;
    }

    public ClipboardController callMethod() {

        Object request;
        try {
            request = method.invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO log it? handle it? etc.
            return null;
        }
        return (ClipboardController) request;
    }

}