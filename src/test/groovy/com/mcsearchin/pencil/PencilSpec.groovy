package com.mcsearchin.pencil

import spock.lang.Specification

class PencilSpec extends Specification {

    Pencil subject
    final static LOWERCASE_WORD = 'word'

    def "given the point durability is exactly sufficient, it can write a supplied word"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length())

        expect:
        subject.write(LOWERCASE_WORD) == LOWERCASE_WORD
    }

    def "given the point durability is less than needed for a word, it cannot write the entire supplied word"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length() - 1)

        expect:
        subject.write(LOWERCASE_WORD) == 'wor '
    }

    def "given the point durability is more than sufficient, it can write a supplied word"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length() + 1)

        expect:
        subject.write(LOWERCASE_WORD) == LOWERCASE_WORD
    }

    def "given the point durability is less than needed for writing a word twice, it will go dull during the second writing"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length() * 2 - 2)

        when:
        def firstResult = subject.write(LOWERCASE_WORD)
        def secondResult = subject.write(LOWERCASE_WORD)

        then:
        firstResult == LOWERCASE_WORD
        secondResult == 'wo  '
    }
}
