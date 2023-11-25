package kata.lisp.a20231125;

public class NumberResult implements Result {

    private final Integer value;

    public NumberResult(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }

}
