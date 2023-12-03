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
            soak = m1((SimpleEnemy) other); // LoD_O.1
        }
        return soak;
    }

    private static int m1(SimpleEnemy simpleEnemy) {
        return m2(simpleEnemy.getArmor(), simpleEnemy); // LoD_O.2, LoD_O.1
    }

    private static int m2(Armor armor, SimpleEnemy simpleEnemy) {
        return Math.round(armor.getDamageSoak() * m3(simpleEnemy.getBuffs())); // LoD_O.2, LoD_O.2 
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
        return m4(inventory.getEquipment(), stats.getStrength()); // LoD_O.4, LoD_O.4
    }

    private static float m4(Equipment equipment, int strength) {
        Item leftHand = equipment.getLeftHand(); // LoD_O.2
        Item rightHand = equipment.getRightHand(); // LoD_O.2
        Item head = equipment.getHead(); // LoD_O.2
        Item feet = equipment.getFeet(); // LoD_O.2
        Item chest = equipment.getChest(); // LoD_O.2

        float strengthModifier = strength * 0.1f;
        return strengthModifier + m5(leftHand, rightHand, head, feet, chest); // LoD_O.1
    }

    private static float m5(Item leftHand, Item rightHand, Item head, Item feet, Item chest) {
        return leftHand.getDamageModifier() + // LoD_O.2
                rightHand.getDamageModifier() + // LoD_O.2
                head.getDamageModifier() + // LoD_O.2
                feet.getDamageModifier() + // LoD_O.2
                chest.getDamageModifier(); // LoD_O.2
    }

    private int getBaseDamage() {
        return m6(inventory.getEquipment()); // LoD_O.4
    }

    private static int m6(Equipment equipment) {
        Item leftHand = equipment.getLeftHand(); // LoD_O.2
        Item rightHand = equipment.getRightHand(); // LoD_O.2
        Item head = equipment.getHead(); // LoD_O.2
        Item feet = equipment.getFeet(); // LoD_O.2
        Item chest = equipment.getChest(); // LoD_O.2
        return m7(leftHand, rightHand, head, feet, chest); // LoD_O.1
    }

    private static int m7(Item leftHand, Item rightHand, Item head, Item feet, Item chest) {
        return leftHand.getBaseDamage() + // LoD_O.2
                rightHand.getBaseDamage() + // LoD_O.2
                head.getBaseDamage() + // LoD_O.2
                feet.getBaseDamage() + // LoD_O.2
                chest.getBaseDamage(); // LoD_O.2
    }

}
