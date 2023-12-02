package kata.lisp.a20231125.token;

public class CharacterStream {

    private final String code;

    private int current = 0;
    private int beginToken = 0;

    public CharacterStream(String code) {
        this.code = code;
    }

    public boolean notFinished() {
        return current < code.length();
    }

    public char peek() {
        return code.charAt(current);
    }

    public void next() {
        current++;
    }

    public void markBegin() {
        beginToken = current;
    }

    public boolean hasChunk() {
        return beginToken < current && current <= code.length();
    }

    public String getChunk() {
        return code.substring(beginToken, current);
    }

}
