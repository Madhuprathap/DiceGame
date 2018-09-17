package com.game.factory;

import java.util.HashMap;

import com.game.interfaces.IFactory;
import com.game.interfaces.IOperator;
import com.game.pojo.MinusOperator;
import com.game.pojo.PlusOperator;

public class OperatorFactory implements IFactory<IOperator>{

	private HashMap<Character, IOperator> map =  new HashMap<>();
	
	public OperatorFactory() {
		map.put('+', new PlusOperator());
		map.put('-', new MinusOperator());
	}

	@Override
	public IOperator newDiceInstance(int noOfFaces) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IOperator newOperatorInstance(Character operator) {
		return map.get(operator);
	}

}
