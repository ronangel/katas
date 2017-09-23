public class Cell
{
    private boolean isAlive;

    public Cell(boolean isAlive)
    {
        this.isAlive = isAlive;
    }

    public void nextTurn(int numNeighbors)
    {
        if (numNeighbors < 2)
        {
            this.isAlive = false;
        }
        else if (numNeighbors > 3)
        {
            this.isAlive = false;
        }
        else if (numNeighbors == 3)
        {
            this.isAlive = true;
        }
    }

    public boolean isAlive()
    {
        return isAlive;
    }
}
