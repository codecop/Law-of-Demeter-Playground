package kata.lisp.a20231125;

import java.lang.reflect.Array;

public class ArrayLib {

    public static <T> T[] copy(T[] source, T[] target, int from, int length) {
        for (int i = 0; i < length; i++) {
            target[i] = source[from + i];
        }
        return target;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] newArrayOf(Class<T> componentType, int length) {
        return (T[]) Array.newInstance(componentType, length);
    }

}
