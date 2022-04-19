package com.muhammedtopgul.springexcel.excel;

import com.muhammedtopgul.springexcel.annotation.ExcelColumn;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 16:50
 */

public class ExcelColumnProcessor {

    private final Map<String, String> fieldNames = new HashMap<>();
    private String columnHeader = "";

    private boolean isExclude(Field field) {
        ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
        if (!excelColumn.exclude())
            columnHeader = excelColumn.columnHeader().trim();
        else
            columnHeader = "";
        return excelColumn.exclude();
    }

    public Map<String, String> getFieldNamesForClass(Class<?> clazz) {
        Class<?> superclass = clazz.getSuperclass();
        readClassFields(superclass.getDeclaredFields());
        readClassFields(clazz.getDeclaredFields());
        return fieldNames;
    }

    private void readClassFields(Field[] fields) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                if (!isExclude(field)) {
                    fieldNames.put(field.getName(), !columnHeader.equals("") ? columnHeader : capitalize(field.getName()));
                }
            }
        }
    }

    public <T> Object invokeMethod(Class<?> clazz, T dataClass, String fieldName) throws Exception {
        Method method;
        try {
            method = clazz.getMethod("get" + capitalize(fieldName));
        } catch (NoSuchMethodException e) {
            method = clazz.getMethod("get" + fieldName);
        }
        return method.invoke(dataClass, (Object[]) null);
    }

    private String capitalize(String var) {
        if (var.length() == 0)
            return var;
        return var.substring(0, 1).toUpperCase() + var.substring(1);
    }
}
