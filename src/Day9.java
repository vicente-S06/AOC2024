import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day9 {
    private static List<Integer> diskBlock = new ArrayList<>();
    private static Map<Integer, Integer> fileSizes = new HashMap<>(); //<ID, Size>

    private static long part1Solution() {
        long fileChecksum = 0;
        int id = 0;
        for(int i = 0; i < diskBlock.size(); i++) {
            if (diskBlock.get(i) == -1) {
                diskBlock.set(i, diskBlock.getLast());
                diskBlock.removeLast();

                i--;
            } else {
                fileChecksum += ((long) diskBlock.get(i) * id);
                id++;
            }
        }

        return fileChecksum;
    }

    private static long part2Solution() {
        long fileChecksum = 0;
        int currID = diskBlock.getLast();
        int currBlockSize;
        int currBlockIdx;

        while(currID >= 0){
            currBlockSize = fileSizes.get(currID);
            currBlockIdx = diskBlock.indexOf(currID);
            int freeSpaceCount = 0;
            int freeSpaceIdx = -1;
            for(int i = 0; i < diskBlock.size() && i < currBlockIdx; i++) {
                if(diskBlock.get(i) == -1) {
                    if(freeSpaceCount <= 0){
                        freeSpaceIdx = i;
                    }
                    freeSpaceCount++;
                }else{
                    freeSpaceCount = 0;
                    freeSpaceIdx = -1;
                }

                if(freeSpaceCount == currBlockSize){
                    for(int j = 0; j < currBlockSize; j++){
                        diskBlock.set(freeSpaceIdx + j, currID);
                        diskBlock.set(currBlockIdx + j, -1);
                    }

                    freeSpaceIdx = -1;
                    freeSpaceCount = 0;
                    break;
                }
            }

            currID--;
        }

        for(int i = 0; i < diskBlock.size(); i++){
            if(diskBlock.get(i) != -1) {
                fileChecksum += diskBlock.get(i) * i;
            }
        }

        return fileChecksum;
    }

    private static void printList(){
        for(Integer ind: diskBlock) {
            if(ind == -1) {
                System.out.print('.');
            }else{
                System.out.print(ind);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("input.txt");
        int id = 0;
        while(fis.available() > 0){
            int tmp = fis.read() - '0';
            for(int i = 0; i < tmp; i++) diskBlock.add((id % 2 == 0)?id/2:-1);

            if(id % 2 == 0){
                fileSizes.put(id/2, tmp);
            }
            id++;
        }

//        for(Map.Entry<Integer, Integer> entry : fileSizes.entrySet()) {
//            System.out.printf("(%d, %d)\n", entry.getKey(), entry.getValue());
//        }

        System.out.println("Part 2 Solution: " + part2Solution());
    }
}