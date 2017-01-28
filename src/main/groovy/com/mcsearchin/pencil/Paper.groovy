package com.mcsearchin.pencil;

class Paper {

    static final char SPACE = ' '

    private StringBuilder text = new StringBuilder()

    def String getText() {
        text.toString()
    }

    def String setText(String text) {}

    def write(char character) {
        text.append(character)
    }

    def write(char character, int index) {
        setCharacterAt(index, character)
    }

    def erase(int index) {
        setCharacterAt(index, SPACE)
    }

    private setCharacterAt(int index, char character) {
        if (index >= 0 && index < text.length()) {
            text.setCharAt(index, character)
        }
    }
}
