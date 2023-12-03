package kata.lisp.a20231125.eval;

public class BasicFunctions {

    public static void addTo(Functions functions) {
        functions.addFunctionNamed(new IntegerAddition()); // LoD_O.2
        functions.addFunctionNamed(new IntegerSquareRoot());
        functions.addFunctionNamed(new StringAppend());
        functions.addFunctionNamed(new Error());
        functions.addFunctionNamed(new If());
        functions.addFunctionNamed(new Let());
        functions.addFunctionNamed(new Define());
    }

}

class IntegerAddition extends StrictFunction {

    public IntegerAddition() {
        super("+", new MoreThanNumberOfArguments(1), new AllSameTypeOfArguments(ResultType.NUMBER));
    }

    @Override
    protected Result apply(Object[] arguments) {
        int sum = 0;
        for (int i = 0; i < arguments.length; i++) {
            sum += (Integer) arguments[i];
        }
        return new Result(sum, ResultType.NUMBER);
    }

}

class IntegerSquareRoot extends StrictFunction {

    public IntegerSquareRoot() {
        super("sqrt", new ExactNumberOfArguments(1), new AllSameTypeOfArguments(ResultType.NUMBER));
    }

    @Override
    protected Result apply(Object[] arguments) {
        Integer value = (Integer) arguments[0];
        return new Result(Math.sqrt(value), ResultType.FLOAT);
    }

}

class StringAppend extends StrictFunction {

    public StringAppend() {
        super("string-append", new MoreThanNumberOfArguments(1), new AllSameTypeOfArguments(ResultType.STRING));
    }

    @Override
    protected Result apply(Object[] arguments) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            buf.append((String) arguments[i]); // LoD_O.3
        }
        return new Result(buf.toString(), ResultType.STRING); // LoD_O.3
    }

}

class Error extends StrictFunction {

    public Error() {
        super("error", new ExactNumberOfArguments(1), new AllSameTypeOfArguments(ResultType.STRING));
    }

    @Override
    protected Result apply(Object[] arguments) {
        return Result.error(arguments[0].toString()); // LoD_O.2
    }

}

// LoD review OK
