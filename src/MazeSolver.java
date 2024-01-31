import java.util.LinkedList;
import java.util.Queue;

public class MazeSolver {

	public static void main(String[] args) {
		Cell[][] cells = new Cell[10][10];
		
		for (int i = 0; i < cells.length; ++i)
			for (int j = 0; j < cells[0].length; ++j)
				cells[i][j] = new Cell(i, j);
		
		cells[1][8].type = 'F';
		cells[4][8].type = 'W';
		cells[1][7].type = 'W';
		cells[4][9].type = 'W';
		cells[0][7].type = 'W';
		cells[2][8].type = 'W';
		
		findPathInCells(7, 3, cells);
		cells[1][8].type = 'F';
		printCells(cells);
	}
	
	public static void findPathInCells(int row, int col, Cell[][] cells) {
		Cell cell = searchBreadthFirst(row, col, cells);
		
		while (cell != null) {
			cell.type = 'O';
			cell = cell.previousCell;
		}
		cells[row][col].type = 'S';
	}
	
	public static void printCells(Cell[][] cells) {
		System.out.print(" ");
		for (int i = 0; i < cells[0].length; ++i)
			System.out.print(" " + i);
		System.out.println();
		
		
		for (int i = 0; i < cells.length; ++i) {
			System.out.print(i);
			
			for (int j = 0; j < cells[0].length; ++j)
				System.out.print(" " + cells[i][j].type);
			
			System.out.println();
		}
	}
	
	public static Cell searchBreadthFirst(int startRow, int startCol, Cell[][] cells) {
		Queue<Cell> queue = new LinkedList<>();
		boolean[][] visited = new boolean[cells.length][cells[0].length];
		
		queue.offer(cells[startRow][startCol]);
		
		while (!queue.isEmpty()) {
			
			Cell src = queue.poll();
			visited[src.row][src.col] = true;
			
			if (src.type == 'F')
				return src;
			
			
			int rowBorder = Math.min(cells.length, src.row + 2);
			int colBorder = Math.min(cells[0].length, src.col + 2);
			
			int startCellRow = Math.max(src.row - 1, 0);
			int startCellCol = Math.max(src.col - 1, 0);
			
			for (int row = startCellRow; row < rowBorder; ++row)
				for (int col = startCellCol; col < colBorder; ++col)
					if ((row == src.row ^ col == src.col) && !visited[row][col] && cells[row][col].type != 'W') {
						
						Cell cell = cells[row][col];
						queue.offer(cell);
						cell.previousCell = src;
					}
		}
		
		return null;
	}

}
