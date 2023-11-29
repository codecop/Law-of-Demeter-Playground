package kata.lisp.a20231125;

public class Lexer {

    public Tokens tokenise(String code) {
        Token token = new Token(code);
        return new Tokens(token);
    }

}
