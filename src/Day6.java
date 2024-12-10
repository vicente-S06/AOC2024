import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day6 {
    private static ArrayList<ArrayList<Character>> room = new ArrayList<>();
    private static Point startPos;
    private static Point currPos;

    private static int part1Solution() {
        int diffPositions = 0;
        int horizVelocity = 0;
        int vertVelocity = 1;

        while (currPos.x >= 0 && currPos.x < room.getFirst().size() &&
                currPos.y >= 0 && currPos.y < room.size()) {
            char currChar = room.get(currPos.y).get(currPos.x);

            if (currChar == '#') {
                //Revert back to position
                currPos.x -= horizVelocity;
                currPos.y += vertVelocity;

                //Turn 90 degrees
                if (vertVelocity != 0) {
                    horizVelocity = vertVelocity;
                    vertVelocity = 0;
                } else {
                    vertVelocity = -horizVelocity;
                    horizVelocity = 0;
                }

            } else if (currChar != 'X') {
                diffPositions++;
                room.get(currPos.y).set(currPos.x, 'X');
            }
            currPos.x += horizVelocity;
            currPos.y -= vertVelocity;
        }

        return diffPositions;
    }

    private static int part2Solution() {
        int diffObstructions = 0;

        for (int i = 0; i < room.size(); i++) {
            for (int j = 0; j < room.get(i).size(); j++) {
                char currChar = room.get(i).get(j);
                if (currChar == '.') {
                    List<List<Character>> newRoom = new ArrayList<>();
                    List<List<Set<Point>>> newRoomSpeeds = new ArrayList<>();
                    for (ArrayList<Character> row : room) {
                        newRoom.add(new ArrayList<>(row));
                        newRoomSpeeds.add(new ArrayList<>());
                        for (Character c : row) {
                            newRoomSpeeds.getLast().add(new HashSet<>());
                        }
                    }

                    newRoom.get(i).set(j, '#');

                    if (checkIfLoop(newRoom, newRoomSpeeds)) diffObstructions++;
                }
            }
        }

        return diffObstructions;
    }

    private static boolean checkIfLoop(List<List<Character>> modRoom, List<List<Set<Point>>> roomSpeeds) {
        //Reset Positions
        currPos.x = startPos.x;
        currPos.y = startPos.y;

        int horizVelocity = 0;
        int vertVelocity = 1;

        while (currPos.x >= 0 && currPos.x < modRoom.getFirst().size() &&
                currPos.y >= 0 && currPos.y < modRoom.size()) {
            char currChar = modRoom.get(currPos.y).get(currPos.x);

            if (currChar == '#') {
                //Revert back to position
                currPos.x -= horizVelocity;
                currPos.y += vertVelocity;

                //Turn 90 degrees
                if (vertVelocity != 0) {
                    horizVelocity = vertVelocity;
                    vertVelocity = 0;
                } else {
                    vertVelocity = -horizVelocity;
                    horizVelocity = 0;
                }
            }else{
                if(horizVelocity != 0) {
                    modRoom.get(currPos.y).set(currPos.x, '-');
                }else{
                    modRoom.get(currPos.y).set(currPos.x, '|');
                }
            }

            if (!roomSpeeds.get(currPos.y).get(currPos.x).add(new Point(horizVelocity, vertVelocity))) {
                return true;
            }
            

            currPos.x += horizVelocity;
            currPos.y -= vertVelocity;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Scanner scan = new Scanner(new File("input.txt"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            ArrayList<Character> chars = new ArrayList<>();

            line.chars().forEach(c -> {
                if (c == '^') {
                    startPos = new Point(chars.size(), room.size());
                }
                chars.add((char) c);
            });
            room.add(chars);
        }
        currPos = new Point(startPos.x, startPos.y);

        System.out.printf("Start X: %d, Start Y: %d\n", currPos.x, currPos.y);
        System.out.printf("Part 2 Solution: %d\n", part2Solution());
        System.out.printf("Time: %d ms\n", (System.currentTimeMillis() - startTime));

    }

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
            this(0, 0);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Point other) {
                return x == other.x && y == other.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
