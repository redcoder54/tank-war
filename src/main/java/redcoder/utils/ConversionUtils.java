package redcoder.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ConversionUtils {

    public static <T> Object convert(Class<T> type, Object value) {
        if (type.isAssignableFrom(Boolean.class)) {
            return Boolean.TRUE.equals(value);
        } else if (type.isAssignableFrom(String.class)) {
            return value.toString();
        } else if (type.isAssignableFrom(Long.class)) {
            return Long.parseLong(value.toString());
        } else if (type.isAssignableFrom(Integer.class)) {
            return Integer.parseInt(value.toString());
        } else if (type.isAssignableFrom(Set.class)) {
            throw new IllegalArgumentException("type not supported:" + type.getName());
        } else if (type.isAssignableFrom(Collection.class) || type.isAssignableFrom(List.class)) {
            throw new IllegalArgumentException("type not supported:" + type.getName());
        } else {
            Object instance;
            try {
                instance = forName(value.toString()).newInstance();
            } catch (Exception ex) {
                // try one more hack to load the thing
                try {
                    ClassLoader loader = ClassLoader.getSystemClassLoader();
                    instance = loader.loadClass(value.toString()).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Cannot instantiate " + value + ", even on second attempt. ", e);
                }
            }

            try {
                return (T) instance;
            } catch (ClassCastException e) {
                System.err.println();
                throw new RuntimeException("Value " + value + ", is not of expected type.", e);
            }
        }
    }

    private static Class<?> forName(String name) throws ClassNotFoundException {
        ClassLoader ctxLoader = Thread.currentThread().getContextClassLoader();
        try {
            return Class.forName(name, true, ctxLoader);
        } catch (ClassNotFoundException e) {
            // ignore
        }
        return Class.forName(name);
    }
}
