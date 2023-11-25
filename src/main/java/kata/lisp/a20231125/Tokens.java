package kata.lisp.a20231125;

public class Tokens {

    private final String[] tokens;

    public Tokens(String... tokens) {
        this.tokens = tokens;
    }

    public boolean startsWithBracket() {
        return isOpeningBracket(get(0));
    }

    private boolean isOpeningBracket(String token) {
        return token.matches("\\(|\\[|\\{");
    }

    private boolean isClosingBracket(String token) {
        return token.matches("\\)|\\]|\\]");
    }

    public String[] tokensInsideBrackets() {
        for (int i = 1; i < tokens.length; i++) {
            if (isClosingBracket(get(i))) {
                return ArrayLib.subArray(tokens, String.class, 1, i);
            }
        }
        return null;
    }

    public String get(int i) {
        return tokens[i];
    }

}
