package com.github.ronangel.katas.gol.core;

import java.util.ArrayList;
import java.util.List;

public class NeighborLocator
{
    public List<CellLocation> locateNeighbors(CellLocation location)
    {
        List<CellLocation> locations = new ArrayList<>(8);

        locations.add(CellLocation.get(location.getX() - 1, location.getY())); // left
        locations.add(CellLocation.get(location.getX() + 1, location.getY())); // right
        locations.add(CellLocation.get(location.getX(), location.getY() + 1)); // above
        locations.add(CellLocation.get(location.getX(), location.getY() - 1)); // below

        locations.add(CellLocation.get(location.getX() - 1, location.getY() + 1)); // top-left corner
        locations.add(CellLocation.get(location.getX() + 1, location.getY() + 1)); // top-right corner
        locations.add(CellLocation.get(location.getX() + 1, location.getY() - 1)); // bottom-right corner
        locations.add(CellLocation.get(location.getX() - 1, location.getY() - 1)); // bottom-left corner

        return locations;
    }
}
