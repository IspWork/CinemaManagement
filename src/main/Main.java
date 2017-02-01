package main;

import java.io.IOException;

import models.ConsoleUI;

public class Main {
	public static void main(String[] args) {
		 ConsoleUI ui = new ConsoleUI();
		 try {
		 ui.startLoop();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
	}
}
