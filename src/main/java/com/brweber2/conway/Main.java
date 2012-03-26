/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main
{
    
    public static int WIDTH = 35;
    public static int HEIGHT = 20;
    public static int COORDS = 345;
    public static Random randomCoords = new Random(  );
    public static Random randomWidth = randomCoords;
    public static Random randomHeight = randomCoords;
    
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

    private static List<Coordinate> randomIntialState()
    {
        Set<Coordinate> list = new HashSet<Coordinate>();
        int n = getRandomNumberOfCoordinates();
        for ( int i = 0; i < n; i++ )
        {
            list.add( getRandomCoordinate() );
        }
        return new ArrayList<Coordinate>( list );
    }

    public static int getRandomNumberOfCoordinates()
    {
        return randomCoords.nextInt( COORDS );
    }
    
    public static Coordinate getRandomCoordinate()
    {
        return new Coordinate( getRandomWidth(), getRandomHeight() );
    }

    public static int getRandomWidth()
    {
        return randomWidth.nextInt( WIDTH );
    }

    public static int getRandomHeight()
    {
        return randomHeight.nextInt( HEIGHT );
    }
}
