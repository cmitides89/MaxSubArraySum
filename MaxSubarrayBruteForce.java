/*************************************************************************
 *
 *  Pace University
 *
 *  Course: CS 608 Algorithms and Computing Theory
 *  Author: PUT YOUR NAME HERE
 *  Collaborators: PUT YOUR COLLABORATORS HERE, IF NONE, PUT NONE
 *  References: PUT THE LINKS TO YOUR SOURCES HERE
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
class MaxSubarrayBruteForce{
    static int smallestInt = (int) Integer.MIN_VALUE;
    static int largestInt = (int) Integer.MAX_VALUE;

    public static void main(String[] args){
       
        Scanner scan = new Scanner(System.in);
        // System.out.println("please type the int size of the Array");
        // int arrySize = scan.nextInt();
        int[] arry = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("the following are in the array...");
        // int[] myArray = randomArray(arrySize);
        System.out.print("\n"+"\n");
        // int[] myArray = 
        // System.out.println("the MSS is....");
        int mySum = bruteForceMSS(arry);
        System.out.println("The MSS is: "+mySum);
        // for(int i = 0; i < myArray.length; i++){
        //     System.out.println(" "+myArray[i]);
        // }
    }

    public static int[] randomArray(int size){
        // System.out.println(size + " is the size");
        Random rand = new Random();
        int[] returnAry = new int[size];

        for(int i =0; i < returnAry.length; i++){
            int randomNum = rand.nextInt(100);
            // int randomNum = (rand.nextInt(largestInt)- smallestInt);
            System.out.print(" "+randomNum);
            returnAry[i] = randomNum;
        }
		return returnAry;
    }
    public static int bruteForceMSS(int arry[]){
        //negative infinity?
        //you want the max to start out at the lowest possible number. 
        int max = smallestInt;
        // System.out.println("max starts at " + max);
        int sum = 0;
        for(int i = 0; i < arry.length; i++){
            sum = 0;
            for(int j = i; j < arry.length; j++){
                sum = sum + arry[j];
                // System.out.print(" sum is: "+sum);
                if (sum > max) {
                    max = sum;
                }
            }
            
            
        }
		return max;

    }
}