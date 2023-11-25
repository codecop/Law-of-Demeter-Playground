package kata.lisp.a20231125;

public class Result {

    enum Type {
        NUMBER
    }

    private final Object value;
    private final Type type;

    public Result(Object value, Type type) {
        this.value = value;
        this.type = type;
    }

    public Object value() {
        return this.value;
    }

    public Type type() {
        return type;
    }
}
