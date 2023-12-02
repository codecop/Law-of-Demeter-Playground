package kata.lisp.a20231125.token;

public class Token {

    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public boolean isNumber() {
        return token.matches("0|-?[1-9]\\d*"); // LoD_O.4
    }

    public Integer asNumber() {
        return Integer.valueOf(token); // this is a named constructor, no global method
    }

    public boolean isString() {
        return token.matches("\"[^\"]*\""); // LoD_O.4
    }

    public String asString() {
        int limit = token.length() - 1; // LoD_O.4
        return token.substring(1, limit); // LoD_O.4
    }

    public boolean isBoolean() {
        return token.matches("#f|#t"); // LoD_O.4
    }

    public boolean asBoolean() {
        return token.equals("#t"); // LoD_O.
    }

    public boolean isSymbol() {
        return token.matches("[+*!=?a-zA-Z][+*!=?a-zA-Z0-9]*(-[+*!=?a-zA-Z0-9]*)*"); // LoD_O.4
    }

    public String asSymbol() {
        return token;
    }

    public boolean isOpeningBracket() {
        return token.matches("\\(|\\[|\\{"); // LoD_O.4
    }

    public boolean isClosingBracket() {
        return token.matches("\\)|\\]|\\]"); // LoD_O.4
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Token)) {
            return false;
        }
        Token that = (Token) other;
        return token.equals(that.token); // LoD_O.4, LoD_Cs.1
    }

    @Override
    public int hashCode() {
        return token.hashCode(); // LoD_O.4
    }

    @Override
    public String toString() {
        return token;
    }

}

// LoD review OK
