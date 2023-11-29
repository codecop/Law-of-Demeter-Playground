package kata.lisp.a20231125.token;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String code;
    private final List<String> chunks = new ArrayList<>();

    private int beginToken = 0;
    private int current = 0;

    public Lexer(String code) {
        this.code = code;
    }

    public Tokens tokenise() {
        while (notFinished()) {

            if (isIgnoredSeparator()) {
                addPreviousChunk();
                ignoreCurrent();

            } else if (isBraces()) {
                addPreviousChunk();
                addCurrent();

            } else if (isQuote()) {
                addPreviousChunk();
                addUpToQuote();

            } else {
                current++;
            }

        }
        addPreviousChunk();

        return Tokens.tokensOf(chunks.toArray(new String[0]));
    }

    private boolean notFinished() {
        return current < code.length();
    }

    private char peek() {
        return code.charAt(current);
    }

    private boolean isIgnoredSeparator() {
        return Character.isWhitespace(peek());
    }

    private void addPreviousChunk() {
        if (beginToken < current && current <= code.length()) {
            chunks.add(code.substring(beginToken, current));
        }
        beginToken = current;
    }

    private void ignoreCurrent() {
        current++;
        beginToken = current;
    }

    private boolean isBraces() {
        return ("" + peek()).matches("\\(|\\[|\\{|\\)|\\]|\\]");
    }

    private void addCurrent() {
        current += 1;
        addPreviousChunk();
    }

    private boolean isQuote() {
        return peek() == '"';
    }

    private void addUpToQuote() {
        current++;
        while (!isQuote() && notFinished()) {
            current++;
        }
        current++;
        addPreviousChunk();
    }

}
