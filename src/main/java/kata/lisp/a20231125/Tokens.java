package kata.lisp.a20231125;

import java.util.Arrays;

public class Tokens {

    private final Token[] tokens;

    public Tokens(Token... tokens) {
        this.tokens = tokens;
    }

    public boolean startsWithBracket() {
        return tokens[0].isOpeningBracket();
    }

    public Token[] tokensInsideBrackets() {
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].isClosingBracket()) {
                return Arrays.copyOfRange(tokens, 1, i);
            }
        }
        return null;
    }

}
