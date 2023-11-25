package kata.lisp.a20231125;

import java.lang.reflect.Array;

public class ArrayLib {

    public static <T> T[] subArray(T[] source, Class<T> componentType, int from, int to) {
        int length = to - from;
        @SuppressWarnings("unchecked")
        T[] target = (T[]) Array.newInstance(componentType, length);
        for (int i = 0; i < length; i++) {
            target[i] = source[from + i];
        }
        return target;
    }

}
