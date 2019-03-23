import static org.junit.Assert.assertEquals;

import org.junit.Test;

import carrom.game.CarromBoard;
import carrom.game.utils.Player;

public class CarromGameTest {
	
	@Test
	public void testProcessGame() {
		Player pl = new Player("Vasu",11);
		CarromBoard cb = new CarromBoard(pl,pl);
		cb.processGame(1, pl);
		
		assertEquals(cb.getBlack(),8);
		assertEquals(pl.getScore(),1);
	}
}
