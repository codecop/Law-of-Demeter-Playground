package kata.lisp.a20231125.eval;

public abstract class Function {

    private final String name;
    private final NumberOfArguments numberOfArguments;

    public Function(String name, NumberOfArguments numberOfArguments) {
        this.name = name;
        this.numberOfArguments = numberOfArguments;
    }

    public String getName() {
        return name;
    }

    public boolean matchesArgumentNumber(int parameterCount) {
        return numberOfArguments.matches(parameterCount); // LoD_O.4
    }

    public abstract Result apply(LazyResults arguments, Variables variables);

    @Override
    public String toString() {
        return name;
    }

}

// LoD review OK
