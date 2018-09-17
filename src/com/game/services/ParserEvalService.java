package com.game.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import com.game.enums.ArithmeticSigns;
import com.game.enums.FactoryType;
import com.game.enums.ValidDiceFaces;
import com.game.exception.InvalidFaces;
import com.game.exception.InvalidInputExpression;
import com.game.exception.InvalidNoOfDice;
import com.game.exception.InvalidOperator;
import com.game.exception.InvalidUnitnDs;
import com.game.factory.FactoryOfFactories;
import com.game.factory.OperatorFactory;
import com.game.interfaces.IOperator;
import com.game.interfaces.IParserEvalService;
import com.game.pojo.UnitnDs;

public class ParserEvalService implements IParserEvalService{

	// to store nDs//nds
	Queue<UnitnDs> unitsofnDs = new LinkedList<>();
	// to store operators in FIFO order for evaluation
	Queue<IOperator> operators = new LinkedList<>();
	HashSet<Character> allowedOperators = null;
	HashSet<Integer> allowedFaces = null;
	
	private static OperatorFactory operatorFactory = new FactoryOfFactories<OperatorFactory>().getFactory(FactoryType.OPERATOR);
	
	private void parse(StringBuilder input) throws RuntimeException {
		boolean forUnit = true;
		int inputLength = input.length();
		for (int i = 0; i < inputLength; ) {
			if (forUnit) {
				// To ignore space before and after operator
				while (input.charAt(i) == ' ')
					++i;
				
				int noOfDices = 0;
				boolean isValidChar = false;
				while (i < inputLength && input.charAt(i) >= 48 && input.charAt(i) <= 57) {
					noOfDices = noOfDices * 10 + (input.charAt(i) - 48);
					++i;
					isValidChar = true;
				}
				// Dices number should be numeric && 0 no of Dice doesn't make sense
				if (!isValidChar) {
					throw new InvalidInputExpression("No of Dices should be numeric.");
				} else if (noOfDices == 0) {
					throw new InvalidNoOfDice("0 dice doesn't make sense");
				} 
				
				//Space between nDs like 4 D 6
				char d = input.charAt(i++);
				if (d == ' ') {
					throw new InvalidUnitnDs("no spaces allowed in nDs notation");
				} // invalid char: unit should be in nDs or nds format
				else if (d != 'd' && d != 'D') {
					throw new InvalidUnitnDs("Paly unit should be in nDs or nds format");
				}
				
				// validate no of faces
				int noOfFaces = 0;
				while (i < inputLength && input.charAt(i) >= 48 && input.charAt(i) <= 57) {
					noOfFaces = noOfFaces * 10 + (input.charAt(i) - 48);
					++i;
				}
				if(isNoOfFacesValid(noOfFaces)) {
					unitsofnDs.add(new UnitnDs(noOfDices, noOfFaces));
					forUnit = false;
				}
			} else {
				// To ignore space before and after operator
				while (i < inputLength && input.charAt(i) == ' ')
					++i;
				
				if (isAllowedOperator(input.charAt(i))) {
					operators.add(operatorFactory.newOperatorInstance(input.charAt(i++)));
					forUnit = true;
				}
			}
		}
	}
	

	private boolean isNoOfFacesValid(int noOfFaces) throws InvalidFaces{
		if (allowedFaces == null) {
			allowedFaces = new HashSet<>();
			for (ValidDiceFaces op  : ValidDiceFaces.values()) {
				allowedFaces.add(op.getFaces());
			}
		}
		
		if (allowedFaces.contains(noOfFaces)) {
			return true;
		} else 
			throw new InvalidFaces(noOfFaces +" Sided dice are not supported");
	}


	private boolean isAllowedOperator(char c){
		if (allowedOperators == null) {
			allowedOperators = new HashSet<>();
			for (ArithmeticSigns op  : ArithmeticSigns.values()) {
				allowedOperators.add(new Character(op.getOperator()));
			}
		}
		
		if (allowedOperators.contains(c)) {
			return true;
		} else 
			throw new InvalidOperator(c + "Operator not allowed");
	}

	@Override
	public int eval(StringBuilder input) throws RuntimeException {
		if (operators.isEmpty() && unitsofnDs.isEmpty()) {
			parse(input);
		}
		int totalScore = 0;
		if (operators.size() + 1 == unitsofnDs.size()) {
			//Fist unit
			UnitnDs unit = unitsofnDs.poll();
			totalScore = unit.play();
			while (!unitsofnDs.isEmpty() || !operators.isEmpty()) {
				IOperator operator = operators.poll();
				unit = unitsofnDs.poll();
				int score = unit.play();
				totalScore = operator.compute(totalScore, score);
			}
			return totalScore;
		} else {
			throw new InvalidInputExpression("Invalid input expression");
		}
	}
	
	/*public static void main(String[] args) {
		IParserEvalService parserService = new ParserEvalService();
		System.out.println(parserService.eval(new StringBuilder("4D6 + 3D4")));
	}*/

}
