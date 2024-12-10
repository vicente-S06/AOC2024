import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day1 {
    private static final ArrayList<Integer> leftList = new ArrayList<>();
    private static final ArrayList<Integer> rightList = new ArrayList<>();

    //Returns distance
    public static long part1Solution(){
        Collections.sort(leftList);
        Collections.sort(rightList);
        long distance = 0;
        for(int i = 0; i < leftList.size(); i++) {
            distance += Math.abs(leftList.get(i) - rightList.get(i));
        }

        return distance;
    }

    //Returns comparison Score
    public static long part2Solution(){
        long comparisonScore = 0;

        HashMap<Integer, Integer> appearInRight = new HashMap<>();
        HashMap<Integer, Integer> appearInLeft = new HashMap<>();
        for(Integer k: leftList){
            appearInLeft.put(k, appearInLeft.getOrDefault(k, 0) + 1);
            if(!appearInRight.containsKey(k)) {
                for (Integer m : rightList) {
                    if (k.equals(m)) {
                        appearInRight.put(k, appearInRight.getOrDefault(k, 0) + 1);
                    }
                }
            }
        }

        for(Integer key: appearInRight.keySet()){
            comparisonScore += appearInLeft.get(key) * ((long) key * appearInRight.get(key));
        }
        return comparisonScore;
    }

    public static void main(String[] args) throws IOException {
        File f = new File("input.txt");
        Scanner scan = new Scanner(f);
        while(scan.hasNextLine()) {
            leftList.add(scan.nextInt());
            rightList.add(scan.nextInt());
        }
        scan.close();

        System.out.println("Comparison Score: " + part2Solution());
    }
}