package com.github.ronangel.katas.gol.ui.rendering;

import com.github.ronangel.katas.gol.core.Cell;
import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasGridRenderer implements GridRenderer
{
    private final Canvas canvas;
    private CellRenderer cellRenderer;

    public CanvasGridRenderer(Canvas canvas)
    {
        this.canvas = canvas;
        this.cellRenderer = new CellRenderer();
    }

    public void setCellRenderer(CellRenderer cellRenderer) {
        this.cellRenderer = cellRenderer;
    }

    @Override
    public void render(Grid grid) throws Exception
    {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        clearCanvas(graphicsContext);
        drawGrid(graphicsContext, grid);
        drawCells(graphicsContext, grid);

    }

    private void clearCanvas(GraphicsContext graphicsContext)
    {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        graphicsContext.clearRect(0, 0, width, height);
    }

    private void drawGrid(GraphicsContext graphicsContext, Grid grid)
    {
        drawBorder(graphicsContext);
        drawGridLines(graphicsContext, grid);
    }

    private void drawBorder(GraphicsContext graphicsContext)
    {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeRect(0,0, width, height);
    }

    private void drawGridLines(GraphicsContext graphicsContext, Grid grid)
    {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        double cellWidth = width / grid.getWidth();
        double cellHeight = height / grid.getHeight();

        // draw horizontal lines
        for (double x = 0; x < width; x += cellWidth)
        {
            graphicsContext.strokeLine(x, 0, x, height);
        }

        // draw vertical lines
        for (double y = 0; y < height; y += cellHeight)
        {
            graphicsContext.strokeLine(0, y, width, y);
        }
    }

    private void drawCells(GraphicsContext graphicsContext, Grid grid)
    {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        double cellWidth = width / grid.getWidth();
        double cellHeight = height / grid.getHeight();

        for (int col = 0; col < grid.getWidth(); col++)
        {
            for (int row = 0; row < grid.getHeight(); row++)
            {
                Cell cell = grid.getCell(CellLocation.get(col, row));

                if (cell.isAlive())
                {
                    cellRenderer.render(graphicsContext, col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                }
            }
        }
    }
}
