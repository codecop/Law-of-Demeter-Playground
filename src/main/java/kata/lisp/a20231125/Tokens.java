package kata.lisp.a20231125;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Tokens {

    private final Token[] tokens;
    private int position = 0;

    public static Tokens tokensOf(String... arguments) {
        // can be replaced with loop without LoD
        Token[] tokens = Arrays.stream(arguments). //
                map(Token::new). //
                collect(Collectors.toList()). //
                toArray(new Token[0]);
        return new Tokens(tokens);
    }

    public Tokens(Token... tokens) {
        this.tokens = tokens;
    }

    public boolean atOpeningBracket() {
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
