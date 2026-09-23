class Solution {
    public int binarysearch(int[] arr, int k) {
        // Code Here
        int minIndex = 0;
        int maxIndex = arr.length-1;
        int index = -1;
        
        if(arr[minIndex] == k)
        {
            return minIndex;
        }
        if(arr[maxIndex] == k)
        {
            return maxIndex;
        }
        
        while(maxIndex - minIndex > 1)
        {
            int nextMedian = minIndex + ((maxIndex - minIndex)/2);
            if(arr[nextMedian] > k)
            {
                maxIndex = nextMedian;
            }
            else if(arr[nextMedian] < k)
            {
                minIndex = nextMedian;
            }
            else
            {
                index = nextMedian;
                break;
            }
        }
        
        if(index != -1)
        {
            for(int i=index; i>-1; i--)
            {
                if(arr[i] !=k)
                {
                    return i+1;
                }
            }
        }
        
        if(arr[minIndex] == k)
        {
            return minIndex;
        }
        if(arr[maxIndex] == k)
        {
            return maxIndex;
        }
        return -1;
    }
}

/*
https://www.geeksforgeeks.org/problems/binary-search-1587115620/1

Problem Solved Successfully
Suggest Feedback
Test Cases Passed
1117 / 1117
Attempts : Correct / Total
1 / 10
Accuracy :
10%
Points Scored 
2 / 2
Your Total Score:
2 
Time Taken
0.96

*/
