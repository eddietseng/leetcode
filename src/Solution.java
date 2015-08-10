import java.util.*;

/**
 * Solutions for leet code problems.
 * Created by eddietseng on 6/27/15.
 */
public class Solution {
    /**
     * Fraction to Recurring decimal
     * @param numerator the numerator
     * @param denominator the denominator
     * @return the string with recurring decimal representation
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0 )) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }

        // fractional part
        res.append(".");
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        while (num != 0) {
            map.put((int)num, res.length());

            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey((int)num)) {
                int index = map.get((int)num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
        }
        return res.toString();
    }

    /**
     * Roman to Integer
     */
    public static final HashMap<String,Integer> map;

    static
    {
        map = new HashMap<String,Integer>();
        map.put( "M", 1000 );
        map.put( "D", 500 );
        map.put( "C", 100 );
        map.put( "L", 50 );
        map.put( "X", 10 );
        map.put( "V", 5 );
        map.put( "I", 1 );
    }

    /**
     * Paras a roman string into an integer
     * @param s input roman string
     * @return out put integer
     */
    public static int romanToInt(String s) {

        s = s.trim();

        String[] chars = s.split("");
        int sum = 0;
        if( chars.length > 1 )
        {
            for( int i = 1; i < chars.length; i++ )
            {
                int fValue = map.get( chars[i - 1] );
                int sValue = map.get(chars[i]);
                if( fValue >= sValue )
                {
                    sum += fValue;
                }
                else // fValue < sValue
                {
                    sum += ( sValue - fValue );
                    i++;
                }

                if( i == chars.length - 1 )
                    sum += map.get(chars[(chars.length - 1)]);
            }
        }
        else
          sum = map.get( chars[0] );

        return sum;
    }

    /**
     * Utility method for romanToInt
     * @param s given string
     * @return the value of the string
     */
    public static int convertChar( String s )
    {
        char c = s.charAt(0);
        int value = 0;
        switch( c )
        {
            case 'M':
                value = 1000;
                break;
            case 'D':
                value = 500;
                break;
            case 'C':
                value = 100;
                break;
            case 'L':
                value = 50;
                break;
            case 'X':
                value = 10;
                break;
            case 'V':
                value = 5;
                break;
            case 'I':
                value = 1;
                break;
        }
        return value;
    }

    /**
     * Happy Number
     * @param value the int that will be checked
     * @return true if the given int is a happy value
     */
    public static boolean isHappy( int value )
    {
        HashSet<Integer> set = new HashSet<Integer>();
        int sum = toDigitSum(value);

        while( sum != 1 )
        {
            set.add( sum );
            sum = toDigitSum( sum );

            if( set.contains( sum ) )
                return false;
        }

        return true;
    }

    public static int toDigitSum( int value )
    {
        String inputS = String.valueOf(value);
        char[] chars = inputS.toCharArray();
        int sum = 0;
        for( int i = 0; i < chars.length; i++ )
            sum = sum + (int)Math.pow( Character.getNumericValue( chars[ i ] ), 2 );

        return sum;
    }

    /**
     * Reverse Integer
     * @param x the input int that needs to be reversed
     * @return the reversed integer
     */
    public static int reverse( int x )
    {
        int value = 0;
        while( x != 0 )
        {
            int digit = x % 10;
            int temp  = value * 10 + digit;
            if( ( temp - digit ) / 10 != value )
                return 0;
            value = temp;
            x = x / 10;
        }

        return value;
    }

    /**
     * Checks if the int is Palindrome
     * @param x the int that needs to be tested
     * @return true if the given int is a palindrome
     */
    public static boolean isPalindrome( int x )
    {
        int orig = x;
        int value = 0;
        while( x != 0 )
        {
            int digit = x % 10;
            int temp  = value * 10 + digit;
            if( ( temp - digit ) / 10 != value )
                return false;
            value = temp;
            x = x / 10;
        }

        if( value == orig && orig >= 0 )
            return true;
        return false;
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
     * Invert tree recursive method
     * @param root the tree node that will be invert
     * @return the invert node
     */
    public static TreeNode invertTree(TreeNode root) {
        if( root == null )
            return null;

        TreeNode tn = root;
        TreeNode temp = tn.right;

        tn.right = invertTree( tn.left );
        tn.left = invertTree( temp );

        return tn;
    }

    /**
     * Gets the height of the binary tree
     * @param root the given binary tree
     * @return the height of the given tree
     */
    public static int getBinaryTreeHeight( TreeNode root ) {
        if( root == null )
            return 0;
        else
            return 1 + Math.max( getBinaryTreeHeight( root.left ), getBinaryTreeHeight( root.right ) );
    }

    /**
     * Same as above
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
     * Checks to see if the BinaryTree is balance
     * A Tree consider balance when the depth of the two subtrees of every node never differ by more than 1
     * @param root the given tree
     * @return true if the tree is balance
     */
    public static boolean isBalanced(TreeNode root) {
        if( root == null ) return true;

        int leftHeight = getBinaryTreeHeight( root.left );
        int rightHeight = getBinaryTreeHeight( root.right );
        int diff = leftHeight - rightHeight;
        if( -1 <= diff && diff <= 1  )
            return isBalanced( root.left ) && isBalanced( root.right );

        return false;
    }

    /**
     * Parse String to int
     * @param str given int string
     * @return the int value
     */
    public static int myAtoi(String str) {
        // "" input string
        if( str.length() == 0 ) return 0;

        // Get rid of the whitespaces in front of the String
        String temp = str.trim();

        int index = 0, multiplier = 1, total = 0;

        //symbol check
        if( temp.charAt( index ) == '+' || temp.charAt( index ) == '-' ) {
            multiplier = temp.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        //Walk through each character
        while( index < temp.length() )
        {
            int v = temp.charAt( index ) - '0';
            if( v < 0 || v > 9 )
                break;

            //Overflow check
            if( total > Integer.MAX_VALUE / 10 ||
                    (total == Integer.MAX_VALUE / 10 && v > 7 ) )
                return multiplier == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = total * 10 + v;
            index++;
        }

        return total * multiplier;
    }

    /**
     * Calculates the area of the combined rectangle
     * @param A value x of the lower left point in Rectangle 1
     * @param B value y of the lower left point in Rectangle 1
     * @param C value x of the upper right point in Rectangle 1
     * @param D value y of the upper right point in Rectangle 1
     * @param E value x of the lower left point in Rectangle 2
     * @param F value y of the lower left point in Rectangle 2
     * @param G value x of the upper right point in Rectangle 2
     * @param H value y of the upper right point in Rectangle 2
     * @return The area of the combined rectangle
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int overlapped = 0;

        int areaA = (C-A)*(D-B);
        int areaB = (G-E)*(H-F);

        if( !( E >= C || F >= D || A >= G || B >= H ) ){
            int oX = Math.min(C, G) - Math.max(A, E);
            int oY = Math.min(H, D) - Math.max(F, B);

            overlapped = oX * oY;

        }
        return areaA + areaB - overlapped;
    }

    /**
     * Finds the majority element inside an array
     * The majority number will be greater than half of the array size
     * @param nums the given number array
     * @return the majority number
     */
    public static int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();

        for( int v : nums )
        {
            Integer temp = map.get( v );
            if( temp == null ) {
                map.put(v, 1);
                if( 1 > nums.length/2 )
                    return v;
            }
            else {
                temp++;
                map.put( v, temp );
                if( temp > nums.length/2 )
                    return v;
            }
        }
        return 0;
    }

    /**
     * Gets the Pascal's Triangle row
     * @param rowIndex the given row
     * @return the list of numbers in the given row
     */
    public static List<Integer> getRow( int rowIndex ) {
        List<Integer> list = new ArrayList<Integer>();
        long value = 1; // taking care of overflow
        for( int i = 0; i <= rowIndex; i++){
            list.add( (int)value );
            value = value * ( rowIndex - i ) / ( i + 1 );
        }
        return list;
    }

    /**
     * Returns Pascal's Triangle with the given number of rows
     * @param numRows numbers of rows that will be printed
     * @return a List of pascal triangle rows
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> container = new ArrayList<List<Integer>>();
        for( int i = 0; i < numRows; i++ ) {
            long value = 1;
            List<Integer> row = new ArrayList<Integer>();
            for( int j = 0; j <= i; j++ ) {
                row.add( (int)value );
                value = value * ( i-j )/( j+1 );
            }
            container.add( row );
        }
        return container;
    }

    /**
     * Removes elements from the array
     * @param nums the given array
     * @param val matching value
     * @return the length of the new array
     */
    public static int removeElement(int[] nums, int val) {
        /*int count = 0;
        for( int i = 0; i < nums.length; i++ ) {
            if( nums[i] == val )
                count++;
            else { // move elements down
                nums[i - count] = nums[i];
            }
        }
        System.out.println( Arrays.toString( nums ) );
        return nums.length - count;*/

        int next = 0;
        for(int i = 0; i < nums.length; i++) {
            //if( nums[i]!=val && i!=next++) {
                //nums[next-1] =nums[i]; //??
            //}
            if(nums[i] != val)
                nums[next++] = nums[i];
        }
        System.out.println( Arrays.toString( nums ) );
        return next;
    }

    /**
     * Checks if the given two strings have the same number of characters
     * @param s Given string 1
     * @param t Given String 2
     * @return true if they both contains same number of characters
     */
    public boolean isAnagram(String s, String t) {
        if( s.length() != t.length() ) return false;

        char[] chars = s.toCharArray();
        Map<Character,Long> map = new HashMap<Character,Long>();
        for( char c: chars ) {
            if( map.get( c ) == null )
                map.put( c, (long)1 );
            else
                map.put( c, map.get(c) + 1 );
        }

        Map<Character,Long> map2 = new HashMap<Character,Long>();
        for( char c: t.toCharArray() ) {
            if( map2.get( c ) == null )
                map2.put( c, (long)1 );
            else
                map2.put( c, map2.get(c) + 1 );
        }

        for( Map.Entry<Character,Long> entry: map.entrySet() ) {
            if( ! map2.containsKey( entry.getKey() ) )
                return false;
            // It's better to use compareTo()
            if( map2.get( entry.getKey() ).compareTo( entry.getValue() ) != 0 )
                return false;
        }
        return true;
    }

    /**
     * Test program.
     * @param args arguments
     */
    public static void main( String[] args ) {
        int[] a = new int[]{1, 1, 1, 4, 5};
        System.out.println(removeElement(a, 1));
    }


}