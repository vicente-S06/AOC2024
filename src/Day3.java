import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    private static final StringBuilder str = new StringBuilder();
    private static int part1Solution(){

        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher matcher = pattern.matcher(str.toString());

        int addedMult = 0;

        while(matcher.find()){
            StringBuilder instruction = new StringBuilder(matcher.group());
            int commaIdx = instruction.indexOf(",");
            int a = Integer.parseInt(instruction.substring(4, commaIdx));
            int b = Integer.parseInt(instruction.substring(commaIdx+1, instruction.length() - 1));
            addedMult += a*b;
        }

        return addedMult;
    }

    private static int part2Solution(){
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|don't\\(\\)|do\\(\\)");
        Matcher matcher = pattern.matcher(str.toString());

        int addedMult = 0;
        boolean doReadMult = true;

        while(matcher.find()){
            StringBuilder instruction = new StringBuilder(matcher.group());
            if(instruction.toString().equals("do()")){
                doReadMult = true;
            }else if(instruction.toString().equals("don't()")){
                doReadMult = false;
            }else if(doReadMult){
                int commaIdx = instruction.indexOf(",");
                int a = Integer.parseInt(instruction.substring(4, commaIdx));
                int b = Integer.parseInt(instruction.substring(commaIdx+1, instruction.length() - 1));
                addedMult += a*b;
            }
        }

        return addedMult;
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        while(scan.hasNextLine()) {
            str.append(scan.nextLine());
        }
        scan.close();

        System.out.println("Part 2 Solution: " + part2Solution());
    }
}
