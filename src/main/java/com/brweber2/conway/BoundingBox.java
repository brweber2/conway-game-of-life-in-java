/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.Collection;

public class BoundingBox
{
    int minX, maxX, minY, maxY;

    public BoundingBox( Board previousBoard, Board board )
    {
        boolean seeded = false;
        if ( previousBoard != null )
        {
            // we don't ever want to get smaller than the previous board so the size isn't jumping all over the place...
            BoundingBox lastBoundingBox = previousBoard.getBoundingBox();
            seed( lastBoundingBox.getMinX(), lastBoundingBox.getMinY() );
            check( lastBoundingBox.getMaxX(), lastBoundingBox.getMaxY() );
            seeded = true;
        }
        init( seeded, board.keySet() );
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
                seed( x, y );
                seeded = true;
            }
            check( x, y );
        }
    }

    private void seed( int x, int y )
    {
        minX = x;
        maxX = x;
        minY = y;
        maxY = y;
    }

    private void check( int x, int y )
    {
        if ( x < minX ) { minX = x; }
        if ( x > maxX ) { maxX = x; }
        if ( y < minY ) { minY = y; }
        if ( y > maxY ) { maxY = y; }
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
        return "BoundingBox{" +
                "minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                '}';
    }
}
