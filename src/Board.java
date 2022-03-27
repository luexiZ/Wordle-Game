public class Board
{
    private String[][] grid;

    public Board()
    {
        grid = new String[5][5];
        clearGrid();
    }

    /**
     * @param array user's modified array with colors
     * @param row the number of tries the player had
     */
    public void setGrid(String[] array, int row)
    {
            for(int i = 0; i < grid[row].length; i++)
            {
                grid[row][i] = array[i];
            }
    }
    // set every element of the grid back into an empty string value
    public void clearGrid()
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                grid[i][j] = "";
            }
        }
    }

    /* prints the grid in the following format :   [ , , , , ]
                                                   [ , , , , ]
                                                   [ , , , , ]
                                                   [ , , , , ]
                                                   [ , , , , ]
     */
    public void printGrid()
    {
        int commas = 0;
        for(String[] row: grid)
        {
            System.out.print("[");
            for(String element : row)
            {
                System.out.print(element);
                if(commas < 4)
                {
                    System.out.print(", ");
                    commas++;
                }
            }
            commas = 0;
            System.out.println("]");

        }
    }
}
