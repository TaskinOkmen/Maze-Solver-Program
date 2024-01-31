
public class Cell {
	char type;
	int row;
	int col;
	Cell previousCell;
	
	public Cell(char type, int row, int col) {
		this.type = type;
		this.row = row;
		this.col = col;
	}
	
	public Cell(int row, int col) {
		this(' ', row, col);
	}
	
}
