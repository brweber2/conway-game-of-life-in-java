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
    
    public static int WIDTH = 35;
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
            Thread.sleep( 500 );
        } 
        while ( ! game.over() );
    }

    private static Collection<Coordinate> randomIntialState()
    {
        Set<Coordinate> list = new HashSet<Coordinate>();
        for ( int i = 0; i < random.nextInt(COORDS); i++ )
        {
            list.add( new Coordinate( random.nextInt(WIDTH), random.nextInt(HEIGHT) ) );
        }
        return list;
    }

}
