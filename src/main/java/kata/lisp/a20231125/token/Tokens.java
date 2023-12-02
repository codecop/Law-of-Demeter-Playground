package kata.lisp.a20231125.token;

public class Tokens {

    private final Token[] tokens;
    private int position = 0;

    public static Tokens tokensOf(String... arguments) {
        Token[] target = new Token[arguments.length]; // LoD_O.2
        for (int i = 0; i < arguments.length; i++) { // LoD_O.2
            target[i] = new Token(arguments[i]); // LoD_O.2
        }
        return new Tokens(target);
    }

    public Tokens(Token... tokens) {
        this.tokens = tokens;
    }

    public boolean atOpeningBracket() {
        return tokens[position].isOpeningBracket(); // LoD_O.4
    }

    public boolean atClosingBracket() {
        return tokens[position].isClosingBracket(); // LoD_O.4
    }

    public boolean hasNext() {
        return position < size(); // LoD_O.1
    }

    public Token next() {
        return tokens[position]; // LoD_O.1
    }

    public int size() {
        return tokens.length; // LoD_O.1
    }

    public void consumeToken() {
        position++; // LoD_O.1
    }

    public boolean finished() {
        return !hasNext(); // LoD_O.1
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("Tokens: "); // LoD_O.3
        for (int i = 0; i < size(); i++) { // LoD_O.1
            Token token = tokens[i];
            if (i > 0) {
                buf.append(", "); // LoD_O.3
            }
            buf.append(token); // toString is implicit, is flagged as violation
            if (position == i) {
                buf.append(" <= current"); // LoD_O.3
            }
        }
        if (finished()) { // LoD_O.1
            buf.append(", <= END"); // LoD_O.3
        }
        return buf.toString(); // LoD_O.3
    }

}
