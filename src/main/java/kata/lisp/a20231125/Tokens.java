package kata.lisp.a20231125;

import java.util.Arrays;

public class Tokens {

    private final Token[] tokens;
    private int position = 0;

    public Tokens(Token... tokens) {
        this.tokens = tokens;
    }

    public boolean atStartingBracket() {
        return tokens[position].isOpeningBracket();
    }

    public Token[] tokensInsideBrackets() {
        for (int i = position; i < tokens.length; i++) {
            if (tokens[i].isClosingBracket()) {
                return Arrays.copyOfRange(tokens, position, i);
            }
        }
        return null;
    }

    public void consumeBracket() {
        position++;
    }

}
