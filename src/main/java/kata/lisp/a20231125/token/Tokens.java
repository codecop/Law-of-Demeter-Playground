package kata.lisp.a20231125.token;

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

    public boolean hasNext() {
        return position < size();
    }

    public Token next() {
        return tokens[position];
    }

    public int size() {
        return tokens.length;
    }

    public void consumeToken() {
        position++;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("Tokens: ");
        for (int i = 0; i < tokens.length; i++) {
            Token token = tokens[i];
            if (i > 0) {
                buf.append(", ");
            }
            buf.append(token);
            if (position == i) {
                buf.append(" <= current");
            }
        }
        if (!hasNext()) {
            buf.append(", <= END");
        }
        return buf.toString();
    }

}
