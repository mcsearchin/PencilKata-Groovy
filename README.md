# Pencil Kata

From Dan Wiebe

The purpose of the Pencil Kata is to write code to simulate, first coarsely and then more faithfully, an ordinary graphite pencil.  This kata was composed by a group of prisoners at the Marion Correctional Institution in Marion, OH as a way to teach test-driven developmentto one another.

1. I want to be able to create a Pencil object that, when instructed to write a string of text, will faithfully return the string it was to write.
2. I want to be able to specify a pointDurability parameter when creating a Pencil object. When pointDurability is low, the Pencil will be able to write only a limited number of characters before it goes dull; after it goes dull, every character it is directed to write will appear as a space.  A Pencil created with a high pointDurability will still go dull, but not as fast.
3. I want to be able to sharpen a Pencil.  When a Pencil is sharpened, it regains its initial pointDurability and can write more characters before it goes dull again.
4. I also want to be able to specify a length parameter when creating a Pencil object. Pencils of short length will only be sharpenable a small number of times.  Once a Pencil has been sharpened that number of times, further sharpening it will no longer restore its pointDurability, and it must be discarded and replaced.  Pencils of great length can be sharpened more times.
5. Writing spaces, tabs, and newlines expends no graphite; therefore these characters should not be considered in pointDurability.
6. In general, writing capital letters expends more graphite than writing lowercase letters. Adjust the Pencil to take this into account.
7. At least for the letters of the alphabet, make a reasonable estimation of the proportionate amount of graphite that is expended to write upper- and lowercase versions of each one, and figure that into the simulation.
8. Come up with your own ideas to make the simulation even more faithful to reality.

Bonus: Using inheritance, composition or some other OO pattern, implement an ink pen.


# An Attempt at User Stories:

## Write
*As a writer  
I want to be able use a pencil to write text on a sheet of paper  
so that I can better remember my thoughts*  

When the pencil is instructed to write a string of text on a sheet of paper, the paper will reflect the text that was written.

Text written by the pencil should always be appended to existing text on the paper. Thus, given a piece of paper with the text "She sells sea shells", when a pencil is instructed to write "&nbsp;down by the sea shore" on the paper, the paper will then contain the entire string.


## Point Degradation
*As a pencil manufacturer  
I want writing to cause a pencil point to go dull  
so that I can sell more pencils*  

When a pencil is created, it can be provided with a value for point durability.  When the point durability is low, the pencil will be able to write only a limited number of characters before it goes dull; after it goes dull, every character it is directed to write will appear as a space.  A pencil created with a high point durability will still go dull, but not as fast.

Writing spaces and newlines expends no graphite; therefore these characters should not affect the pencil point.

Lowercase letters should degrade the pencil point by a value of one, and capital letters should degrade the point by two.  Hence when a pencil with a point durability of four is instructed to write the string "text", the paper will contain the entire string.  But if a pencil with point durability of four is instructed to write the string "Text", the paper will only show "Tex&nbsp;".


## Sharpen
*As a writer  
I want to be able to sharpen my pencil  
so that I can continue to write with it after it goes dull*  

When a pencil is sharpened, it regains its initial point durability and can write more characters before it goes dull again.  Thus, given a pencil created with point durability of 40,000 that has since degraded, when it is sharpened, its point durability will be 40,000 again.

A pencil should also be created with an initial length value. Pencils of short length will only be sharpenable a small number of times while pencils of great length can be sharpened more times.  The pencil's length is reduced by one each time it is sharpened.  When a pencil's length is zero, then sharpening it no longer restores its point durabliity.


## Erase
*As a writer  
I want to be able to erase previously written text  
so that I can remove my mistakes*  

When the pencil is instructed to erase text from the paper, the last occurrence of that text on the paper will be replaced with empty spaces.  

Given a piece of the paper containing the string:  
	"How much wood would a woodchuck chuck if a woodchuck could chuck wood?"  
when the string "chuck" is erased, the paper should read:  
	"How much wood would a woodchuck chuck if a woodchuck could&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;wood?"    
and if the string "chuck" is erased again, the paper should read:  
"How much wood would a woodchuck chuck if a wood&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;could&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;wood?"  


## Eraser Degradation
*As a pencil manufacturer  
I want a pencil eraser to eventually wear out  
so that I can sell more pencils*  

When a pencil is created, it can be provided with a value for eraser durability.  For simplicity, all characters except for white space should degrade the eraser by a value of one.  Text should be erased in the opposite order it was written.  Once the eraser durability is zero, the eraser is worn out and can no longer erase.

Thus if a pencil's eraser has remaining durability of three, and it is instructed to erase the word "Bill" from "Buffalo Bill", then the text remaining on the paper is "Buffalo B&nbsp;&nbsp;&nbsp;".


## Editing
*As a writer  
I want to be able edit previously written text  
so that I can change my writing without starting over*  

Once text has been erased from the paper, a pencil may be instructed to write new text over the resulting white space.  For instance, if the paper contains the text "An&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a day keeps the doctor away", a pencil can can be instructed to write the word "onion" in the white space gap, so the text reads "An onion a day keeps the doctor away".  

Existing text on the page cannot 'shift' to make room for new text.  If the new text is longer than the allocated whitespace and thus would collide with other existing non-whitespace characters on the page, these character collisions should be represented by the "@" character.  For example, writing "artichoke" in the middle of "An&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a day keeps the doctor away" would result in "An artich@k@ay keeps the doctor away".
