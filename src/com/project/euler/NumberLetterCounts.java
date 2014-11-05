package com.project.euler;

public class NumberLetterCounts
{

    public static String one = "One";

    public static String two = "two";

    public static String three = "three";

    public static String four = "four";

    public static String five = "five";

    public static String six = "six";

    public static String seven = "seven";

    public static String eight = "eight";

    public static String nine = "nine";

    public static String ten = "ten";

    public static String twenty = "twenty";

    public static String thirty = "thirty";

    public static String fourty = "forty";

    public static String fifty = "fifty";

    public static String sixty = "sixty";

    public static String seventy = "seventy";

    public static String eighty = "eighty";

    public static String ninety = "ninety";

    public static String hundred = "hundred";

    public static String thousand = "thousand";


    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int ret = 0;
        for (int i = 1; i <= 1000; i++)
        {
            final String word = getWord(i);
            System.out.println(word);
            ret = ret + word.length();
        }
        System.out.println(ret);
    }

    private static String getWord(final int no)
    {
        final String number = "" + no;
        if (no == 1000)
        {
            return one + thousand;
        }
        String ret = "";
        for (int i = number.length() - 1; i >= 0; i--)
        {
            final int digit = (number.length() - 1) - i;
            final int value = Integer.parseInt("" + number.charAt(i));
            switch (value)
            {
                case 1:
                    switch (digit)
                    {
                        case 2:
                            ret = ret.equals("") ? one + hundred : one + hundred + "and" + ret;
                            break;
                        case 1:
                            final int previousValue = Integer.parseInt("" + number.charAt(i + 1));
                            switch (previousValue)
                            {
                                case 1:
                                    ret = "eleven";
                                    break;
                                case 2:
                                    ret = "twelve";
                                    break;
                                case 3:
                                    ret = "thirteen";
                                    break;
                                case 4:
                                    ret = "fourteen";
                                    break;
                                case 5:
                                    ret = "fifteen";
                                    break;
                                case 6:
                                    ret = "sixteen";
                                    break;
                                case 7:
                                    ret = "seventeen";
                                    break;
                                case 8:
                                    ret = "eighteen";
                                    break;
                                case 9:
                                    ret = "nineteen";
                                    break;
                                case 0:
                                    ret = "ten";
                                    break;
                            }
                            break;
                        case 0:
                            ret = one + ret;
                            break;
                    }
                    break;
                case 2:
                    switch (digit)
                    {
                        case 2:
                            ret = ret.equals("") ? two + hundred : two + hundred + "and" + ret;
                            break;
                        case 1:
                            ret = twenty + ret;
                            break;
                        case 0:
                            ret = two + ret;
                            break;
                    }
                    break;
                case 3:
                    switch (digit)
                    {
                        case 2:
                            ret = ret.equals("") ? three + hundred : three + hundred + "and" + ret;
                            break;
                        case 1:
                            ret = thirty + ret;
                            break;
                        case 0:
                            ret = three + ret;
                            break;
                    }
                    break;
                case 4:
                    switch (digit)
                    {
                        case 2:
                            ret = ret.equals("") ? four + hundred : four + hundred + "and" + ret;
                            break;
                        case 1:
                            ret = fourty + ret;
                            break;
                        case 0:
                            ret = four + ret;
                            break;
                    }
                    break;
                case 5:
                    switch (digit)
                    {
                        case 2:
                            ret = ret.equals("") ? five + hundred : five + hundred + "and" + ret;
                            break;
                        case 1:
                            ret = fifty + ret;
                            break;
                        case 0:
                            ret = five + ret;
                            break;
                    }
                    break;
                case 6:
                    switch (digit)
                    {
                        case 2:
                            ret = ret.equals("") ? six + hundred : six + hundred + "and" + ret;
                            break;
                        case 1:
                            ret = sixty + ret;
                            break;
                        case 0:
                            ret = six + ret;
                            break;
                    }
                    break;
                case 7:
                    switch (digit)
                    {
                        case 2:
                            ret = ret.equals("") ? seven + hundred : seven + hundred + "and" + ret;
                            break;
                        case 1:
                            ret = seventy + ret;
                            break;
                        case 0:
                            ret = seven + ret;
                            break;
                    }
                    break;
                case 8:
                    switch (digit)
                    {
                        case 2:
                            ret = ret.equals("") ? eight + hundred : eight + hundred + "and" + ret;
                            break;
                        case 1:
                            ret = eighty + ret;
                            break;
                        case 0:
                            ret = eight + ret;
                            break;
                    }
                    break;
                case 9:
                    switch (digit)
                    {
                        case 2:
                            ret = ret.equals("") ? nine + hundred : nine + hundred + "and" + ret;
                            break;
                        case 1:
                            ret = ninety + ret;
                            break;
                        case 0:
                            ret = nine + ret;
                            break;
                    }
                    break;
            }
        }

        return ret;
    }


}
