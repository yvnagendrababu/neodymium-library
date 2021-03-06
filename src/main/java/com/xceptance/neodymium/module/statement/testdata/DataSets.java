package com.xceptance.neodymium.module.statement.testdata;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <b>Do not use this annotation</b>. This annotation is just the wrapper for repeated {@link DataSet} annotations.
 * 
 * @author m.kaufmann
 */
@Retention(RUNTIME)
@Target(
{
  TYPE, METHOD
})
public @interface DataSets
{
    DataSet[] value();
}
