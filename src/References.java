import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
}
