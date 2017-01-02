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

Text written by the pencil should always be appended to existing text on the paper.

## Point Degradation
*As a pencil manufacturer  
I want writing to cause pencil points to go dull  
so that I can sell more pencils*  

When a pencil is created, it can be provided with a value for point durability.  When the point durability is low, the pencil will be able to write only a limited number of characters before it goes dull; after it goes dull, every character it is directed to write will appear as a space.  A pencil created with a high point durability will still go dull, but not as fast.

Writing spaces, tabs, and newlines expends no graphite; therefore these characters should not affect the pencil point.

In general, writing capital letters expends more graphite than writing lowercase letters. The pencil should take this into account.

## Sharpen
*As a writer  
I want to be able to sharpen my pencil  
so that I can continue to write with it after it goes dull*  

When a pencil is sharpened, it regains its initial point durability and can write more characters before it goes dull again.

## Length
*As a pencil manufacturer  
I want to be able to make pencils in different lengths  
so that I can sell pencils at different price points*  

Pencils of short length will only be sharpenable a small number of times.  Once a pencil has been sharpened that number of times, further sharpening it will no longer restore its point durability, and it must be discarded and replaced.  Pencils of great length can be sharpened more times.

## Erase
*As a writer  
I want to be able to erase previously written text  
so that I can change what I have written without having to start over*  

When the pencil is instructed to erase text, the last occurrence of that text on the paper will be replaced with empty spaces.