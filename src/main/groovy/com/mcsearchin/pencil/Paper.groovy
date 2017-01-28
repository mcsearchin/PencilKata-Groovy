package com.mcsearchin.pencil;

class Paper {

    static final char SPACE = ' '
    static final char COLLISION = '@'

    private StringBuilder text = new StringBuilder()

    def String getText() {
        text.toString()
    }

    def write(char character) {
        text.append(character)
    }

    def write(char character, int index) {
        if (index == text.length()) {
            write(character)
        } else {
            setCharacterAt(index, character)
        }
    }

    def erase(int index) {
        setCharacterAt(index, SPACE)
    }

    private setCharacterAt(int index, char character) {
        if (index >= 0 && index < text.length()) {
            char newCharacter = isEitherWhitespace(character, text.charAt(index)) ? character : COLLISION
            text.setCharAt(index, newCharacter)
        }
    }

    private isEitherWhitespace(char newChar, char oldChar) {
        newChar.isWhitespace() || oldChar.isWhitespace()
    }
}
