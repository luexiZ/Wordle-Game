import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordCollections
{

    private ArrayList<String> dictionary;
    private Scanner scanner;
    private String fileNames;

    // constructor; uses try-catch syntax which we haven't discussed!
    public WordCollections(String fileName)
    {
        this.fileNames = fileName;
        dictionary = new ArrayList<String>();
        importDictionary(fileName);
        scanner = new Scanner(System.in);

    }

    public String[] getRandomWord()
    {
        String[] guess = new String[5];
        int randomIndex = (int)(Math.random() * dictionary.size());  // hard code this number to check
//        String str = dictionary.get(randomIndex);
        String str = "hello";
        for(int i = 0; i < guess.length; i++)
        {
            guess[i] = str.substring(i, i+1);
        }
        return guess;  // write a method to put out a random five letter word inside dictionary ArrayList.
    }

    private void importDictionary(String fileName)
    {
        String[] tmp = null;
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;

            while ((line = bufferedReader.readLine()) != null)
            {
//                lines.add(line);
                dictionary.add(line);
            }

//            bufferedReader.close();
//            tmp = lines.toArray(new String[lines.size()]);
            System.out.println("\nFile imported successfully!");
        }
        catch (IOException e)
        {
            System.out.println("Error importing file; unable to access "+ e.getMessage());
        }

//        dictionary = new ArrayList<String>(Arrays.asList(tmp));
    }

}
