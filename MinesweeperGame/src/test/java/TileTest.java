import org.OOP.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;      //Testing imports


public class TileTest {
    @Test
    public void testConstructor(){ //Testing
        Tile TestTile = new Tile();
        Assertions.assertEquals(false,TestTile.getRevealed(), "TestTile started off revealed");


    }



}
