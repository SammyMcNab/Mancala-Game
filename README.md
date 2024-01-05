# Project Title

Simple Mancala Game Simulator

## Description

Program simulates the Mancala Board Game. A max of 2 players can play against eachother on this program.
The players can replay for as many rounds as they wish.

## Getting Started

### Dependencies

* The BookTest file requires the following: 
```
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
```

* The MancalaGame file requires:
```
import java.util.ArrayList;
import java.util.Arrays;
```

* The Board file requires:
```
import java.util.ArrayList;
import java.util.Arrays;
```


None

### Executing program

* How to build and run the program
* Step-by-step bullets
* First, gradle build the project
```
oer-GP3$ gradle build
```

* Second, gradle echo to see the executable
```
oer-GP3$ gradle echo

> Task :echo
To run the program from jar:
java -jar build/libs/TextUI.jar
To run the program from class files:
java -cp build/classes/java/main ui.TextUI

BUILD SUCCESSFUL in 860ms
1 actionable task: 1 executed
```

* Finally, run the executable
```
java -cp build/classes/java/main ui.TextUI
```

* The expected output is as follows:
```
Player 1 name:
```
## Limitations

Everything should be in working order, couple of checkstyle errors,
failed junit case for capture stones, even though method works in
running program.

## Author Information

NAME: Samuel McNab  EMAIL: mcnab@uoguelph.ca

## Development History
TALK ABOUT DIFFERENCES!

The basic fundamental methods and classes of the AI solution and mine are near identical.
Although we do take different approaches for the more convoluted methods. For example
in the Board Class, our captureStone and distributeStones have very different approaches. 
Personally I prefer the logic behind the AI solution for both of these methods. It 
is way less suffocating than the rows of code I have written. The AI method of
distributeStones is much quicker and efficient, my code changes the index depending 
on where the stone lands, whereas the AI loops and increments eitherway, only 
incrementing if the index is on a pit. Additionally the captureStones has many less 
lines of code, it is pretty much a refactored version of my implementation.

* 0.3 
    * adding comments
* 0.2 
    * Better text alignment
    * More on the ai review and README
* 0.1
    * Pushed everything 

## Acknowledgments
