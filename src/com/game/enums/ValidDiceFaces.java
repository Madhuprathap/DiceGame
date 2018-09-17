package com.game.enums;

public enum ValidDiceFaces {
	FOUR(4),SIX(6),EIGHT(8),TEN(10),TWELVE(12),TENTY(20);
	
	private int faces;
	
	private ValidDiceFaces(int faces){
		this.faces = faces;
	}
	
	public int getFaces() {
		return faces;
	}
}
