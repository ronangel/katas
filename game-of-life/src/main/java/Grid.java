public class Grid
{

    private final int height;
    private final int width;
    private Cell[][] cells;

    public Grid(int width, int height)
    {
        this.height = height;
        this.width = width;

        cells = new Cell[width][height];
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setCell(Cell cell, CellLocation location)
    {
        int x = location.getX();
        int y = location.getY();

        cells[x][y] = cell;
    }

    public Cell getCell(CellLocation location)
    {
        int x = location.getX();
        int y = location.getY();

        return cells[x][y];
    }

//    public int getNumNeighbors(CellLocation location)
//    {
//
//    }
}
