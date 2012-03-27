/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main
{

    public static int WIDTH = 75;
    public static int HEIGHT = 20;
    public static int COORDS = 345;
    public static Random random = new Random();

    public static void main( String[] args ) throws InterruptedException
    {
        Game game = new Game( randomIntialState() );
        do
        {
            game.printBoard();
            game.advanceToNextRound();
            Thread.sleep( 250 );
        }
        while ( ! game.over() );
    }

    private static Collection<Coordinate> randomIntialState()
    {
        Set<Coordinate> list = new HashSet<Coordinate>();
        int n = random.nextInt( COORDS ); // inlining this on the next line causes a loss in randomness!?!?
        for ( int i = 0; i < n; i++ )
        {
            list.add( new Coordinate( random.nextInt( WIDTH ), random.nextInt( HEIGHT ) ) );
        }
        return list;
    }

}
