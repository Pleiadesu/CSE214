 

public class App {
    public static void main(String[] args) {
        Maze map= new Maze(map1);
        Path path = new Path(map);
        path.findPath(0,  0,  15,  15);
        map.print();
    }
    public static final char[][] map1 = {
            ".#.#......#...#.".toCharArray(),
            ".#...#.####.#.#.".toCharArray(),
            ".#.###......#.#.".toCharArray(),
            "...#...##.#.#...".toCharArray(),
            "####.#.##.#.####".toCharArray(),
            "...#.#..........".toCharArray(),
            ".###.##########.".toCharArray(),
            ".....#.......#..".toCharArray(),
            "######.#.###.#.#".toCharArray(),
            ".....#.##...##.#".toCharArray(),
            ".#.#......#.....".toCharArray(),
            ".#.#.###########".toCharArray(),
            ".#.#...#........".toCharArray(),
            ".....#...#.####.".toCharArray(),
            ".#.#..#####.....".toCharArray(),
            "...##.......##..".toCharArray()
        };
}
