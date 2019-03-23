package carrom.game.utils;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int id;
	private String name;
	private List<String> moves;
	private int score;
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}

	public Player(String name, int id) {
		this.id = id;
		this.name = name;
		this.moves = new ArrayList<String>();
		score = 0;
	}
	
	public List<String> getMoves() {
		return moves;
	}

	public void setMove(String move) {
		this.moves.add(move);
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
