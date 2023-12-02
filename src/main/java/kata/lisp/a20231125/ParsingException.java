package kata.lisp.a20231125;

import kata.lisp.a20231125.ast.Ast;

public class ParsingException extends RuntimeException {

    private final Ast ast;

    public ParsingException(String message, Ast ast) {
        super(message + ": " + ast.toString()); // LoD_O.2 
        this.ast = ast;
    }

    public Ast getAst() {
        return ast;
    }

}

// LoD review OK
