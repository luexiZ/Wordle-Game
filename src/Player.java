public class Player
{
    private int scores;
    private String name;

    public Player(String name)
    {
        this.name = name;
    }

    public void win() {
        scores++;
    }

    public int getScores() {
        return scores;
    }

    public String getName() {
        return name;
    }
}
