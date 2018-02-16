import java.util.*;
class OnlineExample{
    public static void main(String[] args){
        int[] myArray = {1,-4,3,-4};
        int myMaxNum = bruteForce(myArray);
        System.out.println(myMaxNum);
    }

    public static int bruteForce(int[] array){
        int max = (int) Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < array.length; i++){
            for(int j=i; j < array.length; j++){
                for(int k = i; k <= j; k++){
                    sum = sum + array[k];
                }
                if(sum > max)
                    max = sum;
                sum = 0;
            }
        }
        return max;
    }
}