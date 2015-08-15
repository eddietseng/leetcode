import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Leet code references.
 * Created by eddietseng on 8/8/15.
 */
public class References {
    /**
     * Fraction to Recurring decimal
     * @param numerator the numerator
     * @param denominator the denominator
     * @return the string with recurring decimal representation
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuilder strB = new StringBuilder();

        if( !( ((long)numerator*(long)denominator) >= 0 ) )
            strB.append( "-" );

        long n = Math.abs( (long)numerator );
        long d = Math.abs( (long)denominator );

        long rem = Math.abs( n % d );
        long q = n / d;

        strB.append( q );
        if( rem == 0 )
            return strB.toString();

        strB.append( "." );
        HashMap<Long,Integer> map = new HashMap<Long,Integer>();
        while( rem != 0 )
        {
            map.put( rem, strB.length() );

            rem *= 10;

            strB.append( rem / d );
            rem %= d;
            if( map.get( rem ) != null )
            {
                int index = map.get( rem );
                strB.insert( index, "(" ).append(")");
                break;
            }
        }

        return strB.toString();
    }

    /**
     * Print Pascal's Triangle
     */
    public static void printPascalTriangle() {
        for (int n = 0; n < 31; n++) {
            int nCk = 1;
            for (int k = 0; k <= n; k++) {
                System.out.print(nCk + " ");
                nCk = nCk * (n-k) / (k+1);
            }
            System.out.println();
        }
    }

    /**
     * Prints a given row of Pascal's Triangle
     * @param x given row that needs to be printed
     */
    public static void printPascalTriangleRow( int x ) {
        long nCk = 1;
        for (int k = 0; k <= x; k++) {
            System.out.print(nCk + " ");
            nCk = nCk * (x-k) / (k+1);
        }
    }

    /**
     * Gets the given row of Pascal's Triangle
     * This solution is from leet code website
     * @param rowIndex given row
     * @return list of the number that are on the row
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        for(int i = 0;i<rowIndex+1;i++) {
            res.add(1);
            for(int j=i-1;j>0;j--) {
                res.set(j, res.get(j-1)+res.get(j));
            }
        }
        return res;
    }

    /**
     * This is my own String to int method.
     * Leet code is asking for something that can convert
     * "  - 12a134" into -12.
     * I don't agree with that...
     * @param str given String that needs to be converted into int
     * @return the converted int value
     */
    public static int myStringToInt(String str) {
        if( str.trim().length() > 0 )
        {
            Pattern pattern = Pattern.compile( "^\\d+$" );
            Matcher matcher = pattern.matcher( str.trim() );
            if( !pattern.matcher( str.trim().substring(1) ).find()
                    && str.trim().length() > 1 ) //Learn regex
                return 0;
            else
            {
                try{
                    if( str.trim().startsWith( "-" )
                            ||str.trim().startsWith( "+" ) )
                        return Integer.parseInt(str.trim());
                    else if( ! matcher.find() )
                        return 0;
                    else
                        return Integer.parseInt(str.trim());
                } catch( Exception e ) {
                    return 0;
                }
            }
        }
        return 0;
    }

    /**
     * Tree node model
     */
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * This is a leet code question
     * @param root the given binary tree
     * @return the height of the given tree
     */
    public int maxDepth(TreeNode root) {
        if( root == null )
            return 0;
        else
            return 1 + Math.max( maxDepth(root.left), maxDepth(root.right) );
    }

    /**
     * check the balance of the given binary tree
     * Solution from leet code website
     * @param root binary tree
     * @return true if balance
     */
    public boolean isBalanced(TreeNode root) {
        return getH(root)!=-1;
    }

    /**
     * -1 is a flag to avoid trivial recursion
     * @param node
     * @return
     */
    public int getH(TreeNode node){
        if(node==null)
            return 0;
        int leftH = getH(node.left);
        if(leftH==-1)
            return -1;
        int rightH = getH(node.right);
        if(rightH==-1)
            return -1;
        if(Math.abs(leftH-rightH)>1)
            return -1;
        return Math.max(rightH, leftH)+1;
    }

    /**
     * Returns the length of the last word
     * @param s Given string
     * @return the last word length
     */
    public int lengthOfLastWord(String s) {
        int last = s.lastIndexOf( " " );
        return s.length() - last - 1;
    }

    /**
     * Using bit count.
     * Since the binary of a power of two will
     * always contain one 1.
     * @param n the given number
     * @return true is the number is the power of two
     */
    public boolean isPowerOfTwo(int n) {
        return n>0 && Integer.bitCount(n) == 1;
    }
    /**
     * Power of 2 means only one bit of n is '1'
     * so use the trick n&(n-1)==0 to judge whether that is the case
     */
    public boolean isPowerOfTwo2(int n) {
        return ((n & (n-1))==0 && n>0);
    }

    /**
     * set remove isn't free
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }

    /**
     * Displays the range of a given array
     * leet code
     * @param nums given int array
     * @return a List of ranges
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list=new ArrayList<String>();
        /*if(nums.length==1){
            list.add(nums[0]+"");
            return list;
        }*/
        for(int i=0;i<nums.length;i++){
            int a=nums[i];
            while(i+1<nums.length&&(nums[i+1]-nums[i])==1){
                i++;
            }
            if(a!=nums[i]){
                list.add(a+"->"+nums[i]);
            }else{
                list.add(a+"");
            }
        }
        return list;
    }

    /**
     * Convert string to zig zag
     * @param s string that will be converted
     * @param nRows rows that starts to turn
     * @return the converted string
     */
    public String convert(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }
}
