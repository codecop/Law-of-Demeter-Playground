package kata.lisp.a20231125;

public interface Function {

    boolean isNamed(String name);

    boolean matchesArgumentNumber(int count);

    boolean matchesArgumentType(int i, ResultType type);

    Result execute(Ast[] arguments, Functions context);

    void accept(EvalVisitor evalVisitor, Ast[] arguments);
}
