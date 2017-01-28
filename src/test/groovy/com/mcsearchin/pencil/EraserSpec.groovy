package com.mcsearchin.pencil

import spock.lang.Specification

class EraserSpec extends Specification {

    Eraser subject
    Paper paper

    def setup() {
        paper = new Paper()
    }

    public "when it is instructed to erase text, the text is replaced with whitespace"() {
        given:
        subject = new Eraser()
        def text = 'xy'
        write(text, paper)

        when:
        subject.erase(text, paper)

        then:
        paper.text == '  '
    }

    private write(String text, Paper paper) {
        text.each {
            paper.write((char) it)
        }
    }
}
