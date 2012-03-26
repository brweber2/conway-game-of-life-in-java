/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.Collection;

public class BoardBoundaries
{
    int minX, maxX, minY, maxY;
    int offset = 2;

    public BoardBoundaries( BoardBoundaries lastBoardBoundaries, Collection<Coordinate> coordinates )
    {
        minX = lastBoardBoundaries.getMinX();
        maxX = lastBoardBoundaries.getMaxX();
        minY = lastBoardBoundaries.getMinY();
        maxY = lastBoardBoundaries.getMaxY();
        init( true, coordinates );
    }

    public BoardBoundaries( Collection<Coordinate> coordinates )
    {
        init( false, coordinates );
    }
    
    private void init( boolean seeded, Collection<Coordinate> coordinates )
    {
        for ( Coordinate coordinate : coordinates )
        {
            int x = coordinate.getX();
            int y = coordinate.getY();
            if ( !seeded )
            {
                // don't assume min x and y are zero...
                minX = x - offset;
                maxX = x + offset;
                minY = y - offset;
                maxY = y + offset;
                seeded = true;
            }
            if ( x < minX ) { minX = x - offset; }
            if ( x > maxX ) { maxX = x + offset; }
            if ( y < minY ) { minY = y - offset; }
            if ( y > maxY ) { maxY = y + offset; }
        }
    }

    public int getMinX()
    {
        return minX;
    }

    public int getMaxX()
    {
        return maxX;
    }

    public int getMinY()
    {
        return minY;
    }

    public int getMaxY()
    {
        return maxY;
    }

    @Override
    public String toString()
    {
        return "BoardBoundaries{" +
                "minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                '}';
    }
}
