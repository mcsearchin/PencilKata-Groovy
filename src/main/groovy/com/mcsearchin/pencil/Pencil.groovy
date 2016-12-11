package com.mcsearchin.pencil

class Pencil {

    private final int initialPointDurability
    private int remainingPointDurability
    private int length

    Pencil(int pointDurability, int length = 0) {
        this.initialPointDurability = pointDurability
        this.remainingPointDurability = pointDurability
        this.length = length
    }

    def String write(String text) {
        int pointDegradation = text.replaceAll(/\W/, '').length()
        String result = remainingPointDurability >= pointDegradation ?
                text : text.substring(0, remainingPointDurability).padRight(text.length())
        remainingPointDurability -= Math.min(pointDegradation, remainingPointDurability)
        result
    }

    def sharpen() {
        if (length > 0) {
            remainingPointDurability = initialPointDurability
            length--
        }
    }
}
