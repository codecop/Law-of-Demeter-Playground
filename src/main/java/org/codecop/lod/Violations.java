package org.codecop.lod;

public class Violations {

    void usesEnums() {
        Values one = Values.ONE;
        System.out.println(one);
        System.out.println(one.name()); // 1 LoD violation "object not created locally"
        System.out.println(one.name().length()); // 3 LoD violations
    }

}
