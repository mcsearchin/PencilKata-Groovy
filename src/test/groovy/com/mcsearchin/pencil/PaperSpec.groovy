package com.mcsearchin.pencil

import spock.lang.Specification

class PaperSpec extends Specification {

    static final char CHARACTER = 'x'

    Paper subject

    def setup() {
        subject = new Paper()
    }

    def "when a character is written it contains the text"() {
        when:
        subject.write(CHARACTER)

        then:
        subject.text == CHARACTER
    }

    def "given text has already been written, when more text is written then it is appended to the existing text"() {
        given:
        subject.write(CHARACTER)

        when:
        subject.write(CHARACTER)

        then:
        subject.text == "$CHARACTER$CHARACTER"
    }

    def "when no text has been written, it contains an empty string"() {
        expect:
        subject.text == ""
    }

    def "given text has been written, when a given index is erased, it is replaced with whitespace"() {
        given:
        subject.write(CHARACTER)
        subject.write(CHARACTER)

        when:
        subject.erase(0)

        then:
        subject.text == " $CHARACTER"
    }

    def "given text has been written, when an out of range index is erased, the string is not altered"() {
        given:
        subject.write(CHARACTER)

        when:
        subject.erase(1)

        then:
        subject.text == "$CHARACTER"
    }

    def "when a negative index is erased, the string is not altered"() {
        when:
        subject.erase(-1)

        then:
        subject.text == ""
    }

    def "when there is whitespace in the text, text can be written over it"() {
        given:
        subject.write(CHARACTER)
        subject.write(Paper.SPACE)
        subject.write(CHARACTER)

        when:
        subject.write(CHARACTER, 1)

        then:
        subject.text == "$CHARACTER$CHARACTER$CHARACTER"
    }

    def "when instructed to write at an out of range index, the string is not altered"() {
        given:
        subject.write(CHARACTER)

        when:
        subject.write(CHARACTER, 1)

        then:
        subject.text == "$CHARACTER"
    }

    def "when instructed to write at an index, that is already occupied by a non-white space character, the resulting character is '@'"() {
        given:
        subject.write(CHARACTER)
        subject.write(CHARACTER)
        subject.write(CHARACTER)

        when:
        subject.write(CHARACTER, 1)

        then:
        subject.text == "$CHARACTER@$CHARACTER"
    }
}
