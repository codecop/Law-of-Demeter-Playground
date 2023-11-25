package kata.lisp.a20231125;

public class Parser {

    public Ast parse(String token) {
        return new NumberAst(Integer.parseInt(token));
    }

}
