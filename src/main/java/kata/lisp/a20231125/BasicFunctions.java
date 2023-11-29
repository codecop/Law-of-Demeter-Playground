package kata.lisp.a20231125;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.eval.Result;
import kata.lisp.a20231125.eval.ResultType;

public class BasicFunctions {

    public static void add(Functions functions) {
        functions.addFunctionNamed(new IntegerAddition());
        functions.addFunctionNamed(new IntegerSquareRoot());
        functions.addFunctionNamed(new StringAppend());
    }

}

abstract class StrictFunction extends Function {

    public StrictFunction(String name) {
        super(name);
    }

    @Override
    public Result apply(Ast[] x, EvalVisitor evalVisitor) {
        StrictFunction function = this;
        Results arguments = evalArguments(x, evalVisitor);
        return execute(function, arguments);
    }

    private Results evalArguments(Ast[] arguments, EvalVisitor evalVisitor) {
        Result[] tempResults = new Result[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            arguments[i].accept(evalVisitor);
            tempResults[i] = evalVisitor.result();
        }
        return new Results(tempResults);
    }

    public abstract boolean matchesArgumentNumber(int parameterCount);

    public abstract boolean matchesArgumentType(int i, ResultType parameterType);
    
    private Result execute(StrictFunction function, Results arguments) {
        Result error = arguments.firstErrorInArguments();
        if (error != null) {
            return error;
        }

        error = arguments.numberMismatchWith(function);
        if (error != null) {
            return error;
        }

        error = arguments.typeMismatchWith(function);
        if (error != null) {
            return error;
        }

        Object[] values = arguments.toValues();
        return execute(values);
    }

    abstract Result execute(Object[] arguments);

}

class IntegerAddition extends StrictFunction {

    public IntegerAddition() {
        super("+");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count >= 1;
    }

    @Override
    public boolean matchesArgumentType(int i, ResultType type) {
        return ResultType.NUMBER == type;
    }

    @Override
    public Result execute(Object[] arguments) {
        int sum = 0;
        for (int i = 0; i < arguments.length; i++) {
            sum += (Integer) arguments[i];
        }
        return new Result(sum, ResultType.NUMBER);
    }

}

class IntegerSquareRoot extends StrictFunction {

    public IntegerSquareRoot() {
        super("sqrt");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count == 1;
    }

    @Override
    public boolean matchesArgumentType(int i, ResultType type) {
        return ResultType.NUMBER == type;
    }

    @Override
    public Result execute(Object[] arguments) {
        Integer value = (Integer) arguments[0];
        return new Result(Math.sqrt(value), ResultType.FLOAT);
    }

}

class StringAppend extends StrictFunction {

    public StringAppend() {
        super("string-append");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count >= 1;
    }

    @Override
    public boolean matchesArgumentType(int i, ResultType type) {
        return ResultType.STRING == type;
    }

    @Override
    public Result execute(Object[] arguments) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            buf.append((String) arguments[i]);
        }
        return new Result(buf.toString(), ResultType.STRING);
    }

}
