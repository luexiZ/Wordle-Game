public class Board
{
    private String[][] grid;

    public Board()
    {
        grid = new String[5][5];
        clearGrid();
    }


    public void setGrid(String[] array, int row)
    {
            for(int i = 0; i < grid[row].length; i++)
            {
                grid[row][i] = array[i];
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
