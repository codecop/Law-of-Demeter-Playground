package kata.lisp.a20231125;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String code;
    private final List<String> chunks = new ArrayList<>();
    private int lastStart = 0;
    private int current = 0;

    public Lexer(String code) {
        this.code = code;
    }

    public Tokens tokenise() {
        while (current < code.length()) {
            char ch = code.charAt(current);

            if (isIgnoredSeparator(ch)) {
                addChunk();
                ignoreCurrent();

            } else if (isBraces(ch)) {
                addChunk();
                addCurrent();

            } else {
                current++;
            }

        }
        addChunk();

        return Tokens.tokensOf(chunks.toArray(new String[0]));
    }

    private boolean isIgnoredSeparator(char ch) {
        return Character.isWhitespace(ch);
    }

    private void addChunk() {
        if (lastStart < current && current <= code.length()) {
            chunks.add(code.substring(lastStart, current));
        }
        lastStart = current;
    }

    private void ignoreCurrent() {
        current++;
        lastStart = current;
    }

    private boolean isBraces(char ch) {
        return ("" + ch).matches("\\(|\\[|\\{|\\)|\\]|\\]");
    }

    private void addCurrent() {
        current += 1;
        addChunk();
    }

}
