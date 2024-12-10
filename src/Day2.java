import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day2 {

    public static int part1Solution(Scanner scan) {
        int safeReports = 0;
        int increasing = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            int[] levels = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

            //Increasing if 1, decreasing if -1
            increasing = (levels[1] > levels[0]) ? 1 : -1;
            int diff;

            for (int i = 1; i < levels.length; i++) {
                diff = (levels[i] - levels[i - 1]) * increasing;
                if (diff < 1 || diff > 3) {
                    safeReports--;
                    break;
                }
            }

            safeReports++;
        }

        return safeReports;
    }

    public static int part2Solution(Scanner scan) {
        int safeReports = 0;
        int increasing = 0;
        while (scan.hasNextLine()) {
            //{0, 5, 1, 2, 3, 4}
            //Reading Line & Parsing to ArrayList
            String line = scan.nextLine();
            ArrayList<Integer> levelList = new ArrayList<>();
            Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).forEach(levelList::add);

            //Setting Run Variables
            boolean successfulRun = false;

            for (int s = 0; !successfulRun && s < levelList.size(); s++) {
                ArrayList<Integer> tempList = new ArrayList<>(levelList.stream().toList());
                tempList.remove(s);

                increasing = (tempList.get(1) > tempList.get(0)) ? 1 : -1;

                for (int i = 1, diff; i < tempList.size(); i++) {
                    diff = (tempList.get(i) - tempList.get(i - 1)) * increasing;
                    if (diff < 1 || diff > 3) {
                        break;
                    } else if(i == tempList.size() - 1){
                        successfulRun = true;
                    }
                }
            }

            safeReports += (successfulRun) ? 1 : 0;
        }
        System.out.println();
        return safeReports;
    }

    public static void main(String[] args) throws IOException {
        File f = new File("input.txt");
        Scanner scan = new Scanner(f);

        System.out.println("Safe Reports: " + part2Solution(scan));
    }
}
