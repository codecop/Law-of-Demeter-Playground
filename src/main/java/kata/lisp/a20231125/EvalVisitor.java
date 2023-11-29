package kata.lisp.a20231125;

import java.util.Arrays;

public class EvalVisitor {

    private final Functions context;
    private Result lastResult;

    public EvalVisitor(Functions context) {
        this.context = context;
    }

    public void visitBoolean(Boolean value) {
        lastResult = new Result(value, ResultType.BOOLEAN);
    }

    public void visitNumber(Integer value) {
        lastResult = new Result(value, ResultType.NUMBER);
    }

    public void visitString(String value) {
        lastResult = new Result(value, ResultType.STRING);
    }

    public void visitProgram(Ast[] children) {
        Result programResult = new Result("Empty Program", ResultType.ERROR);
        for (Ast ast : children) {
            ast.accept(this);
            programResult = lastResult;
        }
        lastResult = programResult;
    }

    public void visitExpression(Ast[] expressions) {
        if (expressions[0] instanceof SymbolAst) {
            lastResult = evalAsFunction(expressions, context);
            return;
        }
        throw new IllegalStateException();
    }

    private Result evalAsFunction(Ast[] expressions, Functions context) {
        SymbolAst symbol = (SymbolAst) expressions[0];
        Ast[] arguments = Arrays.copyOfRange(expressions, 1, expressions.length);
        return evalAsFunction(symbol, arguments, context);
    }

    private Result evalAsFunction(SymbolAst symbol, Ast[] arguments, Functions context) {
        // return symbol.evalAsFunction(arguments, context);
        return applyFunction(symbol.value, arguments);
    }

    private Result applyFunction(String value, Ast[] arguments) {
        Function function = context.getFunctionNamed(value);
        if (function == null) {
            return new Result("Unknown symbol " + value, ResultType.ERROR);
        }
        return applyFunction(function, arguments);
    }

    private Result applyFunction(Function function, Ast[] arguments) {
        // return function.execute(arguments, context);
        function.accept(this, arguments);
        return lastResult;
    }

    public Result result() {
        return lastResult;
    }

    public void setResult(Result r) {
        lastResult = r;
    }

}
