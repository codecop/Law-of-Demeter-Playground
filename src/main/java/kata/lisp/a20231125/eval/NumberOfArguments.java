package kata.lisp.a20231125.eval;

interface NumberOfArguments {
    
    boolean matches(int parameterCount);
    
}

class MoreThanNumberOfArguments implements NumberOfArguments {
    
    private final int requiredCount;

    public MoreThanNumberOfArguments(int requiredCount) {
        this.requiredCount = requiredCount;
    }

    @Override
    public boolean matches(int count) {
        return count >= requiredCount;
    }
}

class ExactNumberOfArguments implements NumberOfArguments {
    
    private final int requiredCount;

    public ExactNumberOfArguments(int requiredCount) {
        this.requiredCount = requiredCount;
    }

    @Override
    public boolean matches(int count) {
        return count == requiredCount;
    }
}

// LoD review OK
