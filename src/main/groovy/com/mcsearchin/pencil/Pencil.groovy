package com.mcsearchin.pencil

class Pencil extends Degradable {

    private final int initialPointDurability
    private Point point
    private Eraser eraser

    Pencil(int pointDurability, int length = 1, Eraser eraser = null) {
        this.durability = length
        this.initialPointDurability = pointDurability
        this.point = new Point(pointDurability)
        this.eraser = eraser
    }

    def write(String text, Paper paper) {
        text.toCharArray().each {
            paper.write(convertAndDegradePoint(it))
        }
    }

    def write(String text, int index, Paper paper) {
        int characterIndex = index
        text.toCharArray().each {
            paper.write(convertAndDegradePoint(it), characterIndex)
            characterIndex++
        }
    }

    private char convertAndDegradePoint(char character) {
        char convertedChar = character
        if (!character.isWhitespace()) {
            def pointDegradation = getPointDegradation(character)
            if (point.canHandle(pointDegradation)) {
                point.degradeBy(pointDegradation)
            } else {
                convertedChar = Paper.SPACE
            }
        }
        convertedChar
    }

    private int getPointDegradation(char character) {
        def pointDegradation = character.isUpperCase() ? Point.CAPITAL_LETTER_DEGRADATION : Point.STANDARD_LETTER_DEGRADATION
        if (Point.CHARACTERS_THAT_DEGRADE_POINT_BY_ONE_MORE.contains(character)) {
            pointDegradation += 1
        }
        pointDegradation
    }

    def sharpen() {
        if (!isWornOut()) {
            point = new Point(initialPointDurability)
            degrade()
        }
    }

    def erase(String text, Paper paper) {
        if (eraser != null) {
            eraser.erase(text, paper)
        }
    }

    private static class Point extends Degradable {
        private static final int STANDARD_LETTER_DEGRADATION = 1
        private static final int CAPITAL_LETTER_DEGRADATION = 2
        private static final char[] CHARACTERS_THAT_DEGRADE_POINT_BY_ONE_MORE = ['m', 'w', 'M', 'W']

        private Point(int durability) {
            this.durability = durability
        }
    }
}
