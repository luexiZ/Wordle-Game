/**
 * This class represent the game, Wordle
 *
 * @author Luexi(Leo) Zhao
 */

import java.util.Scanner;
import java.util.Arrays;
public class wordle
{
    /** Colors for the letters of the word */
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    /** Objects from other class to retrieve data */
    private final WordCollections dictionary;
    private Board grid;
    /** Scanner to accept user's input*/
    private Scanner scanner;
    /** Both instance variables contain the user's guess, but in different types*/
    private String[] guessingWordArray;
    private String guessingWord;
    /** The correct random word that user should guess on*/
    private String correctWord;
    /** User's menu choice */
    private String choice;
    /** The player objects for corresponding user number*/
    private Player player1;
    private Player player2;
    private Player currentPlayer;



    public wordle()
    {
        scanner = new Scanner(System.in);
        dictionary = new WordCollections("src\\word.txt");
        grid = new Board();
        choice = "";
        guessingWord = "";
        correctWord = "";
        guessingWordArray = new String[5];
        player1 = null;
        player2 = null;
        currentPlayer = null;
    }

    // Begins the program
    public void start()
    {
        System.out.println("Welcome to WORDLE, enjoy yourself in the world of words.");
        boolean asking = true;
        String playerNumber = "";
        while(asking)
        {
            System.out.print("One Player(1) or two Player(2)? ");   // Giving User two choices: Single or double player
            playerNumber = scanner.nextLine();
            if(playerNumber.equals("1") || playerNumber.equals("2"))
            {
                asking = false;
            }
        }
        if(playerNumber.equals("1"))   // creating player1 object
        {
            System.out.print("What is your name? ");
            String name = scanner.nextLine();
            player1 = new Player(name);
            System.out.println("You're all set!\n");
        }
        else         // creating player1 and player2 object
        {
            System.out.print("What is player1's name? ");
            String name = scanner.nextLine();
            player1 = new Player(name);
            System.out.print("What is player2's name? ");
            String name2 = scanner.nextLine();
            player2 = new Player(name2);
            System.out.println("You guys are all set!\n");
        }
        currentPlayer = player1;   // default player
        mainMenu();
    }

    //Display the Overall menu for the player, ends when "q" is entered
    public void mainMenu()
    {
        while (!(choice.equals("q")))
        {
            System.out.println("-- GAME MENU --");
            System.out.println("(P)lay");
            System.out.println("(R)ules for the game");
            System.out.println("(S)cores");
            System.out.println("(C)hange Player");
            System.out.println("(Q)uit");
            System.out.print("Select an option: ");
            choice = scanner.nextLine();
            processChoice(choice);
        }
    }

    /**
     * @param choice User's choice selection above the menu
     */
    public void processChoice(String choice)
    {
        System.out.println();
        choice = choice.toLowerCase();
        if(choice.equals("p"))  // play
        {
            play();
        }
        else if(choice.equals("r"))  // rules
        {
            rules();
        }
        else if(choice.equals("s"))  // scores
        {
            scores();
        }
        else if(choice.equals("c"))  // change player
        {
            changePlayer();
        }
        else if(choice.equals("q"))  // quit
        {
            System.out.println("You have a great day! GoodBye!");
        }
        else   // redirect user to enter the correct letter
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
        System.out.println("");
        waitNow();
    }

    private void play()
    {
        int trial = 1;   // max of 5 trials for the user to guess
        boolean isGuessing = true;
        int result = 0;   // used in line 174
        String[] answer = dictionary.getRandomWord();
        for(int i = 0; i < answer.length; i++)
        {
            correctWord += answer[i];
        }

        while(trial < 6 && isGuessing)  // ends the loop when trials
        {
            grid.printGrid();
            boolean badLetter = true;
            while(badLetter)  // loop them if they insert symbols, not a word in the file, or not exact 5-letter word.
            {
                System.out.print("Enter your guess : ");
                guessingWord = scanner.nextLine();
                guessingWord = guessingWord.toLowerCase();
                boolean isaWord = dictionary.getDictionary().contains(guessingWord);
                if(guessingWord.length() == 5 && guessingWord.matches("[a-zA-Z]+") && isaWord)
                {
                    badLetter = false;
                }
                else if (guessingWord.length() != 5)
                {
                    System.out.println("Five Letter word only!");
                }
                else
                {
                    System.out.println("Not a word");
                }
            }
            fillIntoArray(guessingWord);  // stores the data inside the array
            result = check(answer);  // check() returns a number in line 212
            grid.setGrid(guessingWordArray, trial -1);  // add the user's modified array into the 2D array in the board class
            trial++;
            grid.printGrid();
            if(result == 5)    // user guessed all five letter correctly
            {
                System.out.println("Congrats! " + currentPlayer.getName() + ", you got it right!");
                isGuessing = false;
                currentPlayer.win();
            }
            else  // user didn't correctly guess the entire word.
            {
                if(trial == 6)  // when it's in the sixth trials, guessing should be over and answer should be revealed.
                {
                    System.out.println("Correct Answer: " + correctWord );
                }
                else
                {
                    System.out.println("Nice Try, " + currentPlayer.getName());
                }
            }
            System.out.println();
        }
        // set the grid and correctAnswer back to default
        grid.clearGrid();
        correctWord = "";
    }

    // store the user's guess into the guessingWordArray
    private void fillIntoArray(String guess)
    {
        for(int i = 0; i < guessingWordArray.length; i++)
        {
            guessingWordArray[i] = guess.substring(i, i+1);
        }
    }

    /**
     * @param original array that stores the correct word by letter
     * @return the number of letter guessed correctly
     */
    public int check(String[] original)
    {
        int result = 0;
        // new array to compare its element with "original"; once color changes in "guessingWordArray", comparing the same letter will be false
        String[] copied = new String[5];
        for(int i = 0;  i < guessingWordArray.length; i++)
        {
            copied[i] =guessingWordArray[i];
        }
        // compare guessingWordArray with original
        for(int i = 0; i < guessingWordArray.length; i++)
        {
            int totalNumOfTimes = 0; // the number of times a particular letter should be in the correct word.
            int timesExisted = 0; // the number of times a particular letter exist so far in the guessing word.
            if (correctWord.contains(guessingWordArray[i]) && original[i].equals(guessingWordArray[i]))  // right letter, right position
            {
                guessingWordArray[i] = GREEN + guessingWordArray[i] + RESET;
                result++;
            }
            else if (correctWord.contains(guessingWordArray[i]) && !original[i].equals(guessingWordArray[i])) // right letter, wrong position
            {
                for (String s : original)  // calculate totalNumOfLetter
                {
                    if (s.equals(copied[i]))
                    {
                        totalNumOfTimes++;

                    }
                }
                for(int j = 0; j <= i; j++)  // calculate letterExisted
                {
                    if(copied[j].equals(copied[i]))
                    {
                        timesExisted++;
                    }
                }

                if(timesExisted > totalNumOfTimes)
                {
                    guessingWordArray[i] = RED + guessingWordArray[i] + RESET;
                }
                else
                {
                    guessingWordArray[i] = YELLOW + guessingWordArray[i] + RESET;
                }
            }
            else   // wrong letter, wrong position
            {
                guessingWordArray[i] = RED + guessingWordArray[i] + RESET;
            }
        }
        return result;  // if return 5, meaning the user guessed every single letter correctly!
    }
    // prints out the basic color rules in the game
    private void rules()
    {
        System.out.println("--------Rules--------\n");
        System.out.println("The letter W is in \"WORLD\" and in the correct spot: " + GREEN +"W" + RESET + "ORLD");
        System.out.println("The letter OW is in \"WORLD\" but in the wrong spot: " + YELLOW + "OW" + RESET + "RLD");
        System.out.println("The letter U is not in \"WORLD\" in any spot: WORL" + RED + "U" + RESET);
    }
    // prints the currentPlayer's scores
    private void scores()
    {
        System.out.println("Player: " + currentPlayer.getName() );
        System.out.print("Number of question guessed: " + currentPlayer.getScores());
    }

    //switch player's turn if it's in a two player mode
    public void changePlayer()
    {
        if(player2 == null)
        {
            System.out.println("Invalid Option! You only selected one person mode.");
        }
        else
        {
            if(currentPlayer == player1)
            {
                currentPlayer = player2;
                System.out.println("Player2 is now in control.");
            }
            else
            {
                currentPlayer = player1;
                System.out.println("Player1 is now in control.");
            }
        }
    }



    private void waitNow() // this holds the text for 1 second before the clear() executes. Thus, no need to code scanner.nextLine()
    {
        try
        {
            Thread.sleep(2000); // 1000 milliseconds is one second.
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
