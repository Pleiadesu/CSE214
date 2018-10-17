 

public class Maze {
    public int row, col;
    public boolean[][] map;
    public int[][] distance;
    
    public Maze(char[][] map) {
        row = map.length;
        col = map[0].length;
        this.map = new boolean[row][col];
        for(int i = 0; i < row; i++)
            for(int j = 0; j < col; j++)
                this.map[i][j] = (map[i][j] == '.');
        this.distance = new int[row][col];
        initDistance();
    }
    
    public void initDistance() {
        for(int i = 0; i < row; i++)
            for(int j = 0; j < col; j++)
                this.distance[i][j] = row*col;
    }
    
    public void print() {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(!map[i][j])  System.out.print("###");
                else            System.out.format("%3d", distance[i][j]);
            }
            System.out.println("");
        }
    }
}
