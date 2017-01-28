package com.mcsearchin.pencil;

class Paper {

    private static final char SPACE = ' '

    private String text = ""

    def String getText() {
        text
    }

    def String setText(String text) {}

    def write(char character) {
        text += character
    }

    def erase(int index) {
        if (index >= 0 && index < text.length()) {
            def erasable = new StringBuilder(text)
            erasable.setCharAt(index, SPACE)
            text = erasable.toString()
        }
    }
}
