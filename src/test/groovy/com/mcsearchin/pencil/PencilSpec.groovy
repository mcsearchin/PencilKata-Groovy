package com.mcsearchin.pencil

import spock.lang.Specification
import spock.lang.Unroll

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

    def "when white space is written, then the point does not degrade"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length())

        when:
        subject.write(' \t\n')

        then:
        subject.write(LOWERCASE_WORD) == LOWERCASE_WORD
    }

    def "given the text to be written contains whitespace, then the point only degrades for non-white space characters"() {
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

    def "given the text to be written contains a capital letter, the point will degrade twice as fast for that letter"() {
        given:
        def word = 'In'
        subject = new Pencil(word.length())

        expect:
        subject.write(word) == 'I '
    }

    def "given the letter at the end of the text is capital and the point durability can only handle a lowercase letter, the capital letter will not be written"() {
        given:
        def word = 't4lly--hooO'
        subject = new Pencil(word.length())

        expect:
        subject.write(word) == 't4lly--hoo '
    }

    @Unroll
    def "when an '#character' is written it degrades the point by #pointDegradation"() {
        given:
        subject = new Pencil(pointDegradation)

        when:
        def result = subject.write(character)

        then:
        result == character

        and:
        subject.write('i') == ' '

        where:
        character   || pointDegradation
        'm'         || 2
        'w'         || 2
        'M'         || 3
        'W'         || 3
    }
}
