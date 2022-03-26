import java.util.Scanner;
import java.util.Arrays;
public class wordle
{
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    private final WordCollections dictionary;
    private Board grid;
    private Scanner scanner;
    private String[] guessingWordArray;
    private String guessingWord;
    private String correctWord;
    private String choice;
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
        start();
    }

    public void start()
    {
        System.out.println("Welcome to WORDLE, enjoy yourself in the world of words.");
        boolean asking = true;
        int playerNumber = 0;
        while(asking)
        {
            System.out.print("One Player(1) or two Player(2)? ");
            playerNumber = scanner.nextInt();
            scanner.nextLine();
            if(playerNumber == 1 || playerNumber == 2)
            {
                asking = false;
            }
        }
        if(playerNumber == 1)
        {
            System.out.print("What is your name? ");
            String name = scanner.nextLine();
            player1 = new Player(name);
            System.out.println("You're all set!");
        }
        else
        {
            System.out.print("What is player1's name? ");
            String name = scanner.nextLine();
            player1 = new Player(name);
            System.out.print("What is player2's name? ");
            String name2 = scanner.nextLine();
            player2 = new Player(name2);
            System.out.println("You guys are all set!");
        }
        currentPlayer = player1;
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
            System.out.println("(C)hange Player");
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
        else if(choice.equals("c"))
        {
            changePlayer();
        }
        else if(choice.equals("q"))  // quit
        {
            System.out.println("You have a great day! GoodBye!");
        }
        else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
        System.out.println("");
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
            grid.printGrid();
            boolean badLetter = true;
            while(badLetter)  // loop them if they insert symbols or not exact 5-letter word.
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
            fillIntoArray(guessingWord);
            result = check(answer);
            grid.setGrid(guessingWordArray, trial);
            trial++;
            grid.printGrid();
            if(result == 5)
            {
                System.out.println("Congrats! " + currentPlayer.getName() + ", you got it right!");
                isGuessing = false;
                currentPlayer.win();
            }
            else
            {
                if(trial == 5)
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
        String[] copied = new String[5];
        for(int i = 0;  i < guessingWordArray.length; i++)
        {
            copied[i] =guessingWordArray[i];
        }
        for(int i = 0; i < guessingWordArray.length; i++)
        {
            int totalNumOfLetter = 0;
            int letterExisted = 0;
            if (correctWord.contains(guessingWordArray[i]) && original[i].equals(guessingWordArray[i]))  // right letter, right position
            {
                guessingWordArray[i] = GREEN + guessingWordArray[i] + RESET;
                result++;
            }
            else if (correctWord.contains(guessingWordArray[i]) && !original[i].equals(guessingWordArray[i])) // right letter, wrong position
            {
                for (String s : original)
                {
                    if (s.equals(copied[i]))
                    {
                        totalNumOfLetter++;

                    }
                }
                for(int j = 0; j <= i; j++)
                {
                    if(copied[j].equals(copied[i]))
                    {
                        letterExisted++;
                    }
                }

                if(letterExisted > totalNumOfLetter)
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
    private void rules()
    {
        System.out.println("--------Rules--------\n");
        System.out.println("The letter W is in \"WORLD\" and in the correct spot: " + GREEN +"W" + RESET + "ORLD");
        System.out.println("The letter OW is in \"WORLD\" but in the wrong spot: " + YELLOW + "OW" + RESET + "RLD");
        System.out.println("The letter U is not in \"WORLD\" in any spot: WORL" + RED + "U" + RESET);
    }

    private void scores()
    {
        System.out.println();
        System.out.println("Player: " + currentPlayer.getName() );
        System.out.print("Number of question guessed: " + currentPlayer.getScores());
    }

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
