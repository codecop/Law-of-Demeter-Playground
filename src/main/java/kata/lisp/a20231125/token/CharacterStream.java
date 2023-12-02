package kata.lisp.a20231125.token;

public class CharacterStream {

    private final String code;

    private int current = 0;
    private int beginToken = 0;

    public CharacterStream(String code) {
        this.code = code;
    }

    public boolean notFinished() {
        return current < code.length(); // LoD_O.4
    }

    public char peek() {
        return code.charAt(current); // LoD_O.4
    }

    public void next() {
        current++; // LoD_O.1
    }

    public void markBegin() {
        beginToken = current; // LoD_O.1
    }

    public boolean hasChunk() {
        return beginToken < current && current <= code.length(); // LoD_O.4
    }

    public String getChunk() {
        return code.substring(beginToken, current); // LoD_O.4
    }

}

// LoD review OK
