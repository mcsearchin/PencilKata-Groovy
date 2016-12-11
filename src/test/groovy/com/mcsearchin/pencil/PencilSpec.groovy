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

    def "when it goes dull while writing the supplied text, then it will write all spaces on subsequent writings"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length() - 1)

        when:
        def firstResult = subject.write(LOWERCASE_WORD)
        def secondResult = subject.write(LOWERCASE_WORD)

        then:
        firstResult == 'wor '
        secondResult == '    '
    }

    def "given it has gone dull, when it is sharpened, then it can write again"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length(), 1)
        subject.write(LOWERCASE_WORD)

        when:
        subject.sharpen()

        then:
        subject.write(LOWERCASE_WORD) == LOWERCASE_WORD
    }

    def "given it has gone dull and is too short to be sharpened, when it is sharpened, then the point is not restored"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length(), 0)
        subject.write(LOWERCASE_WORD)

        when:
        subject.sharpen()

        then:
        subject.write(LOWERCASE_WORD) == '    '
    }

    def "when white space is written, then the point durability does not decrease"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length())

        when:
        subject.write(' \t\n')

        then:
        subject.write(LOWERCASE_WORD) == LOWERCASE_WORD
    }

    def "given the text to be written contains whitespace, then the point durability only decreases for non-white space characters"() {
        given:
        def text = "\t$LOWERCASE_WORD $LOWERCASE_WORD"
        subject = new Pencil(LOWERCASE_WORD.length() * 2)

        expect:
        subject.write(text) == text
    }

    def "given the text to be written ends with whitespace, then trailing white space characters are preserved"() {
        given:
        def pointDurability = LOWERCASE_WORD.length() - 1
        def text = "$LOWERCASE_WORD\t\n"
        def expected = "${LOWERCASE_WORD.substring(0, pointDurability)} \t\n"
        subject = new Pencil(pointDurability)

        expect:
        subject.write(text) == expected
    }
}
