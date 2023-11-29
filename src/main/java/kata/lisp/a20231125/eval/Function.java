package kata.lisp.a20231125.eval;

public abstract class Function {

    private final String name;

    public Function(String name) {
        this.name = name;
    }

    public boolean isNamed(String targetName) {
        return this.name.equals(targetName);
    }

    public abstract Result apply(LazyResult[] arguments);

    @Override
    public String toString() {
        return name;
    }

}
