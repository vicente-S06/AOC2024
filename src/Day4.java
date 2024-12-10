import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    private static char[][] tableArr;

    private static int part1FoundMatch(int row, int col) {
        boolean ableDown = row + 3 < tableArr.length;
        boolean ableUp = row - 3 >= 0;
        boolean ableRight = col + 3 < tableArr[row].length;
        boolean ableLeft = col - 3 >= 0;

        int count = 0;
        if (ableRight) {
            if (tableArr[row][col + 1] == 'M' &&
                    tableArr[row][col + 2] == 'A' &&
                    tableArr[row][col + 3] == 'S') count++;

            if (ableUp &&
                    tableArr[row - 1][col + 1] == 'M' &&
                    tableArr[row - 2][col + 2] == 'A' &&
                    tableArr[row - 3][col + 3] == 'S') count++;

            if (ableDown &&
                    tableArr[row + 1][col + 1] == 'M' &&
                    tableArr[row + 2][col + 2] == 'A' &&
                    tableArr[row + 3][col + 3] == 'S') count++;

        }

        if (ableLeft) {
            if (tableArr[row][col - 1] == 'M' &&
                    tableArr[row][col - 2] == 'A' &&
                    tableArr[row][col - 3] == 'S') count++;

            if (ableUp &&
                    tableArr[row - 1][col - 1] == 'M' &&
                    tableArr[row - 2][col - 2] == 'A' &&
                    tableArr[row - 3][col - 3] == 'S') count++;

            if (ableDown &&
                    tableArr[row + 1][col - 1] == 'M' &&
                    tableArr[row + 2][col - 2] == 'A' &&
                    tableArr[row + 3][col - 3] == 'S') count++;

        }

        if (ableDown &&
                tableArr[row + 1][col] == 'M' &&
                tableArr[row + 2][col] == 'A' &&
                tableArr[row + 3][col] == 'S') count++;


        if (ableUp &&
                tableArr[row - 1][col] == 'M' &&
                tableArr[row - 2][col] == 'A' &&
                tableArr[row - 3][col] == 'S') count++;
        return count;
    }

    private static int part1Solution() {
        int resultCount = 0;

        for (int row = 0; row < tableArr.length; row++) {
            for (int col = 0; col < tableArr[row].length; col++) {
                if (tableArr[row][col] == 'X') {
                    resultCount += part1FoundMatch(row, col);

                }
            }
        }

        return resultCount;
    }

    private static int part2FoundMatch(int row, int col) {
        boolean firstDiag = false; // "\"
        boolean secondDiag = false; // "/"

        firstDiag = (tableArr[row - 1][col - 1] == 'M' && tableArr[row + 1][col + 1] == 'S') ||
                (tableArr[row - 1][col - 1] == 'S' && tableArr[row + 1][col + 1] == 'M');


        secondDiag = (tableArr[row + 1][col - 1] == 'M' && tableArr[row - 1][col + 1] == 'S') ||
                (tableArr[row + 1][col - 1] == 'S' && tableArr[row - 1][col + 1] == 'M');

        return (firstDiag && secondDiag)?1:0;
    }

    private static int part2Solution() {
        int resultCount = 0;

        for (int row = 1; row < tableArr.length - 1; row++) {
            for (int col = 1; col < tableArr[row].length - 1; col++) {
                if (tableArr[row][col] == 'A') {
                    resultCount += part2FoundMatch(row, col);
                }
            }
        }

        return resultCount;
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        List<List<Character>> table = new ArrayList<>();

        while (scan.hasNextLine()) {
            ArrayList<Character> row = new ArrayList<>();
            scan.nextLine().chars().forEach(c -> row.add((char) c));
            table.add(row);
        }
        scan.close();

        tableArr = new char[table.size()][table.getFirst().size()];
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                tableArr[i][j] = table.get(i).get(j);
            }

        }


        System.out.println("Part 2 Solution: " + part2Solution());

    }
}
