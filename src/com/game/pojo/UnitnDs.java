package com.game.pojo;

import com.game.enums.FactoryType;
import com.game.factory.DiceFactory;
import com.game.factory.FactoryOfFactories;
import com.game.interfaces.IDice;

public class UnitnDs {
	private int noOfDices;
	private int noOfFaces;
	private static DiceFactory diceFactory = new FactoryOfFactories<DiceFactory>().getFactory(FactoryType.DICE);
	
	public int getNoOfDices() {
		return noOfDices;
	}
	public void setNoOfDices(int noOfDices) {
		this.noOfDices = noOfDices;
	}
	public int getNoOfFaces() {
		return noOfFaces;
	}
	public void setNoOfFaces(int noOfFaces) {
		this.noOfFaces = noOfFaces;
	}
	public UnitnDs(int noOfDices, int noOfFaces) {
		super();
		this.noOfDices = noOfDices;
		this.noOfFaces = noOfFaces;
	}
	
	public int play() {
		int score = 0;
		IDice dice = diceFactory.newDiceInstance(noOfFaces);
		while (noOfDices-- > 0) {
			score = score + dice.roll();
//			--noOfDices;
		}
		return score;
	}
}
