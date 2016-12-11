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
        StringBuilder result = new StringBuilder(text.length())
        text.toCharArray().each {
            result.append(convertAndDegradePoint(it))
        }

        result.toString()
    }

    private char convertAndDegradePoint(char character) {
        char convertedChar = character
        if (!character.isWhitespace()) {
            if (remainingPointDurability > 0) {
                remainingPointDurability--;
            } else {
                convertedChar = ' '
            }
        }
        convertedChar
    }

    def sharpen() {
        if (length > 0) {
            remainingPointDurability = initialPointDurability
            length--
        }
    }
}
