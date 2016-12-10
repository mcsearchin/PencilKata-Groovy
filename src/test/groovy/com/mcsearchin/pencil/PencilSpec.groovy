package com.mcsearchin.pencil

import spock.lang.Specification

class PencilSpec extends Specification {

    def subject
    final static LOWERCASE_TEXT = 'text'

    def "given the point durability is sufficient, it can write a the supplied text"() {
        given:
        subject = new Pencil(LOWERCASE_TEXT.length())

        expect:
        subject.write(LOWERCASE_TEXT) == LOWERCASE_TEXT
    }

    def "given the point durability is less than needed for the text, it cannot write the entire supplied text string"() {
        given:
        subject = new Pencil(LOWERCASE_TEXT.length() - 1)

        def result = subject.write(LOWERCASE_TEXT)
        expect:
        result.length() == LOWERCASE_TEXT.length()
        result == 'tex '
    }
}
