package com.mcsearchin.pencil

import spock.lang.Specification

class EraserSpec extends Specification {

    def static final WORD = 'yo'

    Eraser subject
    Paper paper

    def setup() {
        paper = new Paper()
    }

    public "when it is instructed to erase all of text, the text is replaced with whitespace"() {
        given:
        subject = new Eraser(WORD.length())
        write(WORD, paper)

        when:
        subject.erase(WORD, paper)

        then:
        paper.text == '  '
    }

    public "when it is instructed to erase only some of text, the specified text is replaced with whitespace"() {
        given:
        subject = new Eraser(WORD.length())
        write('you', paper)

        when:
        subject.erase(WORD, paper)

        then:
        paper.text == '  u'
    }

    public "when it is instructed to erase text not on the paper, the text on the paper is unaltered"() {
        given:
        subject = new Eraser(WORD.length())
        write(WORD, paper)

        when:
        subject.erase('ho', paper)

        then:
        paper.text == WORD
    }

    public "when the text to be erased occurs more than once, only the last occurrence is erased"() {
        given:
        subject = new Eraser(WORD.length())
        write("$WORD $WORD $WORD", paper)

        when:
        subject.erase(WORD, paper)

        then:
        paper.text == "$WORD $WORD   "
    }

    public "given the eraser is worn out, when it is instructed to erase text, the text is unaltered"() {
        given:
        subject = new Eraser(0)
        write(WORD, paper)

        when:
        subject.erase(WORD, paper)

        then:
        paper.text == WORD
    }

    public "given the eraser durability is less than needed to erase a word, it cannot erase the entire word"() {
        given:
        subject = new Eraser(WORD.length() - 1)
        write(WORD, paper)

        when:
        subject.erase(WORD, paper)

        then:
        paper.text == 'y '
    }

    private write(String text, Paper paper) {
        text.each {
            paper.write((char) it)
        }
    }
}
