The application applies two algorithms to encrypt/decrypt a line: shifting algorithm and algorithm that based on a table of Unicode. 
A user enters input as a command line to run a Java application. Application can read data from the console and a file and prints encrypted/decrypted data to a file and the console as well.

Example 1: java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5 -alg unicode
-mode: enc(encryption) and dec(decryption); -in: reads data from a file; -out: prints data to a file; -key: key of enc/dec -alg: unicode or shift

Example 2: java Main -key 5 -alg unicode -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec
In this example a line is readed after -data and printed to the console.

Additional examples:

![demonstration-1](https://user-images.githubusercontent.com/51421459/74585529-7c090c80-4fee-11ea-870e-c00c58e83d25.gif)
