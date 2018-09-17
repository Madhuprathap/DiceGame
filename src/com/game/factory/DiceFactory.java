package com.game.factory;

import java.util.HashMap;

import com.game.interfaces.IDice;
import com.game.interfaces.IFactory;
import com.game.pojo.Dice;

public class DiceFactory implements IFactory<IDice>{

	private HashMap<Integer, IDice> map =  new HashMap<>();

	public DiceFactory() {
		map.put(4, (IDice) new Dice(4));
		map.put(6, (IDice) new Dice(6));
		map.put(8, (IDice) new Dice(8));
		map.put(10, (IDice) new Dice(10));
		map.put(12, (IDice) new Dice(12));
		map.put(20, (IDice) new Dice(20));
	}

	@Override
	public IDice newDiceInstance(int noOfFaces) {
		return map.get(noOfFaces);
	}

	@Override
	public IDice newOperatorInstance(Character operator) {
		throw new UnsupportedOperationException();
	}
}
