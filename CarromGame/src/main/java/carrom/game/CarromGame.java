package carrom.game;

import java.util.Scanner;

import carrom.game.utils.Player;

public class CarromGame {
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter Player 1 name : ");
		String name = scan.next();
		Player player1 = new Player(name,1);
		System.out.println("Please enter Player 2 name : ");
		name = scan.next();
		Player player2 = new Player(name,2);
		
		CarromBoard board = new CarromBoard(player1,player2);
		board.startGame();
		
		System.out.println(board.gameResult() + "\n");
		board.getStatistics();

	}

}
