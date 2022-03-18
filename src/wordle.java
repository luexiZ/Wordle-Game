import java.util.Scanner;
public class wordle
{
    private WordCollections dictionary;
    private Board grid;
    private Scanner scanner;
    private String choice;

    public wordle()
    {
        scanner = new Scanner(System.in);
        dictionary = new WordCollections("word.txt");
        grid = new Board();
        choice = "";
        start();
    }

    public void start()
    {
        // introduct the player, tell them how to play(TO guess), they can click rules if they want to ..
        mainMenu();
    }


    public void mainMenu()
    {

        clear();

        while (! (choice.equals("Q") || choice.equals("q")))
        {
            System.out.println("-- GAME MENU --");
            System.out.println("1. Play");
            System.out.println("2. Top score for this game");
            System.out.println("3. Top score across games");
            System.out.println("4. Play again");
            System.out.println("5. Quit");

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
