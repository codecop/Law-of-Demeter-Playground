package codingdojo;

public class BasicBuff implements Buff {

    private final float soak;

    public BasicBuff(float soak) {
        this.soak = soak;
    }

    @Override
    public float soakModifier() {
        return soak;
    }

}
