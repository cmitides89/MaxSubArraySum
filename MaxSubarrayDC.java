
/*************************************************************************
 *
 *  Pace University
 *
 *  Course: CS 608 Algorithms and Computing Theory
 *  Author: Constantin Mitides
 *  Collaborators: Arun Mathew
 *  References:
 * generating random range including negative numbers:
 *  https://stackoverflow.com/questions/27976857/how-to-get-random-number-with-negative-number-in-range
 * 
 *
 *  Assignment: PUT THE ASSIGNMENT NUMBER HERE
 *  Problem: PUT THE MAIN PROBLEM NAME HERE
 *  Description: PUT A BRIEF DESCRIPTION HERE
 *
 *  Input:
 *  Output:
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  COPY SIGNATURE OF VISIBLE METHODS HERE
 *
 *
 *   Remarks
 *   -------
 *
 *   PUT ALL NON-CODING ANSWERS HERE
 *
 *
 *************************************************************************/
import java.util.*;
class MaxSubarrayDC{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the size of n");
        int n = scan.nextInt();
        scan.close();
        // int[] myArray = {-2,1,-3,4,-1,2,1,-5,4};
        int[] myRandArray = randArray(n);
        int high = (myRandArray.length - 1);
        int mss = maxSubArray(myRandArray, 0, high);
        System.out.println("The Max Subarray Sum of the random array of size " +n+" is: " +mss);
    }

    static int[] randArray(int n){
        Random randNegPos = new Random();
        int[] randArray = new int[n];
        for(int i = 0; i < randArray.length; i++){
            randArray[i] = randNegPos.nextInt(10 + 1 + 10) - 10;
            System.out.println(randArray[i]);
        }
        return randArray;
    }
    static int maxSubArray(int[] a, int low, int high ){
        //base case
        if(low == high)
            return a[low];
        
        int mid = (low + high)/2;
        int leftSum = maxSubArray(a, low, mid);
        int rightSum = maxSubArray(a, mid + 1, high);
        int crossSum = maxCrossingSubArray(a, low, mid, high);
        int max;
        
        if(leftSum >= crossSum && leftSum >= rightSum){
            max = leftSum;
        }else if(crossSum >= leftSum && crossSum >= rightSum){
            max = crossSum;
        }else{
            max = rightSum;
        }
        return max;

    }

    public static int maxCrossingSubArray(int[] a, int low, int mid, int high){
        int leftSum = (int) Integer.MIN_VALUE;
        int rightSum = (int) Integer.MIN_VALUE;
        int sum = 0;
        for(int i = mid; i >= low; i--){
            sum = sum + a[i];
            if(sum > leftSum){
                leftSum = sum;
            }
        }
        sum = 0;
        for(int j = mid+1; j <= high; j++){
            sum = sum + a[j];
            if(sum > rightSum){
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }
}
