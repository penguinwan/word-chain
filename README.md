### updated 14 February 2018
This is not a valid word chain solution, still, I want to put it in github for keeping record for myself.

### A litte background of this program
I was asked to write a program to test 2 words to check if they are word chain. First thing I did was googled around to understand what is word chain, [wiki](https://en.wikipedia.org/wiki/Word_chain) has a completely different definition than the one I received in the assignment. Add on to that, there are many different word chain game rules in the world, myself was extremely confused at the beginning when given the problem statement written in poor description. And then I came about this [online game](http://www.richardjames.org.uk/hchlogic/wordchain.htm), and "OIC!!!" I thought I know enough about word chain so I created this program based on my own understanding.

### Aftermath
Of course later I found out in a less pleasant way that this program was wrong, and I still did not understand WTH is word chain. Then again I went to my best friend google, and this time [codekata](http://codekata.com/kata/kata19-word-chains/) came out in the result list. The description in codekata, especially the example of "ruby" and "code" cleared a lot of my doubts and prove that my program is wrong. And this is my real "OIC!!!" moment.

# Introduction
This is a word chain tester. Given 2 words, this tester will determine whether these two words can be chained.

# How To Build
1. Install maven version higher than 3.0
1. Open a terminal >> type `mvn install <enter>`
1. `word-chain-1.0-SNAPSHOT.jar` will be created in target folder

# How to Run
1. Open a terminal >> type `java -jar {location of word-chain-1.0-SNAPSHOT.jar} <enter>`
1. You will be prompted to key in the location of a dictionary file. Example: `/Users/superuser/Documents/dictionary.txt`
1. You will be asked to input word chain via terminal, or file
    1. For terminal input, type `1 <enter>`
        1. You will be prompted to key in the first word, type `{first word} <enter>`
        1. You will be prompted to key in the second word, type `{second word} <enter>`
        1. You will be asked if there is any more word, type `yes<enter>` if there is more, type `no<enter>` if there is none
    1. For file input, type `2 <enter>`
        1. You will be prompted to key in the location of input file. Example: `/Users/superuser/Documents/input.txt`. The content of input file must be whitespace delimited pairs of word. Example:
            ```
            cat dog
            kilt pill
            kind pull
            ```
1. Test result will be shown on screen. 
    1. `YES {word chain}` - the input is a word chain
    1. `NO` - the input is not a word chain