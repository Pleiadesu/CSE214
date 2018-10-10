 

import java.util.Random;

public class SnakeWorld {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 70;
    public static final int MAX_APPLES = 8;
    
    public static class Pos {
        public int x, y;
        public Pos(int x, int y) { this.x = x; this.y = y; }
        public boolean equals(Pos pos) { return x == pos.x && y == pos.y; }
    }
    
    //class Apple
    private static class Apple {
        private Wall wall;
        private Snake snake;
        private Pos pos;
        private int createCount;
        
        public Apple(Wall wall, Snake snake) {
            this.wall = wall; 
            this.snake = snake;
            init();
        }
        public void init() {
            createCount = 0;
            create();
        }
        public int create() {
            Random rand = new Random();
            //TODO: randomly place an apple at (x,y) such that
            //      0 <= x < WIDTH,
            //      0 <= y < HEIGHT, and
            //      it does not hit the wall nor the snake
            return createCount++;
        }
        public int getCount() { return createCount; }
        public Pos getPos() { return pos; }
    }
    
    //class Wall
    private static class Wall {
        private CircularlyListDeque<Pos> pos;
        public Wall() {
            init(0);
        }
        public void init(int n) {
            pos = new CircularlyListDeque<Pos>();
            
            for(int x = 0; x < WIDTH; x++) {
                //TODO: add (x, 0) and (x, HEIGHT-1) to pos
            }
            for(int y = 0; y < HEIGHT; y++) {
                //TODO: add (0, y) and (WIDTH-1, y) to pos
            }
            for(int k = 0; k < n; k++) {
                int x = (k+1)*WIDTH/(n+1);
                for(int y = 0; y < HEIGHT*2/5; y++) {
                    //TODO add(x, y) to pos
                }
                for(int y = HEIGHT*3/5; y < HEIGHT; y++) {
                    //TODO add(x, y) to pos
                }
            }
        }
        public Iterable<Pos> getPos() {
            return pos;
        }
    }
    
    //class Snake
    private static class Snake {
        private static final int[] DX = new int[] {0, 1, 0, -1};  //NESW
        private static final int[] DY = new int[] {1, 0, -1, 0};  //NESW
        private CircularlyListDeque<Pos> pos;
        private int dir;
        private int incr;
        
        public Snake() {
            init();
        }
        public void init() {
            incr = 1;
            dir = 1; //E
            //TODO: initialize pos
            //      add Pos(1, HEIGHT/2) to pos
        }
        public void turnRight() {
            //TODO: rotate dir in this order: 0 -> 1 -> 2 -> 3 -> 0 -> 1 ...
        }
        public void turnLeft() {
            //TODO: rotate dir in this order: 0 -> 3 -> 2 -> 1 -> 0 -> 3 ...
        }
        public Pos nextHeadPos() {
            //TODO: get the head position from pos
            //      add DX[dir] and DY[dir] to the head
            return null;
        }
        public void move() {
            //TODO: add nextHeadPos() to pos
            //      if incr > 0, decrease incr
            //      otherwise, remove tail from pos
        }
        public void grow() {
            //TODO: increase incr by pos.size()
        }
        public Iterable<Pos> getPos() {
            //TODO: return pos
        }
    }
    
    private Apple apple;
    private Wall wall;
    private Snake snake;
    private int stageCount;
    private int score;
    private boolean gameOver;
    
    public SnakeWorld() {
        init();
    }
    public void init() {
        wall = new Wall();
        snake = new Snake();
        apple = new Apple(wall, snake);
        stageCount = 0;
        score = 0;
        gameOver = false;
    }
    public int getScore()              { return score; }
    public Iterable<Pos> getWallPos()  { return wall.getPos(); }
    public Iterable<Pos> getSnakePos() { return snake.getPos(); }
    public Pos getApplePos()           { return apple.getPos(); }
    public int getAppleCount()         { return apple.getCount(); }
    public void turnRight()            { snake.turnRight(); }
    public void turnLeft()             { snake.turnLeft(); }
    public boolean step() {
        if(gameOver)
            return true;
        
        Pos head = snake.nextHeadPos();
        //hit the wall or bite itself
        //TODO: using the hit method below, if head hit the wall or
        //      hit the snake itself, set gameOver to true and return true
        
        //eat apple
        //TODO: if the head hit the apple
        //      1. increase score by the snake's size
        //      2. if apple.getCount() == MAX_APPLES call newStage()
        //         otherwise, grow snake and create a new apple
        
        //move to the next position
        snake.move();
        
        return false;
    }
    private void newStage() {
        stageCount++;
        wall.init(stageCount);
        snake.init();
        apple.init();
    }
    private static boolean hit(Iterable<Pos> list, Pos pos) {
        //TODO: return if list has has a position equal to pos
        return false;
    }
}
