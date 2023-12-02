package kata.lisp.a20231125.eval;

interface TypeOfArguments {

    ResultType get(int i);

    boolean matches(int i, ResultType parameterType);

}

class AllSameTypeOfArguments implements TypeOfArguments {

    private final ResultType requiredType;

    public AllSameTypeOfArguments(ResultType requiredType) {
        this.requiredType = requiredType;
    }

    @Override
    public ResultType get(int i) {
        return requiredType;
    }

    @Override
    public boolean matches(int i, ResultType parameterType) {
        return get(i) == parameterType; // LoD_O.1
    }

}

// LoD review OK
