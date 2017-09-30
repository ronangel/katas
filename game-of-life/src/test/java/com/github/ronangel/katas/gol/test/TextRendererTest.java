package com.github.ronangel.katas.gol.test;

import com.github.ronangel.katas.gol.core.Cell;
import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import com.github.ronangel.katas.gol.core.rendering.TextRenderer;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class TextRendererTest {
    private ByteArrayOutputStream outputStream;
    private GridRenderer gridRenderer;
    private Grid grid;

    @Before
    public void setup() throws Exception {
        this.outputStream = new ByteArrayOutputStream();
        this.gridRenderer = new TextRenderer(outputStream);
        this.grid = new Grid(10, 10);
    }

    @Test
    public void shouldRenderAllSpaces() throws Exception {
        final String expected =
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n";

        gridRenderer.render(grid);

        String result = new String(outputStream.toByteArray());

        assertEquals(expected, result);
    }

    @Test
    public void shouldRenderOneRowCorrectly() throws Exception {
        final String expected = "..X......\n";

        grid = new Grid(9, 1);
        grid.setCell(Cell.ALIVE, new CellLocation(2, 0));

        gridRenderer.render(grid);

        String result = new String(outputStream.toByteArray());

        assertEquals(expected, result);
    }

    @Test
    public void shouldRenderOneCellAlive() throws Exception {
        final String expected =
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        ".....X....\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n";

        grid.setCell(Cell.ALIVE, new CellLocation(5, 5));

        gridRenderer.render(grid);

        String result = new String(outputStream.toByteArray());

        assertEquals(expected, result);
    }

    @Test
    public void shouldRenderOneCellInFirstRowAlive() throws Exception {
        final String expected =
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "...X......\n";

        grid.setCell(Cell.ALIVE, new CellLocation(3, 0));

        gridRenderer.render(grid);

        String result = new String(outputStream.toByteArray());

        assertEquals(expected, result);
    }

    @Test
    public void shouldRenderOneCellInLastRowAlive() throws Exception {
        final String expected =
                        ".........X\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n" +
                        "..........\n";

        grid.setCell(Cell.ALIVE, new CellLocation(9, 9));

        gridRenderer.render(grid);

        String result = new String(outputStream.toByteArray());

        assertEquals(expected, result);
    }
}
