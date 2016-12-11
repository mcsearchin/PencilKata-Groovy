package com.mcsearchin.pencil

class Pencil {

    private final int initialPointDurability
    private int remainingPointDurability

    Pencil(int pointDurability) {
        this.initialPointDurability = pointDurability
        this.remainingPointDurability = pointDurability
    }

    def String write(String text) {
        String result = remainingPointDurability >= text.length() ?
                text : text.substring(0, remainingPointDurability).padRight(text.length())
        remainingPointDurability -= Math.min(text.length(), remainingPointDurability)
        result
    }

    def sharpen() {
        remainingPointDurability = initialPointDurability
    }
}
