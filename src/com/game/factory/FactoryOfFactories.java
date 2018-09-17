package com.game.factory;

import java.util.HashMap;

import com.game.enums.FactoryType;
import com.game.interfaces.IFactory;
import com.game.interfaces.IFactoryOfFactories;

public class FactoryOfFactories<T extends IFactory<?>> implements IFactoryOfFactories<T>{
	
	private HashMap<FactoryType, T> map =  new HashMap<>();
	
	public FactoryOfFactories() {
		map.put(FactoryType.DICE, (T)new DiceFactory());
		map.put(FactoryType.OPERATOR, (T)new OperatorFactory());
	}

	@Override
	public T getFactory(FactoryType type) {
		return (T)(map.get(type));
	}
	
}
