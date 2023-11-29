package kata.lisp.a20231125;

import java.util.Arrays;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.ast.AstVisitor;
import kata.lisp.a20231125.ast.SymbolAst;
import kata.lisp.a20231125.eval.Result;
import kata.lisp.a20231125.eval.ResultType;

public class EvalVisitor implements AstVisitor {

    private final Functions context;
    private Result lastResult;

    public EvalVisitor(Functions context) {
        this.context = context;
    }

    @Override
    public void visitBoolean(Boolean value) {
        lastResult = new Result(value, ResultType.BOOLEAN);
    }

    @Override
    public void visitNumber(Integer value) {
        lastResult = new Result(value, ResultType.NUMBER);
    }

    @Override
    public void visitString(String value) {
        lastResult = new Result(value, ResultType.STRING);
    }

    @Override
    public void visitProgram(Ast[] children) {
        Result programResult = new Result("Empty Program", ResultType.ERROR);
        for (Ast ast : children) {
            ast.accept(this);
            programResult = lastResult;
        }
        lastResult = programResult;
    }

    @Override
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
        return applyFunction(symbol.getSymbol(), arguments);
    }

    private Result applyFunction(String value, Ast[] arguments) {
        Function function = context.getFunctionNamed(value);
        if (function == null) {
            return new Result("Unknown symbol " + value, ResultType.ERROR);
        }
        return applyFunction(function, arguments);
    }

    private Result applyFunction(Function function, Ast[] arguments) {
        lastResult = function.apply(arguments, this);
        return lastResult;
    }

    public Result result() {
        return lastResult;
    }

}
