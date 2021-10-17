package com.harrydrummond.is.core.annotations;

import com.google.common.reflect.ClassPath;
import com.harrydrummond.is.core.schematic.ClipboardController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationUtils.class);

    /**
     * Returns all classes in package and sub-packages. If IOException occurs,
     * will return empty set and post to logger. //TODO PROBABLY CHANGE TO METHOD SIGNATURE
     * @param packageName Top level package to search through
     * @return All classes found
     */
    public static Set<Class<?>> findAllClassesWithinPackage(String packageName) {
        Set<Class<?>> set;
        try {
            set = ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getTopLevelClassesRecursive(packageName)
                    .stream()
                    .map(clazz -> clazz.load())
                    .collect(Collectors.toSet());
        } catch (IOException io) {
            LOGGER.error("Failed to access class loader!", io);
            return Set.of();
        }
        return set;

    }

    /**
     * Searches recursively through package for classes annotated with {@link HandlerRequest} and
     * with extension of {@link ClipboardController}. Method retrieves meta data from annotation and required
     * interface method and stored in {@link ClipboardControllerRequestFormData} using reflection. If required interface method
     * or instance cannot be created or found, class is skipped and logged.
     * @param packageName Package to search through recursively
     * @return List of captured data.
     */
    public static List<ClipboardControllerRequestFormData> getAllRequesteeClipboardControllers(String packageName) {
        Set<Class<?>> packageClasses = findAllClassesWithinPackage(packageName);
        List<ClipboardControllerRequestFormData> data = new ArrayList<>();

        for (Class<?> clazz : packageClasses) {
            HandlerRequest requestAnnotation = getHandlerRequest(clazz);
            if (requestAnnotation == null) {
                continue;
            }

            // If code reaches this point, it implements ClipboardController interface and thus has the following method
            Method method;
            Object instance;
            try {
                method = clazz.getDeclaredMethod("request");
                instance = clazz.getDeclaredConstructor().newInstance();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                LOGGER.warn("Failed to invoke class {}", clazz, e);
                continue;
            } catch (NoSuchMethodException e) {
                LOGGER.warn("Class {} requested clipboard controller but did not contain request method or declared constructor!",clazz.getName());
                continue;
            }

            data.add(new ClipboardControllerRequestFormData(method, instance, requestAnnotation.priority()));
        }

        return data;
    }

    /**
     * Gets handler request annotation from class if annotation is present and extends {@link ClipboardController}.
     * Null if class does not contain {@link HandlerRequest} annotation or implement {@link ClipboardController} class
     * @param clazz Class to get handler request from
     * @return HandlerRequest object annotation if present. Null if class does not contain
     * {@link HandlerRequest} annotation or implement {@link ClipboardController} class
     */
    public static HandlerRequest getHandlerRequest(Class<?> clazz) {
        HandlerRequest requestAnnotation = clazz.getAnnotation(HandlerRequest.class);
        if (requestAnnotation == null) return null;
        if (!ClipboardController.class.isAssignableFrom(clazz)) return null;

        return requestAnnotation;
    }

}