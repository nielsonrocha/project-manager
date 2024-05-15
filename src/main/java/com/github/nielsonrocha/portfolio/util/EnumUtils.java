package com.github.nielsonrocha.portfolio.util;

import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EnumUtils<T extends Enum<T>> {

  public List<T> transformEnumList(Class<T> clazz) {
    Set<T> values = EnumSet.allOf(clazz);
    return List.copyOf(values);
  }

  public T fromString(Class<T> clazz, String enumValue, String method) {
    try {

      Set<T> values = EnumSet.allOf(clazz);
      Method stringValueMethod = clazz.getMethod(method, (Class<?>[]) null);

      Optional<T> value =
          values.stream()
              .filter(
                  enumElement -> {
                    try {
                      String stringValue =
                          (String) stringValueMethod.invoke(enumElement, (Object[]) null);
                      return stringValue.equalsIgnoreCase(enumValue.trim());
                    } catch (Exception e) {
                      return false;
                    }
                  })
              .findFirst();
      if (value.isPresent()) {
        return value.get();
      }
    } catch (Exception ex) {
      return null;
    }
    return null;
  }

  public T fromString(Class<T> clazz, String enumValue) {
    return this.fromString(clazz, enumValue, "getStringValue");
  }
}
