package codingdojo;

import java.util.List;

class Player extends Target {
    private Inventory inventory;
    private Stats stats;

    Player(Inventory inventory, Stats stats) {
        this.inventory = inventory;
        this.stats = stats;
    }

    Damage calculateDamage(Target other) {
        int baseDamage = getBaseDamage();
        float damageModifier = getDamageModifier();
        int totalDamage = Math.round(baseDamage * damageModifier);
        int soak = getSoak(other, totalDamage);
        return new Damage(Math.max(0, totalDamage - soak));
    }

    private int getSoak(Target other, int totalDamage) {
        int soak = 0;
        if (other instanceof Player) {
            // TODO: Not implemented yet
            //  Add friendly fire
            soak = totalDamage;
        } else if (other instanceof SimpleEnemy) {
            SimpleEnemy simpleEnemy = (SimpleEnemy) other;
            soak = m11(simpleEnemy);
        }
        return soak;
    }

    private int m11(SimpleEnemy simpleEnemy) {
        return m2(simpleEnemy, simpleEnemy.getArmor());
    }

    private int m2(SimpleEnemy simpleEnemy, Armor armor) {
        List<Buff> buffs = simpleEnemy.getBuffs();
        return Math.round(armor.getDamageSoak() * m3(buffs));
    }

    private float m3(List<Buff> buffs) {
        float sum = 0f;
        for (Buff buff : buffs) {
            sum += buff.soakModifier();
        }
        return sum + 1f;
    }

    private float getDamageModifier() {
        Equipment equipment = this.inventory.getEquipment();
        return m4(equipment);
    }

    private float m4(Equipment equipment) {
        Item leftHand = equipment.getLeftHand();
        Item rightHand = equipment.getRightHand();
        Item head = equipment.getHead();
        Item feet = equipment.getFeet();
        Item chest = equipment.getChest();
        float strengthModifier = stats.getStrength() * 0.1f;
        return strengthModifier + m5(leftHand, rightHand, head, feet, chest);
    }

    private float m5(Item leftHand, Item rightHand, Item head, Item feet, Item chest) {
        return leftHand.getDamageModifier() +
                    rightHand.getDamageModifier() +
                    head.getDamageModifier() +
                    feet.getDamageModifier() +
                    chest.getDamageModifier();
    }

    private int getBaseDamage() {
        Equipment equipment = this.inventory.getEquipment();
        return m6(equipment);
    }

    private int m6(Equipment equipment) {
        Item leftHand = equipment.getLeftHand();
        Item rightHand = equipment.getRightHand();
        Item head = equipment.getHead();
        Item feet = equipment.getFeet();
        Item chest = equipment.getChest();
        return m7(leftHand, rightHand, head, feet, chest);
    }

    private int m7(Item leftHand, Item rightHand, Item head, Item feet, Item chest) {
        return leftHand.getBaseDamage() +
        rightHand.getBaseDamage() +
        head.getBaseDamage() +
        feet.getBaseDamage() +
        chest.getBaseDamage();
    }
}
