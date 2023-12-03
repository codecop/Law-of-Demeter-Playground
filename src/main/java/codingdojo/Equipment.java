package codingdojo;

public class Equipment {
    // TODO add a ring item that may be equipped
    //  that may also add damage modifier
    private final Item leftHand;
    private final Item rightHand;
    private final Item head;
    private final Item feet;
    private final Item chest;

    public Equipment(Item leftHand, Item rightHand, Item head, Item feet, Item chest) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.head = head;
        this.feet = feet;
        this.chest = chest;
    }

    public Item getLeftHand() {
        return leftHand;
    }
    public Item getRightHand() {
        return rightHand;
    }
    public Item getHead() {
        return head;
    }
    public Item getFeet() {
        return feet;
    }
    public Item getChest() {
        return chest;
    }
}
