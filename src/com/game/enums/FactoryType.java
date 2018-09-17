package com.game.enums;

/**
 * @author magaddam
 * For Immutable nature as we will use these enums as key to store Factory class in a map. 
 */
public enum FactoryType {
	DICE("DICE"), OPERATOR("OPERATOR");

	private String factoryType;

	private FactoryType(String type) {
		factoryType = type;
	}

	public String getFactoryType() {
		return factoryType;
	}
}
