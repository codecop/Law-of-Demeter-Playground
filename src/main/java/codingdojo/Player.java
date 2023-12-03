package codingdojo;

import java.util.List;

public class Player extends Target {

    private final Inventory inventory;
    private final Stats stats;

    public Player(Inventory inventory, Stats stats) {
        this.inventory = inventory;
        this.stats = stats;
    }

    public Damage calculateDamage(Target other) {
        int baseDamage = getBaseDamage();
        float damageModifier = getDamageModifier();
        int totalDamage = Math.round(baseDamage * damageModifier);
        int soak = getSoak(other, totalDamage);

        int soakedDamage = totalDamage - soak;
        int damage = Math.max(0, soakedDamage);
        return new Damage(damage);
    }

    private static int getSoak(Target other, int totalDamage) {
        int soak = 0;
        if (other instanceof Player) {
            // TODO: Not implemented yet
            // Add friendly fire
            soak = totalDamage;

        } else if (other instanceof SimpleEnemy) {
            soak = m1((SimpleEnemy) other);
        }
        return soak;
    }

    private static int m1(SimpleEnemy simpleEnemy) {
        return m2(simpleEnemy.getArmor(), simpleEnemy);
    }

    private static int m2(Armor armor, SimpleEnemy simpleEnemy) {
        return Math.round(armor.getDamageSoak() * m3(simpleEnemy.getBuffs()));
    }

    private static float m3(List<Buff> buffs) {
        float sum = 0f;
        for (Buff buff : buffs) {
            float violation = buff.soakModifier();
            sum += violation;
        }
        return sum + 1f;
    }

    private float getDamageModifier() {
        return m4(inventory.getEquipment(), stats.getStrength());
    }

    private static float m4(Equipment equipment, int strength) {
        Item leftHand = equipment.getLeftHand();
        Item rightHand = equipment.getRightHand();
        Item head = equipment.getHead();
        Item feet = equipment.getFeet();
        Item chest = equipment.getChest();

        float strengthModifier = strength * 0.1f;
        return strengthModifier + m5(leftHand, rightHand, head, feet, chest);
    }

    private static float m5(Item leftHand, Item rightHand, Item head, Item feet, Item chest) {
        return leftHand.getDamageModifier() + //
                rightHand.getDamageModifier() + //
                head.getDamageModifier() + //
                feet.getDamageModifier() + //
                chest.getDamageModifier();
    }

    private int getBaseDamage() {
        return m6(inventory.getEquipment());
    }

    private static int m6(Equipment equipment) {
        Item leftHand = equipment.getLeftHand();
        Item rightHand = equipment.getRightHand();
        Item head = equipment.getHead();
        Item feet = equipment.getFeet();
        Item chest = equipment.getChest();
        return m7(leftHand, rightHand, head, feet, chest);
    }

    private static int m7(Item leftHand, Item rightHand, Item head, Item feet, Item chest) {
        return leftHand.getBaseDamage() + //
                rightHand.getBaseDamage() + //
                head.getBaseDamage() + //
                feet.getBaseDamage() + //
                chest.getBaseDamage();
    }

}
