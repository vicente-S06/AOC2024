import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day7 {

    private static long part1Solution(Scanner scan){
        long totalCalibration = 0;

        while(scan.hasNextLine()){
            StringBuilder line = new StringBuilder(scan.nextLine());

            long resultValue = Long.parseLong(line.substring(0, line.indexOf(":")));
            line.delete(0, line.indexOf(":") + 2);

            int[] values = Arrays.stream(line.toString().split(" ")).mapToInt(Integer::parseInt).toArray();

            int operationOrder = (int)Math.pow(2, values.length - 1) - 1; //Using binary value to decide mult or add
            while(operationOrder >= 0){
                int tmpOp = operationOrder;
                long equationRes = values[0];
                for(int i = 1; i < values.length; i++){
                    if((tmpOp & 1) == 1){ //IF 1, multiply. If 0, add.
                        equationRes *= values[i];
                    }else{
                        equationRes += values[i];
                    }
                    tmpOp >>= 1;
                }
                if(equationRes == resultValue){
                    totalCalibration = Math.addExact(totalCalibration, resultValue);
                    //totalCalibration += resultValue;
                    break;
                }
                operationOrder--;
            }

        }

        return totalCalibration;
    }

    private static long part2Solution(Scanner scan){
        long totalCalibration = 0;

        while(scan.hasNextLine()){
            StringBuilder line = new StringBuilder(scan.nextLine());

            long resultValue = Long.parseLong(line.substring(0, line.indexOf(":")));
            line.delete(0, line.indexOf(":") + 2);

            int[] values = Arrays.stream(line.toString().split(" ")).mapToInt(Integer::parseInt).toArray();

            int operationOrder = (int)Math.pow(3, values.length - 1) - 1; //Using binary value to decide mult or add

            while(operationOrder >= 0){
                int tmpOp = operationOrder;
                long equationRes = values[0];
                for(int i = 1; i < values.length; i++){
                    if((tmpOp % 3) == 1){ //IF 1, multiply. If 0, add.
                        equationRes *= values[i];
                    }else if((tmpOp % 3) == 0){
                        equationRes += values[i];
                    }else{ // if 3 concat
                        equationRes *= (long)Math.pow(10, (int)Math.floor(Math.log10(values[i])) + 1);
                        equationRes += values[i];
                    }
                    tmpOp /= 3;
                }
                if(equationRes == resultValue){
                    totalCalibration += resultValue;
                    break;
                }
                operationOrder--;
            }

        }

        return totalCalibration;
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        System.out.println("Part 2 Solution: "+ part2Solution(scan));
    }
}
