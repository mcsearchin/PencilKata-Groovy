package com.mcsearchin.pencil

import spock.lang.Specification
import spock.lang.Unroll

class PencilSpec extends Specification {

    Pencil subject
    Paper paper
    final static LOWERCASE_WORD = 'blah'

    def setup() {
        paper = new Paper()
    }

    def "given the point durability is exactly sufficient, it can write a supplied word"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length())

        when:
        subject.write(LOWERCASE_WORD, paper)

        then:
        LOWERCASE_WORD == paper.text
    }

    def "given the point durability is less than needed for a word, it cannot write the entire supplied word"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length() - 1)

        when:
        subject.write(LOWERCASE_WORD, paper)

        then:
        'bla ' == paper.text
    }

    def "given the point durability is more than sufficient, it can write a supplied word"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length() + 1)

        when:
        subject.write(LOWERCASE_WORD, paper)

        then:
        LOWERCASE_WORD == paper.text
    }

    def "given the point durability is less than needed for writing a word twice, it will go dull during the second writing"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length() * 2 - 2)

        when:
        subject.write(LOWERCASE_WORD, paper)
        subject.write(LOWERCASE_WORD, paper)

        then:
        LOWERCASE_WORD + 'bl  ' == paper.text
    }

    def "when it goes dull while writing the supplied text, then it will write all spaces on subsequent writings"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length() - 1)

        when:
        subject.write(LOWERCASE_WORD, paper)
        subject.write(LOWERCASE_WORD, paper)

        then:
        'bla     ' == paper.text
    }

    def "given it has gone dull, when it is sharpened, then it can write again"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length(), 1)
        subject.write(LOWERCASE_WORD, paper)

        when:
        subject.sharpen()

        then:
        LOWERCASE_WORD == paper.text
    }

    def "given it has gone dull and is too short to be sharpened, when it is sharpened, then the point is not restored"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length(), 0)
        subject.write(LOWERCASE_WORD, paper)

        when:
        subject.sharpen()
        subject.write(LOWERCASE_WORD, paper)

        then:
        LOWERCASE_WORD + '    ' == paper.text
    }

    def "when white space is written, then the point does not degrade"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length())

        when:
        subject.write(' \t\n', paper)
        subject.write(LOWERCASE_WORD, paper)

        then:
        ' \t\n' + LOWERCASE_WORD == paper.text
    }

    def "given the text to be written contains whitespace, then the point only degrades for non-white space characters"() {
        given:
        def text = "\t$LOWERCASE_WORD $LOWERCASE_WORD"
        subject = new Pencil(LOWERCASE_WORD.length() * 2)

        when:
        subject.write(text, paper)

        then:
        text == paper.text
    }

    def "given the text to be written ends with whitespace, then trailing white space characters are preserved"() {
        given:
        def pointDurability = LOWERCASE_WORD.length() - 1
        def text = "$LOWERCASE_WORD\t\n"
        def expected = "${LOWERCASE_WORD.substring(0, pointDurability)} \t\n"
        subject = new Pencil(pointDurability)

        when:
        subject.write(text, paper)

        then:
        expected == paper.text
    }

    def "given the text to be written contains a capital letter, the point will degrade twice as fast for that letter"() {
        given:
        def word = 'In'
        subject = new Pencil(word.length())

        when:
        subject.write(word, paper)

        then:
         'I ' == paper.text
    }

    def "given the letter at the end of the text is capital and the point durability can only handle a lowercase letter, the capital letter will not be written"() {
        given:
        def word = 't4lly--hooO'
        subject = new Pencil(word.length())

        when:
        subject.write(word, paper)

        then:
        't4lly--hoo ' == paper.text
    }

    @Unroll
    def "when an '#character' is written it degrades the point by #pointDegradation"() {
        given:
        subject = new Pencil(pointDegradation)

        when:
        subject.write(character, paper)
        subject.write('i', paper)

        then:
        character + ' ' == paper.text

        where:
        character   || pointDegradation
        'm'         || 2
        'w'         || 2
        'M'         || 3
        'W'         || 3
    }

    def "given the pencil has an eraser and text has been written, when instructed to erase the text, then it leaves whitespace in its place"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length(), 1, new Eraser(LOWERCASE_WORD.length()))
        subject.write(LOWERCASE_WORD, paper)

        when:
        subject.erase(LOWERCASE_WORD, paper)

        then:
        paper.text == '    '
    }

    def "given a pencil has no eraser and text has been written, when instructed to erase the text, then the text is not altered"() {
        given:
        subject = new Pencil(LOWERCASE_WORD.length())
        subject.write(LOWERCASE_WORD, paper)

        when:
        subject.erase(LOWERCASE_WORD, paper)

        then:
        paper.text == LOWERCASE_WORD
    }

    def "given text has been written, when instructed to write text in the middle of the existing text, the paper reflects the change"() {
        given:
        subject = new Pencil(1000)
        subject.write('An       a day keeps the doctor away', paper)

        when:
        subject.write('artichoke', 3, paper)

        then:
        paper.text == 'An artich@k@ay keeps the doctor away'
    }

    def "given text has been written, when instructed to write text that starts in the middle but extends past the end of the existing text, the paper reflects tha change"() {
        given:
        subject = new Pencil(1000)
        subject.write('Finish this sent    ', paper)

        when:
        subject.write('iment.', 16, paper)

        then:
        paper.text == 'Finish this sentiment.'
    }
}
