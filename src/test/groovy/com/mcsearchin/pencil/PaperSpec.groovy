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

    def "text cannot be altered directly"() {
        given:
        subject.text = 'text'

        expect:
        subject.text == ""
    }
}
