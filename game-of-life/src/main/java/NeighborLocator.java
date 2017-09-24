import java.util.ArrayList;
import java.util.List;

public class NeighborLocator
{
    public List<CellLocation> locateNeighbors(CellLocation location)
    {
        List<CellLocation> locations = new ArrayList<>(8);

        locations.add(new CellLocation(location.getX() - 1, location.getY())); // left
        locations.add(new CellLocation(location.getX() + 1, location.getY())); // right
        locations.add(new CellLocation(location.getX(), location.getY() + 1)); // above
        locations.add(new CellLocation(location.getX(), location.getY() - 1)); // below

        locations.add(new CellLocation(location.getX() - 1, location.getY() + 1)); // top-left corner
        locations.add(new CellLocation(location.getX() + 1, location.getY() + 1)); // top-right corner
        locations.add(new CellLocation(location.getX() + 1, location.getY() - 1)); // bottom-right corner
        locations.add(new CellLocation(location.getX() - 1, location.getY() - 1)); // bottom-left corner

        return locations;
    }
}
