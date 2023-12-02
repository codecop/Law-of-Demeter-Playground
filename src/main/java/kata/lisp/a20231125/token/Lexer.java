package kata.lisp.a20231125.token;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final List<String> chunks = new ArrayList<>();
    private final CharacterStream stream;

    public Lexer(String code) {
        this.stream = new CharacterStream(code);
    }

    public Tokens tokenise() {
        stream.markBegin();
        while (stream.notFinished()) {

            if (isIgnoredSeparator()) {
                addPreviousChunk();
                ignoreCurrent();
                stream.markBegin();

            } else if (isBraces()) {
                addPreviousChunk();
                addCurrent();
                stream.markBegin();

            } else if (isQuote()) {
                addPreviousChunk();
                addUpToQuote();
                stream.markBegin();

            } else {
                stream.next();
            }

        }
        addPreviousChunk();

        return Tokens.tokensOf(chunks.toArray(new String[0]));
    }

    private boolean isIgnoredSeparator() {
        return Character.isWhitespace(stream.peek());
    }

    private void addPreviousChunk() {
        if (stream.hasChunk()) {
            chunks.add(stream.getChunk());
        }
    }

    private void ignoreCurrent() {
        stream.next();
    }

    private boolean isBraces() {
        return ("" + stream.peek()).matches("\\(|\\[|\\{|\\)|\\]|\\]");
    }

    private void addCurrent() {
        stream.markBegin();
        stream.next();
        addPreviousChunk();
    }

    private boolean isQuote() {
        return stream.peek() == '"';
    }

    private void addUpToQuote() {
        stream.markBegin();
        stream.next();
        while (!isQuote() && stream.notFinished()) {
            stream.next();
        }
        stream.next();
        addPreviousChunk();
    }

}
