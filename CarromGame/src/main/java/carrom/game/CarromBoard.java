package carrom.game;

import java.util.List;
import java.util.Scanner;

import carrom.game.utils.Player;
import static carrom.game.utils.CarromGameConstants.*;

public class CarromBoard {
	 private static final String WHITE_MULTI_STRIKE = "White MultiStrike";
	private static final String WHITE_STRIKE = "White Strike";
	private Player player1;
	 private Player player2;
	 private int black;
	 private int red;
	 private int white;
	 private boolean game = true;
	 private boolean result = false;
	 private Player winner;
	 
	 public CarromBoard(Player p1, Player p2) {
		 System.out.println("Initializing Clean Strike game with Players : " + p1.getName()
		 + " & " + p2.getName());
		 this.player1 = p1;
		 this.player2 = p2;
		 black = 9;
		 red = 1;
		 white = 5;
	 }
	 
	 public int getBlack() {
			return black;
		}

		public int getRed() {
			return red;
		}
	 
	 public void startGame() {
		 System.out.println("Starting Clean Strike game :");
		 Scanner input = new Scanner(System.in);
		 int option = 0;boolean valid = true;
		 
		 while(game && !result) {
			 if(valid) {
			 System.out.println("Player 1 \"" + player1.getName() + "\" : Choose an outcome from the list below \n1. Strike \n2. Multistrike "
			 		+ "\n3. Red strike \n4. Striker strike \n5. Defunct coin \n6. None \n7. WhiteStrike \n8. White MultiStrike");
			 option = input.nextInt();   // check for valid input range..
			 valid = validateInput(option);
			 if(!valid) {
				 System.out.println("Please enter valid input");
				 valid = true;continue;}
			 
			 processGame(option,player1);
			 
			 checkGame();
			 }
			 
			 if(!game || result)break;
			 
			 valid = true;
			 if(valid) {
			 System.out.println("Player 2 \"" + player2.getName() + "\" : Choose an outcome from the list below \n1. Strike \n2. Multistrike "
				 		+ "\n3. Red strike \n4. Striker strike \n5. Defunct coin \n6. None  \n7. White Strike \n8. White MultiStrike");
			 option = input.nextInt();
			 valid = validateInput(option);
			 if(!valid) {
				 System.out.println("Please enter valid input");
				 continue;
			 }
			 
			 processGame(option,player2);
			 
			 checkGame();
			 }
			 System.out.println("Score board : " + player1.getName() + " - "+ player1.getScore()
			 + " & " + player2.getName() + " - "+ player2.getScore());
		 }
		 
		 System.out.println("Clean Strike game is finished!");
			 
	 }
	 
	 public boolean validateInput(int opt) {
		 if(opt<0 || opt >8)return false;
		 
		 if(black<=0) {
			 if(opt<3)return false;
		 }
		 if(red <=0 && opt==3)return false;
		 
		 if(white<=0) {
			 if(opt>6)return false;
		 }
		 
		 
		 return true;
	 }
	 
	 public void processGame(int opt, Player player) {
		 switch(opt) {
		 case 1 : 
			 player.setScore(1); //simple strike
			 black--;
			 player.setMove(STRIKE);
			 break;
		 case 2 : 
			 player.setScore(2); //multi strike
			 black -= 2;
			 player.setMove(MULTISTRIKE);
			 break;
		 case 3 : 
			 player.setScore(3); //red strike
			 red--;
			 player.setMove(RED_STRIKE);
			 break;
		 case 4 : 
			 player.setScore(-1); //striker
			 player.setMove(STRIKER_STRIKE);
			 if(checkThreeFoul(player))player.setScore(-1);
			 break;
		 case 5 : 
			 player.setScore(-2); //foul strike
			 black--;
			 player.setMove(DEFUNCT_COIN);
			 if(checkThreeFoul(player))player.setScore(-1);
			 break;
		 case 6 : 
			 player.setScore(0); //null strike
			 player.setMove(NONE);
			 break;
		 case 7 : 
			 player.setScore(2); //null strike
			 player.setMove(WHITE_STRIKE);
			 white--;
			 break;
		 case 8 : 
			 player.setScore(4); //null strike
			 player.setMove(WHITE_MULTI_STRIKE);
			 white -= 2;
			 break;
		 default :
			 player.setScore(0);
			 break;
			 
		 }
//		 System.out.println(player + " score : " + player.getScore());
	 }
	 
	 public void checkGame() {
//		 if(black <= 0 && red>0)
//		 System.out.println("Blacks are finsihed, only red strikes are possible..");
		 if(black<=0 && red<=0 && white <=0)game = false;
		 
		 if(player1.getScore()<5 && player2.getScore()<5)return;
		 
//		 System.out.println("checking result..");
		 
		 if(player1.getScore() > player2.getScore()) {
			 if(player1.getScore() - player2.getScore() > 2) {
			 result = true;
			 winner = player1;
			 }
			 return;
		 }
		 else {
			 if(player2.getScore() - player1.getScore() >2) {
				 result = true;
				 winner = player2;
				 }
				 return;
		 }
	 }
	 
	 public boolean checkThreeFoul(Player p) {
		 List<String> histMoves = p.getMoves();
		 int cnt = 0;
		 
		 for(int i = histMoves.size()-1 ; i>=0 && i>histMoves.size()-4 ; i--) {
			 if(histMoves.get(i).equals(STRIKER_STRIKE) || 
					 histMoves.get(i).equals(DEFUNCT_COIN))cnt++;
			
		 }
		 if(cnt>2)return true;
		 return false;
	 }
	 
	 public String gameResult() {
		 if(result) return "Winner of the game is " + winner.toString();
		 
		 return "Game result is Draw!!";		 
	 }
	 
	 public void getStatistics() {
		 System.out.println("Score board : " + player1.getName() + " - "+ player1.getScore()
			 + " & " + player2.getName() + " - "+ player2.getScore());
		 System.out.println("Moves History : ");
		 List<String> history = player1.getMoves();
		 System.out.println(player1.getName() + " - ");
		 for(String move : history) {
			 System.out.print(move + "  ");
		 }
		 List<String> history2 = player2.getMoves();
		 System.out.println("\n" + player2.getName() + " - ");
		 for(String move : history2) {
			 System.out.print(move + "  ");
		 }
	 }
}
