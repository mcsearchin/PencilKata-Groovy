package com.mcsearchin.pencil

class Pencil {

    int pointDurability

    Pencil(int pointDurability) {
        this.pointDurability = pointDurability
    }

    def String write(String text) {
        text.substring(0, pointDurability).padRight(text.length())
    }
}
