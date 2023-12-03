package codingdojo;

public class SimpleArmor implements Armor {

    private final int soak;

    public SimpleArmor(int soak) {
        this.soak = soak;
    }

    @Override
    public int getDamageSoak() {
        return soak;
    }
}
