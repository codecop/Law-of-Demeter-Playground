package kata.lisp.a20231125;

import java.util.Arrays;

public class Tokens {

    private final Token[] tokens;

    public Tokens(Token... tokens) {
        this.tokens = tokens;
    }

    public boolean startsWithBracket(int at) {
        return tokens[at].isOpeningBracket();
    }

    public Token[] tokensInsideBrackets(int at) {
        for (int i = at + 1; i < tokens.length; i++) {
            if (tokens[i].isClosingBracket()) {
                return Arrays.copyOfRange(tokens, at + 1, i);
            }
        }
        return null;
    }

}
