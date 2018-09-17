package com.game.interfaces;

public interface IFactory<T> {
	public T newDiceInstance(int noOfFaces);
	public T newOperatorInstance(Character operator);
}
