package kata.lisp.a20231125;

public class Tokens {

    private final String[] tokens;

    public Tokens(String... tokens) {
        this.tokens = tokens;
    }

    public boolean isList() {
        return isOpeningBracket(get(0));
    }

    private boolean isOpeningBracket(String token) {
        return token.matches("\\(|\\[|\\{");
    }

    private boolean isClosingBracket(String token) {
        return token.matches("\\)|\\]|\\]");
    }

    public String[] insideBrackets() {
        for (int i = 1; i < tokens.length; i++) {
            if (isClosingBracket(get(i))) {
                return subArray(1, i);
            }
        }
        return null;
    }

    private String[] subArray(int from, int to) {
        String[] d = new String[to - from];
        for (int i = 0; i < d.length; i++) {
            d[i] = tokens[from + i];
        }
        return d;
    }

    public String get(int i) {
        return tokens[i];
    }

}
