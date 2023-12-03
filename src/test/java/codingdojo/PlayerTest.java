package codingdojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void damageCalculations() {
        Inventory inventory = new Inventory(new Equipment( //
                new BasicItem("leftHand", 0, 0), //
                new BasicItem("itemOnRight", 10, 1.0f), //
                new BasicItem("head", 0, 0), //
                new BasicItem("feet", 0, 0), //
                new BasicItem("chest", 0, 0)));
        Stats stats = new Stats(10);
        SimpleEnemy target = new SimpleEnemy(
                // It wears an Armour with a Damage Soak of 5.
                new SimpleArmor(5),
                // It has one Buff with a soakModifier of 1.0 and damage modifier of 1.0.
                Arrays.asList(new BasicBuff(0.5f), new BasicBuff(0.5f)));

        Damage damage = new Player(inventory, stats).calculateDamage(target);

        assertEquals(10, damage.getAmount());
    }
}
