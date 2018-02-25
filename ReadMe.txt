Name : Sushant Gupta

The project has 4 files:

1.TrieNode.java which is the data structure class
2.TrieDictionary.java which is the main driving class
3.The input file (input.txt)
4.The output image 

Steps to compile and run in command prompt:
1. Set the "path" for Java-sdk1.8 in Environment variables 
2. compile TrieNode.java using the command : javac TrieNode.java
3. compile TrieDictionary.java using the command : javac TrieDictionary.java

To run:

java TrieDictionary <inputfilepath/input.txt>


_________________________________________________________

Description:

A program to provide dictionary and thesaurus functionality. The dictionary consists of unique words where each word has exactly one meaning. Each word may have any number of synonyms.

The following operations are supported:

1. Add a word and its meaning to the dictionary.
2. Add a synonym to a word in the dictionary.
3. Both the synonym and the word should already be present in the dictionary.
4. Look up the meaning of a word from the dictionary.
5. Look up the synonyms of a word from the dictionary.
6. In addition, synonyms should be considered both commutative and transitive

For clarity:

commutative: associations are reversible; if cold is a synonym of chilly, then chilly is also a synonym of cold

transitive: associations are transferable; if cold is synonymous with chilly and chilly is synonymous with freezing, then cold is synonymous with freezing

IO Format:

The program accepts as an argument the path to a file containing input commands, one per line, as described below.  Output is written to stdout, and only done so on on read operations (lookupWord, lookupSynonyms).

Input: (operation, parameters)

addWord:word1:definition1
addWord:word2:definition2
addWord:word3:definition3
addSynonym:word1:word2
addSynonym:word1:word3
lookupWord:word1
lookupSynonyms:word1

Output: (to stdout)

word1:definition1
word1:word2,word3
