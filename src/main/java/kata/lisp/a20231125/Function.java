package kata.lisp.a20231125;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.eval.Result;

public abstract class Function {

    private final String name;

    public Function(String name) {
        this.name = name;
    }

    public boolean isNamed(String targetName) {
        return this.name.equals(targetName);
    }

    public abstract Result apply(Ast[] arguments, EvalVisitor evalVisitor);

    @Override
    public String toString() {
        return name;
    }

}
