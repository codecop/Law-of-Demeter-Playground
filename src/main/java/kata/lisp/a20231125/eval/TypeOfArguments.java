package kata.lisp.a20231125.eval;

interface TypeOfArguments {

    ResultType expectedType(int index);

    boolean matches(int index, ResultType parameterType);

}

class AllSameTypeOfArguments implements TypeOfArguments {

    private final ResultType requiredType;

    public AllSameTypeOfArguments(ResultType requiredType) {
        this.requiredType = requiredType;
    }

    @Override
    public ResultType expectedType(int index) {
        return requiredType;
    }

    @Override
    public boolean matches(int index, ResultType parameterType) {
        return expectedType(index) == parameterType; // LoD_O.1
    }

}

// LoD review OK
