package com.mcsearchin.pencil

class Pencil {

    private static final int STANDARD_LETTER_DEGRADATION = 1
    private static final int CAPITAL_LETTER_DEGRADATION = 2
    private static final char[] CHARACTERS_THAT_DEGRADE_POINT_BY_ONE_MORE = ['m', 'w', 'M', 'W']

    private final int initialPointDurability
    private int remainingPointDurability
    private int length

    Pencil(int pointDurability, int length = 0) {
        this.initialPointDurability = pointDurability
        this.remainingPointDurability = pointDurability
        this.length = length
    }

    def String write(String text, Paper paper) {
        StringBuilder result = new StringBuilder(text.length())
        text.toCharArray().each {
            result.append(convertAndDegradePoint(it))
        }

        paper.write(result.toString())
    }

    private char convertAndDegradePoint(char character) {
        char convertedChar = character
        if (!character.isWhitespace()) {
            def pointDegradation = getPointDegradation(character)
            if (remainingPointDurability >= pointDegradation) {
                remainingPointDurability -= pointDegradation;
            } else {
                convertedChar = ' '
            }
        }
        convertedChar
    }

    private int getPointDegradation(char character) {
        def pointDegradation = character.isUpperCase() ? CAPITAL_LETTER_DEGRADATION : STANDARD_LETTER_DEGRADATION
        if (CHARACTERS_THAT_DEGRADE_POINT_BY_ONE_MORE.contains(character)) {
            pointDegradation += 1
        }
        pointDegradation
    }

    def sharpen() {
        if (length > 0) {
            remainingPointDurability = initialPointDurability
            length--
        }
    }
}
