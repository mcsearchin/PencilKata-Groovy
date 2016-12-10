package com.mcsearchin.pencil

import spock.lang.Specification

class PencilSpec extends Specification {

    def "it can write a text string"() {
        def subject = new Pencil()
        def text = "text"
        expect:
        subject.write(text) == text
    }
}
