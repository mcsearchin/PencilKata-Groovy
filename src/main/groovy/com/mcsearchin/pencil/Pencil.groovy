package com.mcsearchin.pencil

class Pencil {

    private int pointDurability

    Pencil(int pointDurability) {
        this.pointDurability = pointDurability
    }

    def String write(String text) {
        String result = pointDurability >= text.length() ? text : text.substring(0, pointDurability).padRight(text.length())
        pointDurability -= Math.min(text.length(), pointDurability)
        result
    }
}
