///////////////////////////////////////////////////////////////////////////////
/*NAME:       Brian Bowden
 *COURSE:     Comp2631
 *INSTRUCTOR: Laura Marik
 *DUE DATE:   Oct 21, 2016
 *ASSIGNMENT: 2
 *=============================================================================
 * PURPOSE: To navigate a pre-made maze using the utilities if stacks and two-
 *          dimensional arrays. 
 * 
 * DETAILS: Program takes in a a series of characters to create a map. 
 * 			Characters are representative of passage-ways ('O'), walls ('W'),
 * 			entrance to maze ('M'), and exit of the maze ('E'). Any character
 * 			is separated by a space in the maze, and the maze is offset on odd
 * 			numbered rows by one space. The resulting maze is navigated 
 * 			hexagonally, there are six surrounding options the user could take.
 * 			After each move, the maze is printed out again, the user can only 
 * 			navigate through the passages, if a wall is encountered, the user 
 * 			can not move through it. If the user finds the exit of the maze, it
 * 			would be considered successful, if the maze is unsolvable, ie the 
 * 			path to the exit is completely blocked, or the exit of the maze does
 * 			not exist, the maze would be unsuccessful. Regardless of the outcome
 * 			the maze will give a count of how many steps it takes to determine 
 * 			its state of success.
 * 			After reading the file containing the maze, the spaces are removed,
 * 			and the exterior of the maze is populated with maze with walls (to
 * 			ensure the mouse/user does not run off the edge of the maze). Before
 * 			echoing out the maze, it is repopulated with spaces and any visited
 * 			space is replaced with a '.' and the current location is marked 
 * 			with an 'X'. 
 * 
 * BUGS: Mouse like to occasionally jump around when backtracking
 * 
 * ASSUMPTIONS: Assume pre-made mazes uses the correct characters.
 *              Assume mazes are hexagonally arranged, separated by spaces 
 *                between characters.
 *              Assume mice like cheese
 * 
 * 
 *****************************************************************************/
 //////////////////////////////////////////////////////////////////////////////

import java.io.BufferedReader;
import java.io. FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String fileName = null;
		BufferedReader fIn = null;
		
		try{
			fileName = args[0];
		}
		catch(Exception e){
			System.out.println("Usage: Main <filename>" );
			System.exit(1);
		}
		
		try{
			fIn = new BufferedReader(new FileReader(fileName));
		}
		catch(Exception e){
			System.out.println("Failed to open " +fileName);
			System.exit(1);
		}
		
		Maze map = new Maze(fIn);
		System.out.println("map loaded: \n");
		map.echoMaze();
 		System.out.println("Now we will try to solve it... \n");
		map.exitMaze();

		
		
	}

}
