package com.game.interfaces;

import com.game.enums.FactoryType;

public interface IFactoryOfFactories<T> {
	T getFactory(FactoryType type);
}
