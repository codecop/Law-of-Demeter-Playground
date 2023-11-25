package kata.lisp.a20231125;

public class Tokens {

    private final Token[] tokens;
    private int position = 0;

    public Tokens(Token... tokens) {
        this.tokens = tokens;
    }

    public boolean atStartingBracket() {
        return tokens[position].isOpeningBracket();
    }

    public boolean atClosingBracket() {
        return tokens[position].isClosingBracket();
    }

    public Token next() {
        return tokens[position];
    }

    public void consumeToken() {
        position++;
    }

}
