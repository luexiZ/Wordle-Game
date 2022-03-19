public class Board
{
    private String[][] grid;

    public Board()
    {
        grid = new String[5][5];
    }



    public void setGrid(String[] array, String color, int row)
    {
            for(int i = 0; i < grid[i].length; i++)
            {
                grid[row][i] = color + array[i];
            }
    }

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


    public void printGrid()
    {
        int commas = 0;
        for(String[] row: grid)
        {
            System.out.print("[");
            for(String element : row)
            {
                System.out.print(element);
                if(commas < 5)
                {
                    System.out.print(", ");
                    commas++;
                }
            }
            commas = 0;
            System.out.println("]");
            System.out.println();
        }
    }
}
