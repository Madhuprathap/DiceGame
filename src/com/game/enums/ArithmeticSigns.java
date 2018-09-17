package com.game.enums;

public enum ArithmeticSigns {
	PLUS('+'), MINUS('-');
	
	private char operator;
	
	private ArithmeticSigns(char sign) {
		operator = sign;
	}
	
	public char getOperator() {
		return operator;
	}
}
