import static org.junit.Assert.assertEquals;

import org.junit.Test;

import carrom.game.CarromBoard;
import carrom.game.utils.Player;
import static carrom.game.utils.CarromGameConstants.*;

public class CarromGameTest {
	private CarromBoard cb;
		
	@Test
	public void testProcessGame() {
		Player pl = new Player("Vasu",11);
		cb = new CarromBoard(pl,pl);
		cb.processGame(1, pl);
		
		assertEquals(cb.getBlack(),8);
		assertEquals(pl.getScore(),1);
	}
	
	@Test
	public void testCheckThreeFoul() {
		Player pl = new Player("Vasu",11);
		pl.setMove(STRIKER_STRIKE);
		pl.setMove(DEFUNCT_COIN);
		pl.setMove(STRIKER_STRIKE);
		
		cb = new CarromBoard(pl,pl);
		
		assertEquals(cb.checkThreeFoul(pl),true);
	}
}
