package kata.lisp.a20231125.eval;

public abstract class Function {

    private final String name;
    private final NumberOfArguments numberOfArguments;

    public Function(String name, NumberOfArguments numberOfArguments) {
        this.name = name;
        this.numberOfArguments = numberOfArguments;
    }

    public boolean matchesArgumentNumber(int parameterCount) {
        return numberOfArguments.matches(parameterCount);
    }

    public boolean isNamed(String targetName) {
        return this.name.equals(targetName);
    }

    public abstract Result apply(LazyResults arguments, Variables variables);

    @Override
    public String toString() {
        return name;
    }

}
