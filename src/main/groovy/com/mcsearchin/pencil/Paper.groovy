package com.mcsearchin.pencil;

class Paper {

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
            erasable.setCharAt(index, (char) ' ')
            text = erasable.toString()
        }
    }
}
