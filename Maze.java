import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Maze {
	private int rows = 0, columns = 0;
	private char[][] map;
	private MazeCell exitcell = new MazeCell();
	private MazeCell entrycell = new MazeCell();
	private MazeCell currentcell;
	private final char exitMark = 'C';
	private final char enterMark = 'M';
	private final char visited = '.';
	private final char passage = 'O';
	private final char wall = 'W';
	private final char solve = 'S';
	private Stack<MazeCell> mazeStack = new Stack<MazeCell>();
	private Stack<MazeCell> backtrackStack = new Stack<MazeCell>();
	
	public Maze(BufferedReader fIn){
		int row = 0, col = 0;
		Stack<String> mazeRows = new Stack<String>();
		
		Scanner sc = new Scanner(fIn);		
		try{
		int mazeRow = sc.nextInt();
		int mazeColumns = sc.nextInt();
			String str = sc.nextLine();
			str = str.replace(" ", "");
			while (str != null){
				if (!str.isEmpty()){
					row++;
					columns = str.length();
					if (str.length() != 0){
						str = 'W' + str + 'W';
					}
					mazeRows.push(str);
					if (str.indexOf(enterMark) != -1){
						entrycell.x = row;
						entrycell.y = str.indexOf(enterMark);
					}
					if (str.indexOf(exitMark) != -1){
						exitcell.x = row;
						exitcell.y = str.indexOf(exitMark);
					}
				}
				str = sc.nextLine();
				str = str.replace(" ", "");
			}
		}
			 catch(Exception eof) {
			}
		rows = row;
		map = new char[rows+2][];
		map[0] = new char[columns+2];
		while (!mazeRows.isEmpty()){
			map[row] = mazeRows.pop().toCharArray();
			row--;
		}
		map[rows+1] = new char[columns+2];
		
		for (col = 0; col <= columns + 1; col++){
			map[0][col] = wall;
			map[rows+1][col] = wall;
		}
	}
	
	public void echoMaze(){
		
		for (int row = 0; row <= rows + 1; row++){
			String echo = new String(map[row]);
			echo = echo.replace("", " ");
			if (row % 2 == 0)
				System.out.print(" ");
			System.out.println(echo);
				}
		System.out.println();
		}
	
	
	private void pushUnvisited(int row, int col){
		if (map[row][col] == passage || map[row][col] == exitMark){
			mazeStack.push(new MazeCell(row,col));
		}
//		else if (map[row][col] == visited){
//			mazeStack.push(new MazeCell(row, col));
//		}
	}
	
	
	
	public void exitMaze(){
	
		MazeCell lastcell = new MazeCell(0, 0);
		int rowf = exitcell.x;
		int colf = exitcell.y;
		int count = 0;
		char proceed = 0;
		
		currentcell = entrycell;
		System.out.println("entry at: " + entrycell.x + ", " + entrycell.y + '\n'
				+ "exit at: " + exitcell.x + ", " + exitcell.y);
		System.out.println();
		String prompt = "=================================================\n";
		prompt +=       "         Please press enter to proceed           \n";
		prompt +=       "=================================================\n";
		while (!currentcell.equals(exitcell)){
			System.out.println(prompt);
			try {
				proceed = (char) System.in.read();
				proceed = (char) System.in.read();
			} catch (IOException e){}
//			if (proceed != '\n'){
//				proceed = 0;
//				System.out.println("Please use enter to proceed...");
//			}
			if (proceed == '\r' || proceed == '\n'){
				int row = currentcell.x;
				int col = currentcell.y;
				if (map[row][col] != enterMark)
					map[row][col] = 'X';
				echoMaze(); 
				if (!currentcell.equals(entrycell))
					map[row][col] = visited;
				if (row % 2 == 1){
					pushUnvisited(row-1, col);
					pushUnvisited(row, col+1);
					pushUnvisited(row+1, col);
					pushUnvisited(row+1, col-1);
					pushUnvisited(row, col-1);
					pushUnvisited(row-1, col-1);
					}
				else{
					pushUnvisited(row-1, col+1);
					pushUnvisited(row, col+1);
					pushUnvisited(row+1, col+1);
					pushUnvisited(row+1, col);
					pushUnvisited(row, col-1);
					pushUnvisited(row-1, col);
					}
				if (mazeStack.isEmpty()){
					echoMaze();
					System.out.println("Maze Unsuccessful: \nFailed in " + count + " steps");
					return;
					}
				else{
					lastcell = currentcell;
					backtrackStack.push(lastcell);
					currentcell = mazeStack.pop();
					}
				}
//			else{
//					proceed = 0;
//					System.out.println("Please use enter to proceed...");
//				}
			count++;
		}
		map[rowf][colf] = solve;
		echoMaze();
		System.out.println("Maze Successful: \nSolved in " + count + " steps");
	}
			
}
