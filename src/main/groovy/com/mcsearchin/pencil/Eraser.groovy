package com.mcsearchin.pencil

class Eraser {

    def erase(String text, Paper paper) {
        def start = paper.text.lastIndexOf(text)
        if (start >= 0) {
            def end = start + text.length()
            (start..<end).each {
                paper.erase(it)
            }
        }
    }
}
