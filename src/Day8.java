import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day8 {
    private static HashMap<Character, List<Point>> antennaMap = new HashMap<>();
    private static Set<Point> antiNodePoints = new HashSet<>();
    private static int tableRowSize;
    private static int tableColSize;

    private static int part1Solution(){
        for(Character c : antennaMap.keySet()){
            List<Point> pointList = antennaMap.get(c);
            for(int i = 0; i < pointList.size(); i++){
                for(int j = i + 1; j < pointList.size(); j++){
                    int rowDiff = pointList.get(i).row - pointList.get(j).row;
                    int colDiff = pointList.get(i).col - pointList.get(j).col;

                    Point antiNode1 = new Point(pointList.get(i).row + rowDiff, pointList.get(i).col + colDiff);
                    Point antiNode2 = new Point(pointList.get(i).row - (2 * rowDiff), pointList.get(i).col - (2*colDiff));

                    if(antiNode1.row < tableRowSize && antiNode1.row >= 0 &&
                    antiNode1.col < tableColSize && antiNode1.col >= 0){
                        antiNodePoints.add(antiNode1);
                    }
                    if(antiNode2.row < tableRowSize && antiNode2.row >= 0 &&
                            antiNode2.col < tableColSize && antiNode2.col >= 0){
                        antiNodePoints.add(antiNode2);
                    }
                }
            }
        }

        return antiNodePoints.size();
    }

    private static int part2Solution(){
        for(Character c : antennaMap.keySet()){
            List<Point> pointList = antennaMap.get(c);
            for(int i = 0; i < pointList.size(); i++){
                for(int j = i + 1; j < pointList.size(); j++){
                    int rowDiff = pointList.get(i).row - pointList.get(j).row;
                    int colDiff = pointList.get(i).col - pointList.get(j).col;

                    int newPointR;
                    int newPointC;
                    Point antiNode;
                    for(int n = 0; ;n++){
                        newPointR = pointList.get(i).row + (n * rowDiff);
                        newPointC = pointList.get(i).col + (n * colDiff);
                        antiNode = new Point(newPointR, newPointC);

                        if(antiNode.row < tableRowSize && antiNode.row >= 0 && antiNode.col < tableColSize && antiNode.col >= 0)
                            antiNodePoints.add(antiNode);
                        else
                            break;
                    }

                    for(int n = 1; ;n++){
                        newPointR = pointList.get(i).row - (n * rowDiff);
                        newPointC = pointList.get(i).col - (n * colDiff);
                        antiNode = new Point(newPointR, newPointC);

                        if(antiNode.row < tableRowSize && antiNode.row >= 0 && antiNode.col < tableColSize && antiNode.col >= 0)
                            antiNodePoints.add(antiNode);
                        else
                            break;
                    }
                }
            }
        }

        return antiNodePoints.size();
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        tableRowSize = 0;
        while(scan.hasNextLine()){
            StringBuilder line = new StringBuilder(scan.nextLine());
            for(int i = 0; i < line.length(); i++){
                char c = line.charAt(i);
                if(c != '.'){
                    if(!antennaMap.containsKey(c)){
                        antennaMap.put(c, new ArrayList<Point>());
                    }
                    antennaMap.get(c).add(new Point(tableRowSize, i));
                }
            }
            tableRowSize++;
            tableColSize = line.length();
        }

        System.out.println("Part 1 Solution: " + part1Solution());
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
