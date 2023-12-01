package kata.lisp.a20231125.eval;

public abstract class Function {

    private final String name;

    public Function(String name) {
        this.name = name;
    }

    public boolean isNamed(String targetName) {
        return this.name.equals(targetName);
    }

    public abstract boolean matchesArgumentNumber(int parameterCount);
    
    public abstract Result apply(LazyResults arguments, Variables variables);

    @Override
    public String toString() {
        return name;
    }

}
