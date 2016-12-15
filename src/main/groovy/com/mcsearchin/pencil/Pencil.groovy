package com.mcsearchin.pencil

class Pencil {

    private static final int STANDARD_LETTER_DEGRADATION = 1
    private static final int CAPITAL_LETTER_DEGRADATION = 2

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
            def pointDegradation = character.isUpperCase() ? CAPITAL_LETTER_DEGRADATION : STANDARD_LETTER_DEGRADATION
            if (remainingPointDurability >= pointDegradation) {
                remainingPointDurability -= pointDegradation;
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
