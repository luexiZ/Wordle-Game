import java.util.Scanner;
import java.util.Arrays;
public class wordle
{
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    private WordCollections dictionary;
    private Board grid;
    private Scanner scanner;
    private String[] guessingWordArray;
    private String guessingWord;
    private String correctWord;
    private String choice;
    private int scores;

    public wordle()
    {
        scanner = new Scanner(System.in);
        dictionary = new WordCollections("src\\word.txt");
        grid = new Board();
        choice = "";
        scores = 0;
        guessingWord = "";
        correctWord = "";
        guessingWordArray = new String[5];
        start();
    }

    public void start()
    {
        System.out.println("Welcome to WORDLE, enjoy yourself in the world of words.");
        mainMenu();
    }


    public void mainMenu()
    {
        while (! (choice.equals("Q") || choice.equals("q")))
        {
            System.out.println("-- GAME MENU --");
            System.out.println("(P)lay");
            System.out.println("(R)ules for the game");
            System.out.println("(S)cores");
            System.out.println("(Q)uit");
            System.out.print("Select an option: ");
            choice = scanner.nextLine();
            processChoice(choice);
        }
    }

    public void processChoice(String choice)
    {
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
        else if(choice.equals("q"))  // quit
        {
            System.out.println("You have a great day! GoodBye!");
        }
        else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
        System.out.println("\n");
        waitNow();
    }

    private void play()
    {
        int trial = 0;
        boolean isGuessing = true;
        int result = 0;
        String[] answer = dictionary.getRandomWord();
        for(int i = 0; i < answer.length; i++)
        {
            correctWord += answer[i];
        }

        while(trial < 5 && isGuessing)
        {
            boolean badLetter = true;
            while(badLetter)  // loop them if they insert symbols or not exact 5-letter word.
            {
                grid.printGrid();
                System.out.print("Enter your guess : ");
                guessingWord = scanner.nextLine();
                if(guessingWord.length() == 5 && guessingWord.matches("[a-zA-Z]+"))
                {
                    badLetter = false;
                }
                else if (guessingWord.length() != 5)
                {
                    System.out.println("Five Letter word only!");
                }
                else
                {
                    System.out.println("No Numbers or Symbol");
                }
            }
            fillIntoArray(guessingWord);
            result = check(answer);
            grid.setGrid(guessingWordArray, trial);
            trial++;
            grid.printGrid();
            if(result == 5)
            {
                System.out.println("You got it right!");
                isGuessing = false;
            }
            else
            {
                if(trial == 4)
                {
                    System.out.println("Correct Answer: " + correctWord );
                }
                else
                {
                    System.out.println("Nice Try");
                }
            }
        }
        // set the grid and correctAnswer back to default
        grid.clearGrid();
        correctWord = "";
    }

    // stuff the user's guess into the guessingWordArray
    private void fillIntoArray(String guess)
    {
        for(int i = 0; i < guessingWordArray.length; i++)
        {
            guessingWordArray[i] = guess.substring(i, i+1);
        }
    }

    // Two methods used in play() to set the board after user enter an input
    public int check(String[] original)
    {
        int result = 0;
        for(int i = 0; i < guessingWordArray.length; i++)
        {
            if (correctWord.contains(guessingWordArray[i]) && original[i].equals(guessingWordArray[i]))  // right letter, right position
            {
                guessingWordArray[i] = GREEN + guessingWordArray[i] + RESET;
                result++;
            }
            else if (correctWord.contains(guessingWordArray[i]) && !original[i].equals(guessingWordArray[i])) // right letter, wrong position
            {
                guessingWordArray[i] = YELLOW + guessingWordArray[i] + RESET;
            }
            else   // wrong letter, wrong position
            {
                guessingWordArray[i] = RED + guessingWordArray[i] + RESET;
            }
        }
        return result;  // if return 5, meaning the user guessed every single letter correctly!
    }
    private void rules()
    {
        System.out.println("--------Rules--------\n");
        System.out.println("The letter W is in \"WORLD\" and in the correct spot: " + GREEN +"W" + RESET + "ORLD");
        System.out.println("The letter OW is in \"WORLD\" but in the wrong spot: " + YELLOW + "OW" + RESET + "RLD");
        System.out.println("The letter U is not in \"WORLD\" in any spot: WORL" + RED + "U" + RESET);
    }

    private void scores()
    {
        System.out.println("Number of question guessed: " + scores);
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
