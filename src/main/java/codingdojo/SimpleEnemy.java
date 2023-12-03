package codingdojo;

import java.util.List;

public class SimpleEnemy extends Target {

    private final Armor armor;
    private final List<Buff> buffs;

    public SimpleEnemy(Armor armor, List<Buff> buffs) {
        this.armor = armor;
        this.buffs = buffs;
    }

    public List<Buff> getBuffs() {
        return buffs;
    }

    public Armor getArmor() {
        return this.armor;
    }
}
