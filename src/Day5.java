import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day5 {
    private static HashMap<Integer, Set<Integer>> map;
    private static int swaps = 0;

    private static int part1Solution(Scanner scan) {
        int sumOfMiddle = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            int[] pages = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

            boolean isUnordered = false;
            for (int i = 0; i < pages.length - 1 && !isUnordered; i++) {
                for (int j = i + 1; j < pages.length && !isUnordered; j++) {
                    if (map.get(pages[i]) == null || !map.get(pages[i]).contains(pages[j])) {
                        isUnordered = true;
                    }

                }
            }

            if (!isUnordered) {
                sumOfMiddle += pages[pages.length / 2];
            }
        }

        return sumOfMiddle;
    }


    private static int part2Solution(Scanner scan) {
        int sumOfMiddle = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            int[] pages = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

            boolean isUnordered = false;
            int i, j = 0;
            for (i = 0; i < pages.length - 1; i++) {
                for (j = i + 1; j < pages.length; j++) {
                    if (map.get(pages[i]) == null || !map.get(pages[i]).contains(pages[j])) {
                        isUnordered = true;

                        int tmp = pages[i];
                        pages[i] = pages[j];
                        pages[j] = tmp;
                        swaps++;

                        i--;
                        break;
                    }
                }
            }

            if (isUnordered) {
                sumOfMiddle += pages[pages.length / 2];
            }
        }

        return sumOfMiddle;
    }

    private static int part2Solution2(Scanner scan) {
        int sumOfMiddle = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            int[] pages = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            int[] sortedPages = Arrays.copyOf(pages, pages.length);

            quickSort(sortedPages, 0, sortedPages.length-1);

            if (!Arrays.equals(pages, sortedPages)) {
                sumOfMiddle += sortedPages[sortedPages.length / 2];
            }
        }

        return sumOfMiddle;
    }

    public static void quickSort(int[] pages, int left, int right) {
        if(left < right) {
            int midpoint = (right + left) / 2;
            int l = left;
            int r = right;
            int pivot = pages[midpoint];

            while (true) {
                while (map.get(pages[l]) != null && map.get(pages[l]).contains(pivot)){
                    l++;
                }
                while (map.get(pivot) != null && map.get(pivot).contains(pages[r])) {
                    r--;
                }
                if (l < r) {
                    int temp = pages[l];
                    pages[l] = pages[r];
                    pages[r] = temp;
                    r--;
                    l++;
                    swaps++;
                }else{
                    break;
                }
            }
            quickSort(pages, left, r);
            quickSort(pages, r + 1, right);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        map = new HashMap<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.isEmpty()) break;

            int x = Integer.parseInt(line.substring(0, line.indexOf('|')));
            int y = Integer.parseInt(line.substring(line.indexOf('|') + 1));

            if (!map.containsKey(x)) {
                map.put(x, new HashSet<>());
            }
            map.get(x).add(y);
        }

        System.out.println("Part 2 Solution: " + part2Solution2(scan));
        System.out.println("Swaps: " + swaps);
        scan.close();
    }
}
