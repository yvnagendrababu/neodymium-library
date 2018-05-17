package com.xceptance.neodymium.module;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

public abstract class StatementBuilder extends Statement
{
    public abstract List<Object> createIterationData(TestClass testClass, FrameworkMethod method) throws Throwable;

    public abstract StatementBuilder createStatement(Object testClassInstance, Statement next, Object parameter);

    public abstract String getTestName(Object data);

    public abstract String getCategoryName(Object data);

    public static <T extends StatementBuilder> T instantiate(Class<T> clazz)
    {
        try
        {
            return clazz.newInstance();
        }
        catch (InstantiationException e)
        {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Annotation> List<T> getAnnotations(AnnotatedElement object, Class<T> annotationClass)
    {
        List<T> annotations = new LinkedList<>();
        if (object == null || annotationClass == null)
        {
            return annotations;
        }

        // check if the annotation is repeatable
        Repeatable repeatingAnnotation = annotationClass.getAnnotation(Repeatable.class);
        if (repeatingAnnotation != null)
        {
            Annotation annotation = object.getAnnotation(repeatingAnnotation.value());

            if (annotation != null)
            {
                try
                {
                    List<Object> repeatedAnnotations = Arrays.asList(((Object[]) annotation.getClass().getMethod("value")
                                                                                           .invoke(annotation)));
                    annotations.addAll((Collection<? extends T>) repeatedAnnotations);
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        }

        T anno = object.getAnnotation(annotationClass);
        if (anno != null)
        {
            annotations.add(anno);
        }

        return annotations;
    }
}
