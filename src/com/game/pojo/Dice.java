package com.game.pojo;

import java.util.Random;

import com.game.interfaces.IDice;

public class Dice implements IDice {
	private int noOfFaces;
	private int MAX_VALUE;
	private static final int MIN_VALUE = 1;

	public void setNoOfSide(int noOfSide) {
		this.noOfFaces = noOfSide;
		MAX_VALUE = noOfSide;
	}
	
	public int getNoOfSide() {
		return noOfFaces;
	}

	public Dice(int noOfSide) {
		setNoOfSide(noOfSide);
		MAX_VALUE = noOfSide;
	}
	
	public Dice() {
	}

	@Override
	public int roll() {
		/*Random random = new Random();
		return random.nextInt(MAX_VALUE) + MIN_VALUE;*/
		return (int)(Math.random() * (MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
	}

	/*public static void main(String[] args) {
		DiceFactory diceFactory = new FactoryOfFactories<DiceFactory>().getFactory(FactoryType.DICE);
		Dice dice = diceFactory.newInstance(6);
		System.out.println(dice.roll());
	}*/
}
