package kata.lisp.a20231125;

import java.util.Objects;

public class Token {

    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public boolean isNumber() {
        return token.matches("0|-?[1-9]\\d*");
    }

    public int asNumber() {
        return Integer.parseInt(token);
    }

    public boolean isString() {
        return token.matches("\"[^\"]*\"");
    }

    public String asString() {
        return token.substring(1, token.length() - 1);
    }

    public boolean isBoolean() {
        return token.matches("#f|#t");
    }

    public boolean asBoolean() {
        return token.equals("#t");
    }

    public boolean isSymbol() {
        return token.matches("[+*!=?a-zA-Z][+*!=?a-zA-Z0-9]*(-[+*!=?a-zA-Z0-9]*)*");
    }

    public String asSymbol() {
        return token;
    }

    public boolean isOpeningBracket() {
        return token.matches("\\(|\\[|\\{");
    }

    public boolean isClosingBracket() {
        return token.matches("\\)|\\]|\\]");
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Token)) {
            return false;
        }
        Token that = (Token) other;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return token;
    }

}
