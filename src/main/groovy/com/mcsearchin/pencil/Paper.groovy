package com.mcsearchin.pencil;

class Paper {

    private String text = ""

    def String getText() {
        text
    }

    def String setText(String text) {}

    def write(char text) {
        this.text += text
    }
}
