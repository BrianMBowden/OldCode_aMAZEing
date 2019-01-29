
public class MazeCell {
	public int x, y;
	public MazeCell(){
	}
	public MazeCell(int i, int j){
		x=i;
		y=j;
	}
	public boolean equals(MazeCell cell){
		return x == cell.x && y == cell.y;
	}
}
