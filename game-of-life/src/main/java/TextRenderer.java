import java.io.OutputStream;

public class TextRenderer implements GridRenderer
{

    private final OutputStream outputStream;

    public TextRenderer(OutputStream outputStream)
    {
        this.outputStream = outputStream;
    }

    @Override
    public void render(Grid grid) throws Exception
    {
        for (int row = grid.getHeight() - 1; row >= 0; row--)
        {
            for (int col = 0; col < grid.getWidth(); col++)
            {
                char c = grid.getCell(new CellLocation(col, row)).isAlive() ? 'X' : '.';

                outputStream.write(c);
            }

            outputStream.write('\n');
        }

        outputStream.flush();
    }
}
