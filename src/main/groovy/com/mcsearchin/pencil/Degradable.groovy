package com.mcsearchin.pencil

abstract class Degradable {
    protected static final DEFAULT_DEGRADATION = 1

    protected int durability

    protected void degrade() {
        degradeBy(DEFAULT_DEGRADATION)
    }

    protected void degradeBy(int value) {
        durability -= value
    }

    protected boolean isWornOut() {
        !canHandle(DEFAULT_DEGRADATION)
    }

    protected boolean canHandle(int value) {
        durability >= value
    }
}