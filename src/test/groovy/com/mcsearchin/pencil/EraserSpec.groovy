package com.mcsearchin.pencil

import spock.lang.Specification

class EraserSpec extends Specification {

    Eraser subject
    Paper paper

    def setup() {
        paper = new Paper()
    }

    public "when it is instructed to erase all of text, the text is replaced with whitespace"() {
        given:
        subject = new Eraser()
        def text = 'yo'
        write(text, paper)

        when:
        subject.erase(text, paper)

        then:
        paper.text == '  '
    }

    public "when it is instructed to erase only some of text, the specified text is replaced with whitespace"() {
        given:
        subject = new Eraser()
        write('you', paper)

        when:
        subject.erase('yo', paper)

        then:
        paper.text == '  u'
    }

    public "when it is instructed to erase text not on the paper, the text on the paper is unaltered"() {
        given:
        subject = new Eraser()
        def text = 'yo'
        write(text, paper)

        when:
        subject.erase('ho', paper)

        then:
        paper.text == text
    }

    public "when the text to be erased occurs more than once, only the last occurrence is erased"() {
        given:
        subject = new Eraser()
        write('yo yo yo', paper)

        when:
        subject.erase('yo', paper)

        then:
        paper.text == 'yo yo   '
    }

    private write(String text, Paper paper) {
        text.each {
            paper.write((char) it)
        }
    }
}
