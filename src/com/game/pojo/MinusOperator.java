package com.game.pojo;

import com.game.interfaces.IOperator;

public class MinusOperator implements IOperator{

	@Override
	public <T extends Number> int compute(T left, T right) {
		return left.intValue() - right.intValue();
	}

}
