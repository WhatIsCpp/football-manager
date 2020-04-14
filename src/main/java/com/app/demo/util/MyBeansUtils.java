package com.app.demo.util;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class MyBeansUtils<T> {
    public void copyNonNullProperties(T target, T source) {
        if (source == null || target == null || target.getClass() != source.getClass()) return;

        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper trgt = new BeanWrapperImpl(target);

        for (final Field property : FieldUtils.getAllFieldsList(target.getClass())) {
            Object providedObject = src.getPropertyValue(property.getName());
            if (providedObject != null) {
                trgt.setPropertyValue(
                        property.getName(),
                        providedObject);
            }
        }
    }

}
