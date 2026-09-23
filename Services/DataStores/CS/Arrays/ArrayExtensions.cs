public class ArrayExtensions
{
    //

    public string GetLongestCommonPrefix(string[] strs) {
        string commonPrefix = "";
        int stringsCount = strs.Length;
        if(stringsCount > 0)
        {
            commonPrefix = strs[0];
            int currentStringLength = 0;

            for(int i=1; i< stringsCount; i++)
            {
                currentStringLength = strs[i].Length;
                if(currentStringLength < commonPrefix.Length)
                {
                    commonPrefix = commonPrefix.Substring(0, currentStringLength);
                }
                if(commonPrefix.Length == 0)
                {
                    break;
                }

                for(int j=0; j<currentStringLength; j++)
                {
                    if(j > commonPrefix.Length-1)
                    {
                        break;
                    }
                    if(strs[i][j] != commonPrefix[j])
                    {
                        commonPrefix = commonPrefix.Substring(0, j);
                    }
                }
            }
        }
        return commonPrefix;
    }

    public void MergeArrays(int[] nums1, int m, int[] nums2, int n) {

     for (int i=m+n-1; i>=0; i--)   
     {
        if(n == 0)
        {
            break;
        }

        int maxValue = nums2[n-1];
        if(m > 0)
        {
            if(nums1[m-1] > maxValue)
            {
                maxValue = nums1[m-1];
                m--;
            }            
            else
            {
                n--;
            }
        }
        else
        {
            n--;
        }
        nums1[i] = maxValue;
     }
    }
}