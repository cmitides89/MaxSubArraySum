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
class MaxSubarrayDC {
    public static void main(String[] args) {

        int[] myArray = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int high = (myArray.length - 1);
        int mss = maxSubArray(myArray, 0, high);
        System.out.println(mss);
    }

    public static int maxSubArray(int[] a, int low, int high) {
        // int leftSum;
        // int rightSum;
        // int crossSum;
        // int max;

        //base case
        if (high == low) {
            return a[low];
        } else {
            int mid = (low + high) / 2;
            //recursive call
            // leftSum = maxSubArray(a, low, mid);
            // rightSum = maxSubArray(a, mid+1, high);
            // crossSum = maxCrossingSubArray(a, low, mid, high);

            // if(leftSum >= crossSum && leftSum >= rightSum){
            //     max = leftSum;
            // } else if(crossSum >= leftSum && crossSum >= rightSum){
            //     max = crossSum;
            // }else{
            //     max = rightSum;
            // }
            return Math.max(Math.max(maxSubArray(a, low, high), maxSubArray(a, mid + 1, high)),
                    maxCrossingSubArray(a, low, mid, high));
        }
    }

    public static int maxCrossingSubArray(int[] a, int low, int mid, int high) {
        int leftSum = (int) Integer.MIN_VALUE;
        int rightSum = (int) Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + a[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum = sum + a[j];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

}
