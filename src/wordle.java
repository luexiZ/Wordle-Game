import java.util.Scanner;
public class wordle
{
    private static final String GREEN = "\u001B[32m";
    private static final String BLACK = "\u001B[30m";
    private static final String YELLOW = "\\u001B[33m\t";
    private WordCollections dictionary;
    private String guessingWord;
    private Board grid;
    private Scanner scanner;
    private String choice;

    public wordle()
    {
        scanner = new Scanner(System.in);
        dictionary = new WordCollections("word.txt");
        grid = new Board();
        choice = "";
        guessingWord = "";
        start();
    }

    public void start()
    {
        System.out.println("Welcome to WORDLE, enjoy yourself in the world of wordes.");
        // introduct the player, tell them how to play(TO guess), they can click rules if they want to ..
        mainMenu();
    }


    public void mainMenu()
    {

        clear();

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

        }
        else if(choice.equals("r"))  // rules
        {

        }
        else if(choice.equals("s"))  // scores
        {

        }
        else if(choice.equals("q"))  // quit
        {

        }
        else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
    }

    private void play()
    {

    }

    private void rules()
    {

    }
    private void scores()
    {

    }




    // Two methods used in play() to set the board after user enter an input
    public int check(int index)
    {
        int result = 0;

        return result;   // used in the nested for loop
    }
    private String color(int result)
    {
        if(result == 1) // wrong letter, wrong position
        {
            return BLACK;
        }
        else if(result == 2) // right letter, wrong position
        {
            return YELLOW;
        }
        else if(result == 3) // right letter, right position
        {
            return GREEN;
        }
        else // default color
        {
            return BLACK;
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



    private void clear() // clearing the console for a better Users experiences.
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
