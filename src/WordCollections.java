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

    /**
     * @param fileName name of the txt file
     */
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
        String str = dictionary.get(randomIndex);
        for(int i = 0; i < guess.length; i++)
        {
            guess[i] = str.substring(i, i+1);
        }
        return guess;  // write a method to put out a random five-letter word inside dictionary ArrayList.
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
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
                dictionary.add(line);
            }

            System.out.println("\nFile imported successfully!");
        }
        catch (IOException e)
        {
            System.out.println("Error importing file; unable to access "+ e.getMessage());
        }


    }

}
