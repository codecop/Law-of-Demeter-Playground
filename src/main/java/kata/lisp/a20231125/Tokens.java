package kata.lisp.a20231125;

public class Tokens {

    private final String[] tokens;

    public Tokens(String... tokens) {
        this.tokens = tokens;
    }

    public boolean startsWith(String token) {
        return tokens[0].equals(token);
    }

    public String get(int i) {
        return tokens[i];
    }

}
