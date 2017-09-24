public class Cell
{
    public static Cell ALIVE = new Cell(true);
    public static Cell DEAD = new Cell(false);

    private final boolean isAlive;

    private Cell(boolean isAlive)
    {
        this.isAlive = isAlive;
    }

    public Cell nextTurn(int numAliveNeighbors)
    {
        boolean isAlive;

        if (numAliveNeighbors < 2)
        {
            isAlive = false;
        }
        else if (numAliveNeighbors > 3)
        {
            isAlive = false;
        }
        else //if (numAliveNeighbors == 3)
        {
            isAlive = true;
        }

        if (isAlive)
            return Cell.ALIVE;

        return Cell.DEAD;
    }

    public boolean isAlive()
    {
        return isAlive;
    }
}
