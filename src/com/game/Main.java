package com.game;

import java.util.Scanner;

import com.game.interfaces.IParserEvalService;
import com.game.services.ParserEvalService;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Enter Dice combination:");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		IParserEvalService parserEvalService = new ParserEvalService();
		try{
			int totalScore = parserEvalService.eval(new StringBuilder(input.trim()));
			System.out.println("Valid, "+ totalScore);
		} catch(RuntimeException e) {
			System.out.println("Invalid, " + e.getMessage());
		}
	}
}
