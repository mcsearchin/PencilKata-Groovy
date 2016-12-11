package com.mcsearchin.pencil

class Pencil {

    int pointDurability

    Pencil(int pointDurability) {
        this.pointDurability = pointDurability
    }

    def String write(String text) {
        String result = pointDurability >= text.length() ? text : text.substring(0, pointDurability).padRight(text.length())
        pointDurability -= text.length()
        result
    }
}
