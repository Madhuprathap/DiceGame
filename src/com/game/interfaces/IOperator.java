package com.game.interfaces;

public interface IOperator {
	public <T extends Number> int compute(T left, T right);
}
