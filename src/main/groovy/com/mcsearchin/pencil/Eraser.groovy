package com.mcsearchin.pencil

class Eraser {

    def erase(String text, Paper paper) {
        (0..<paper.text.length()).each {
            paper.erase(it)
        }
    }
}
