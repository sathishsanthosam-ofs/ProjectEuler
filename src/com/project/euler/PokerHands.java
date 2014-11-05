package com.project.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHands
{

    public PokerHands()
    {

    }

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final PokerHands ph = new PokerHands();
        BufferedReader br;
        try
        {
            br = new BufferedReader(new FileReader(new File(
                    "C:/Documents and Settings/sathishkumars/My Documents/poker.txt")));
            String temp = null;
            int ret = 0;
            while ((temp = br.readLine()) != null)
            {
                final Hands firstPlayerHand = ph.getHands(temp.substring(0, 14).split(" "));
                final Hands secoHandPlayerHand = ph.getHands(temp.substring(15).split(" "));
                if (firstPlayerHand.compare(secoHandPlayerHand) > 0)
                {
                    ret++;
                    System.out.println("First Player wins : " + firstPlayerHand);
                }
                else
                {
                    System.out.println("Second Player wins : " + secoHandPlayerHand);
                }
            }
            System.out.println(ret);
        }
        catch (final FileNotFoundException e)
        {

        }
        catch (final IOException e)
        {

        }


    }

    public Hands getHands(final String[] cards)
    {
        final List<Integer> values = new ArrayList<Integer>();
        final List<Character> suits = new ArrayList<Character>();
        final List<Integer> pairs = new ArrayList<Integer>();
        final List<Integer> three = new ArrayList<Integer>();
        final List<Integer> four = new ArrayList<Integer>();
        for (final String card : cards)
        {
            int value = 2;
            final char c = card.charAt(0);
            try
            {
                value = Integer.parseInt("" + c);
            }
            catch (final Exception e)
            {
                if (c == 'T')
                {
                    value = 10;
                }
                else if (c == 'J')
                {
                    value = 11;
                }
                else if (c == 'Q')
                {
                    value = 12;
                }
                else if (c == 'K')
                {
                    value = 13;
                }
                else
                {
                    value = 14;
                }
            }

            final char suit = card.charAt(1);
            if (values.contains(value))
            {
                pairs.add(value);
                final int index = values.indexOf(value);
                values.remove(index);
            }
            else if (pairs.contains(value))
            {
                three.add(value);
                final int index = pairs.indexOf(value);
                pairs.remove(index);
            }
            else if (three.contains(value))
            {
                four.add(value);
                final int index = three.indexOf(value);
                three.remove(index);
            }
            else
            {
                values.add(value);
            }
            if (!suits.contains(suit))
            {
                suits.add(suit);
            }
        }
        // sort the values to check for stright
        Collections.sort(values);
        final int size = values.size();
        int startValue = size > 0 ? values.get(0) : 0;
        final HighCard highcard = new HighCard(size == 0 ? 2 : values.get(size - 1));
        boolean stright = size == 0 ? false : size == 5 ? true : false;
        for (int i = 1; i < size; i++)
        {
            final int current = values.get(i);
            if (current != startValue + 1)
            {
                stright = false;
                break;
            }
            startValue = current;
        }
        if (stright && suits.size() == 1)
        {
            if (values.get(0) == 10)
            {
                return new RoyalFlush();
            }
            else
            {
                return new StrightFlush(values.get(0));
            }
        }
        else if (stright)
        {
            return new Stright(values.get(0));
        }
        else if (suits.size() == 1)
        {
            return new Flush(suits.get(0));
        }
        if (four != null && four.size() > 0)
        {
            return new FourOfAKind(four.get(0));
        }
        if (three != null && three.size() > 0 && pairs.size() > 0)
        {
            return new FullHouse(new ThreeOfAKind(three.get(0)), new Pair(pairs.get(0), highcard));
        }
        else if (three != null && three.size() > 0)
        {
            return new ThreeOfAKind(three.get(0));
        }
        else if (pairs != null && pairs.size() > 0)
        {
            final List<Pair> pairlist = new ArrayList<PokerHands.Pair>();
            for (final Integer pair : pairs)
            {
                pairlist.add(new Pair(pair, highcard));
            }
            if (pairlist.size() == 1)
            {
                return pairlist.get(0);
            }
            else
            {
                return new TwoPair(pairlist, highcard);
            }
        }
        else
        {
            return highcard;
        }
    }

    interface Hands
    {
        public int getRank();

        public int compare(Hands other);
    }

    class HighCard
            implements Hands
    {
        int highCard;

        public HighCard(final int highCard)
        {
            this.highCard = highCard;
        }

        public int compare(final Hands other)
        {
            HighCard hc = null;
            if (other instanceof HighCard)
            {
                hc = (HighCard) other;
            }
            else
            {
                if (other.getRank() > getRank())
                {
                    return -1;

                }
                else
                {
                    return 1;
                }
            }
            if (this.highCard > hc.highCard)
            {
                return 1;
            }
            else if (this.highCard == hc.highCard)
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }

        @Override
        public int getRank()
        {
            return 1;
        }

        public String toString()
        {
            return "High Card with " + highCard;
        }
    }

    class Pair
            implements Hands
    {
        int value;

        HighCard highcard;

        public Pair(final int value, final HighCard highcard)
        {
            this.value = value;
            this.highcard = highcard;
        }

        public int compare(final Hands hand)
        {
            Pair other = null;
            if (hand instanceof Pair)
            {
                other = (Pair) hand;
            }
            else
            {
                if (hand.getRank() > getRank())
                {
                    return -1;

                }
                else
                {
                    return 1;
                }
            }
            if (this.value > other.value)
            {
                return 1;
            }
            else if (this.value == other.value)
            {
                return this.highcard.compare(other.highcard);

            }
            else
            {
                return -1;
            }
        }

        @Override
        public int getRank()
        {
            return 2;
        }

        public String toString()
        {
            return "A Pair of " + value + " " + highcard.toString();
        }
    }

    class TwoPair
            implements Hands
    {
        List<Pair> pairs;

        HighCard highcard;

        public TwoPair(final List<Pair> pairs, final HighCard highCard)
        {
            this.pairs = pairs;
            this.highcard = highCard;
        }

        @Override
        public int getRank()
        {
            return 3;
        }

        @Override
        public int compare(final Hands hand)
        {
            TwoPair other = null;
            if (hand instanceof TwoPair)
            {
                other = (TwoPair) hand;
            }
            else
            {
                if (hand.getRank() > getRank())
                {
                    return -1;

                }
                else
                {
                    return 1;
                }
            }
            final int firstPlayerHighestPair = getHighestPair();
            final int secondPlayerHighestPair = other.getHighestPair();
            if (firstPlayerHighestPair > secondPlayerHighestPair)
            {
                return 1;
            }
            else if (firstPlayerHighestPair == secondPlayerHighestPair)
            {
                return this.highcard.compare(other.highcard);

            }
            else
            {
                return -1;
            }
        }

        public int getHighestPair()
        {
            int ret = 0;
            for (final Pair pair : pairs)
            {
                if (pair.value > ret)
                {
                    ret = pair.value;
                }
            }
            return ret;
        }

        public String toString()
        {
            return "Two Pairs with Highest of  " + getHighestPair() + " ";
        }
    }

    class ThreeOfAKind
            implements Hands
    {
        int value;

        public ThreeOfAKind(final int value)
        {
            this.value = value;
        }


        @Override
        public int getRank()
        {
            return 4;
        }

        @Override
        public int compare(final Hands hand)
        {
            ThreeOfAKind other = null;
            if (hand instanceof ThreeOfAKind)
            {
                other = (ThreeOfAKind) hand;
            }
            else
            {
                if (hand.getRank() > getRank())
                {
                    return -1;

                }
                else
                {
                    return 1;
                }
            }
            return this.value > other.value ? 1 : -1;
        }

        public String toString()
        {
            return "Three of a Kind :  " + value;
        }

    }

    class Stright
            implements Hands
    {

        int startValue;

        public Stright(final int startValue)
        {
            this.startValue = startValue;
        }

        @Override
        public int getRank()
        {
            return 5;
        }

        @Override
        public int compare(final Hands hand)
        {
            Stright other = null;
            if (hand instanceof Stright)
            {
                other = (Stright) hand;
            }
            else
            {
                if (hand.getRank() > getRank())
                {
                    return -1;

                }
                else
                {
                    return 1;
                }
            }
            return this.startValue > other.startValue ? 1 : -1;
        }

        public String toString()
        {
            return "Stringt starts with :  " + startValue;
        }
    }

    class Flush
            implements Hands
    {
        char suit;

        public Flush(final char suit)
        {
            this.suit = suit;
        }

        @Override
        public int getRank()
        {
            return 06;
        }

        @Override
        public int compare(final Hands hand)
        {
            Flush other = null;
            if (hand instanceof Flush)
            {
                other = (Flush) hand;
            }
            else
            {
                if (hand.getRank() > getRank())
                {
                    return -1;

                }
                else
                {
                    return 1;
                }
            }
            return this.suit > other.suit ? 1 : -1;
        }

        public String toString()
        {
            return "Flush with :  " + suit;
        }

    }

    class FullHouse
            implements Hands
    {

        ThreeOfAKind threeOfAKind;

        Pair pair;

        public FullHouse(final ThreeOfAKind threeOfAKind, final Pair pair)
        {
            this.threeOfAKind = threeOfAKind;
            this.pair = pair;
        }

        @Override
        public int getRank()
        {
            return 7;
        }

        @Override
        public int compare(final Hands hand)
        {

            FullHouse other = null;
            if (hand instanceof FullHouse)
            {
                other = (FullHouse) hand;
            }
            else
            {
                if (hand.getRank() > getRank())
                {
                    return -1;

                }
                else
                {
                    return 1;
                }
            }
            final int compare = this.threeOfAKind.compare(other.threeOfAKind);
            if (compare != 0)
            {
                return compare;
            }
            else
            {
                return this.pair.compare(other.pair);
            }

        }

        public String toString()
        {
            return "Full House  :  " + threeOfAKind.toString() + " : " + pair.toString();
        }
    }

    class FourOfAKind
            implements Hands
    {
        int value;

        public FourOfAKind(final int value)
        {
            this.value = value;
        }

        @Override
        public int getRank()
        {
            return 8;
        }

        @Override
        public int compare(final Hands hand)
        {
            FourOfAKind other = null;
            if (hand instanceof FourOfAKind)
            {
                other = (FourOfAKind) hand;
            }
            else
            {
                if (hand.getRank() > getRank())
                {
                    return -1;

                }
                else
                {
                    return 1;
                }
            }
            return this.value > other.value ? 1 : -1;
        }

        public String toString()
        {
            return "Four of a Kind :  " + value;
        }

    }

    class StrightFlush
            implements Hands
    {
        int startValue;

        public StrightFlush(final int startValue)
        {
            this.startValue = startValue;
        }

        @Override
        public int getRank()
        {
            return 9;
        }

        @Override
        public int compare(final Hands hand)
        {
            StrightFlush other = null;
            if (hand instanceof StrightFlush)
            {
                other = (StrightFlush) hand;
            }
            else
            {
                if (hand.getRank() > getRank())
                {
                    return -1;

                }
                else
                {
                    return 1;
                }
            }
            return this.startValue > other.startValue ? 1 : -1;
        }

        public String toString()
        {
            return "String Flush with stat value  :  " + startValue;
        }
    }

    class RoyalFlush
            implements Hands
    {

        @Override
        public int getRank()
        {
            return 10;
        }

        @Override
        public int compare(final Hands other)
        {
            return 1;
        }

        public String toString()
        {
            return "Royal Flush";
        }
    }

}
