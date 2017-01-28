package com.mcsearchin.pencil

class Eraser {

    private int durability

    Eraser(int durability) {
        this.durability = durability
    }

    def erase(String text, Paper paper) {
        if (!isWornOut()) {
            def start = paper.text.lastIndexOf(text)
            if (start >= 0) {
                def end = start + text.length()
                (start..<end).each {
                    paper.erase(it)
                }
            }
        }
    }

    private isWornOut() {
        return durability < 1
    }
}
