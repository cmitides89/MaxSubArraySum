
/*************************************************************************
 *
 *  Pace University
 *
 *  Course: CS 608 Algorithms and Computing Theory
 *  Semester: Spring 2018
 *  Author: Constantin Mitides
 *  Collaborators: Arun Mathew
 *  References:
 *  How to generate random range including negative numbers:
 *  https://stackoverflow.com/questions/27976857/how-to-get-random-number-with-negative-number-in-range
 * 
 *  Pseudocode for Kadane's Algorithm:
 *  http://www.algorithmist.com/index.php/Kadane%27s_Algorithm
 * 
 *
 *  Assignment: 1
 *  Problem: Maximum Subarray Problem: finding the contiguous subarray, within an array of numbers that has the largest sum
 *  Description: Compare and varify various inputs for a bruteforce MSS and a divide and conquer MSS, (Kadane's Algorithm included as extra credit.)
 *
 *  Input: Array size (size of N)
 *  Output: Running times of the computed maximum subarray's sum, and the sum for each algorithm.
 *
 *  Visible data fields:
 *  public static int smallestInt
 *
 *  Visible methods:
 *  public static void main(String[] args)
 *  public static int[] randArray(int n)
 *  public static int bruteForceMSS(int arry[])
 *  public static int maxSubArray(int[] a, int low, int high)
 *  public static int maxCrossingSubArray(int[] a, int low, int mid, int high)
 *   
 *
 *
 *
 *   Remarks
 *   -------
 *   Chart of running times observed in nanoseconds:
 *
 *   Size      |    BruteForce O(n^2)      |    Divide and Conquer O(nlgn)   |   Kadane's Algorithm O(n)
 *  --------------------------------------------------------------------------------------------------------
 *  1,000      | 3,831,787                 |  648,922                        |   244,074
 *  --------------------------------------------------------------------------------------------------------
 *  10,000     | 64,010,008                |  1,622,597                      |   501,545
 *  --------------------------------------------------------------------------------------------------------
 *  100,000    | 5,649,614,593             |  8,736,277                      |   3,876,929
 *  --------------------------------------------------------------------------------------------------------
 *  1,000,000  | 568,265,227,174           |  40,061,339                     |   8,675,687
 *  --------------------------------------------------------------------------------------------------------
 *  10,000,000 | 59,647,081,208,124        |  355,860,179                    |   15,722,954
 *  --------------------------------------------------------------------------------------------------------
 *   As the chart above displays, the running time for BruteForce is very much in line with O(n^2). This is shown by the fact that the 
 *   nanoseconds increase by two digits when the size of N becomes significantly large.
 * 
 *   Divide and Conquer on the other hand is very much in line with O(n lgn), as the increase in nanoseconds is by less than two digits, which is proof that the
 *   growth rate is smaller than O(n^2), yet not small enough to be considered linear growth and therefore falls between the growth rates of
 *   n^2 and linear, which is nlgn.
 *   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Extra Credit~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *   Kadane's Algorithm was expected to be O(n) as we can see in the chart, the runtime in nanoseconds is very much inline with 
 *   what we would expect for a linear runtime. The differences in sizes of N make a very minimal change in the increase of time, there is barely 
 *   an increase of a single digit, we could say that the increase is <= 1 digit, therefore we are operating in O(n).
 *
 *************************************************************************/
import java.util.*;

class MaxSubArray{
    
    public static int smallestInt = (int) Integer.MIN_VALUE;
    /***START PROGRAM ************************************************************************************/
    public static void main(String[] args){
        // int[] myTestArray = {-2,1,-3,4,-1,2,1,-5,4};
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the size of n");
        int n = scan.nextInt();
        scan.close();

        int[] myRandArray = randArray(n);
        int high = (myRandArray.length-1);

        long startTime = System.nanoTime();
        int mss = maxSubArray(myRandArray, 0, high);
        System.out.println("The time taken by Divide and Conquer is " + (System.nanoTime() - startTime) + " nanoseconds.");
        System.out.println("The DC Max Subarray Sum of the random array of size " + n + " is " + mss);

        startTime = System.nanoTime();
        int bForceMSS = bruteForceMSS(myRandArray);
        System.out.println("The time taken by Bruteforce is " + (System.nanoTime() - startTime) + " nanoseconds.");
        
        System.out.println("The BF Max Subarray Sum of the random array of size "+n+" is " +bForceMSS);

        startTime = System.nanoTime();
        int kadaneMSS = kadaneMethod(myRandArray);
        System.out.println("The Kadane Max Subarray Sum of the random array size of " +n+ " is " +kadaneMSS);
        System.out.println("The time taken by Kadane's Algorithm is " + (System.nanoTime() - startTime) + " nanoseconds.");

    }
/***END OF START PROGRAM ******************************************************************************* */

    // CREATE A RANDOM ARRAY OF SIZE N
    public static int[] randArray(int n) {
        Random randNegPos = new Random();
        int[] randArray = new int[n];
        System.out.println("generating values for randArray");
        for (int i = 0; i < randArray.length; i++) {
            randArray[i] = randNegPos.nextInt(10 + 1 + 10) - 10;
        }
        return randArray;
    }

    public static int bruteForceMSS(int arry[]) {
        //you want the max to start out at the lowest possible number. 
        int max = smallestInt;
        // System.out.println("max starts at " + max);
        int sum = 0;
        System.out.println("calculating in bruteforce...");
        for (int i = 0; i < arry.length; i++) {
            sum = 0;
            for (int j = i; j < arry.length; j++) {
                sum = sum + arry[j];
                // System.out.print(" sum is: "+sum);
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }
    //This is Divide and Conquer Method
    static int maxSubArray(int[] a, int low, int high) {
        //base case
        if (low == high)
            return a[low];
        int mid = (low + high) / 2;
        int leftSum = maxSubArray(a, low, mid);
        int rightSum = maxSubArray(a, mid + 1, high);
        int crossSum = maxCrossingSubArray(a, low, mid, high);
        int max;

        if (leftSum >= crossSum && leftSum >= rightSum) {
            max = leftSum;
        } else if (crossSum >= leftSum && crossSum >= rightSum) {
            max = crossSum;
        } else {
            max = rightSum;
        }
        return max;

    }
    // MAXCROSSING IN LINEARTIME
    public static int maxCrossingSubArray(int[] a, int low, int mid, int high) {
        int leftSum = smallestInt;
        int rightSum = smallestInt;
        int sum = 0;
        //this loop is for the "left half" of the array
        for (int i = mid; i >= low; i--) {
            sum = sum + a[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        //reset the sum so we can work on the rightSum
        sum = 0;
        //this loop is for the "right half" of the array
        for (int j = mid + 1; j <= high; j++) {
            sum = sum + a[j];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    //Kadane's Algorithm
    public static int kadaneMethod(int[] array){
    //this will keep track of max sum contiguous segments for all the pos segments
      int maxThusFar = 0; 
    //this will be used to search for all positive contiguous segments 
      int maxEnding = 0;
      for(int i = 0; i < array.length; i++){
          //if maxEnding is negative, ignore it by resetting it to 0
          maxEnding = maxEnding + array[i];
          if(maxEnding < 0)
              maxEnding = 0;
          //we have a positive sum so lets put it in maxThusFar
          if(maxThusFar < maxEnding)
              maxThusFar = maxEnding;
      }
      return maxThusFar;
    }

}