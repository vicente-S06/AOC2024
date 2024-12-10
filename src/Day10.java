import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day10 {
    private static List<List<Integer>> map = new ArrayList<>();
    private static List<Point> trailHeads = new ArrayList<>();

    private static int part1Solution(){
        int sumOfScores = 0;
        for(Point p: trailHeads){
            Set<Point> peaks = new HashSet<>();
            navigateGridPart1(p.row, p.col, peaks);
            sumOfScores += peaks.size();
        }

        return sumOfScores;
    }
    private static void navigateGridPart1(int row, int col, Set<Point> reachedPeaks){
        int currVal = map.get(row).get(col);
        if(currVal == 9) reachedPeaks.add(new Point(row, col));

        if(row + 1 < map.size() && map.get(row+1).get(col) == currVal + 1){
            navigateGridPart1(row+1, col, reachedPeaks);
        }
        if(row - 1 >= 0 && map.get(row-1).get(col) == currVal + 1){
            navigateGridPart1(row-1, col, reachedPeaks);
        }
        if(col + 1 < map.get(col).size() && map.get(row).get(col + 1) == currVal + 1){
            navigateGridPart1(row, col + 1, reachedPeaks);
        }
        if(col - 1 >= 0 && map.get(row).get(col - 1) == currVal + 1){
            navigateGridPart1(row, col - 1, reachedPeaks);
        }
    }

    private static void navigateGridPart2(int row, int col, List<Point> reachedPeaks){
        int currVal = map.get(row).get(col);
        if(currVal == 9) reachedPeaks.add(new Point(row, col));

        if(row + 1 < map.size() && map.get(row+1).get(col) == currVal + 1){
            navigateGridPart2(row+1, col, reachedPeaks);
        }
        if(row - 1 >= 0 && map.get(row-1).get(col) == currVal + 1){
            navigateGridPart2(row-1, col, reachedPeaks);
        }
        if(col + 1 < map.get(col).size() && map.get(row).get(col + 1) == currVal + 1){
            navigateGridPart2(row, col + 1, reachedPeaks);
        }
        if(col - 1 >= 0 && map.get(row).get(col - 1) == currVal + 1){
            navigateGridPart2(row, col - 1, reachedPeaks);
        }
    }

    private static int part2Solution(){
        int sumOfScores = 0;
        for(Point p: trailHeads){
            List<Point> peaks = new ArrayList<>();
            navigateGridPart2(p.row, p.col, peaks);
            sumOfScores += peaks.size();
        }

        return sumOfScores;
    }



    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            map.add(new ArrayList<>());

            line.chars().forEach(c -> {
                map.getLast().add(c - '0');
                if(c == '0'){
                    trailHeads.add(new Point(map.size() - 1, map.getLast().size() - 1));
                }

            });
        }

        System.out.println("Part 2 Solution: " + part2Solution());
    }

    private static class Point{
        int row;
        int col;

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
        public Point(Point p){
            this(0,0);
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof Point p){
                return row == p.row && col == p.col;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
