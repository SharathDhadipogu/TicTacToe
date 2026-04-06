package TicTacToe;

import java.util.Random;
import java.util.Scanner;

class TicTacToe {
	static char[][] board;

	public TicTacToe() {
		board = new char[3][3];
		initBoard();
	}

	public void initBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public static void displayBoard() {
		System.out.println("-------------");
		for(int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	public static void placeMark(int row,int col, char mark) {
		if(row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			board[row][col] = mark;
		}
	}

	public static boolean checkColWin() {
		for(int j = 0; j <= 2; j++) {
			if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkRowWin() {
		for(int i = 0; i <= 2; i++) {
			if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkDiagonalWin() {

		if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]|| board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}
	
	public static boolean checkDraw() {
		for(int i = 0; i <= 2; i++) {
			for(int j = 0; j <= 2; j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}


abstract class Player {
	String name;
	char mark;

	abstract void makeMove();

	boolean isValid(int row, int col) {
		if(row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			if(TicTacToe.board[row][col] == ' ') {
				return true;
			}
		}
		return false;
	}
}

class HumanPlayer extends Player{

	public HumanPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		Scanner sc = new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("Enter row and column: ");
			row = sc.nextInt();
			col = sc.nextInt();
		}while(!isValid(row,col));

		TicTacToe.placeMark(row,col,mark);
	}
}


class AIPlayer extends Player{

	public AIPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		Scanner sc = new Scanner(System.in);
		int row;
		int col;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3
					);
		}while(!isValid(row,col));

		TicTacToe.placeMark(row,col,mark);
	}
}


public class LaunchGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		TicTacToe t = new TicTacToe();
		System.out.println("Welcome to Amazing TicTacToe:)");
		while(true) {
			System.out.println("1.Play with Human");
			System.out.println("2.Play with AI");
			System.out.println("3.Exit");
			System.out.println("Enter your choice: ");
			int n = sc.nextInt();
			sc.nextLine();

			if(n == 1) {
				System.out.println("Enter name for player 1:");
				String player1 = sc.nextLine();
				System.out.println("Enter your character: ");
				char c1 = sc.next().charAt(0);
				sc.nextLine();
				HumanPlayer p1 = new HumanPlayer(player1,c1);
				System.out.println("Enter name for player 2:");
				String player2 = sc.nextLine();
				System.out.println("Enter your character: ");
				char c2 = sc.next().charAt(0);
				HumanPlayer p2 = new HumanPlayer(player2,c2);

				HumanPlayer cp;
				cp = p1;

				while(true) {
					System.out.println(cp.name + " turn");
					cp.makeMove();
					TicTacToe.displayBoard();
					if(TicTacToe.checkColWin()||TicTacToe.checkRowWin()||TicTacToe.checkDiagonalWin()) {
						System.out.println(cp.name + " won ");
						break;
					}else if(TicTacToe.checkDraw()) {
						System.out.println("Game is Draw");
						break;
					}else {
						if(cp == p1) {
							cp = p2;
						}else {
							cp = p1;
						}
					}
				}
			}else if(n == 2){
				HumanPlayer p1 = new HumanPlayer("Bob",'X');
				AIPlayer p2 = new AIPlayer("AI",'O');
				
				Player cp = p1;
				while(true) {
					System.out.println(cp.name + " turn");
					cp.makeMove();
					TicTacToe.displayBoard();
					if(TicTacToe.checkColWin()||TicTacToe.checkRowWin()||TicTacToe.checkDiagonalWin()) {
						System.out.println(cp.name + " won ");
						break;
					}else if(TicTacToe.checkDraw()) {
						System.out.println("Game is Draw");
						break;
					}else {
						if(cp == p1) {
							cp = p2;
						}else {
							cp = p1;
						}
					}
				}
			}else {
				System.exit(0);
			}
		}
	}
}
