package com.github.s0uldsilence.wuxia.item.custom;

public enum ItemRarity {
    COMMON("Ordinary"),
    UNCOMMON("Refined"),
    RARE("Precious"),
    EPIC("Peerless"),
    LEGENDARY("Mythical");

    private final String name;

    ItemRarity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}