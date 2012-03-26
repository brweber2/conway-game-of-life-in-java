/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.Map;

public class Boundaries
{
    int minX, maxX, minY, maxY;


    public Boundaries( Map<Coordinate, Cell> livingCells, Boundaries lastBoundaries )
    {
        boolean first = true;
        for ( Coordinate coordinate : livingCells.keySet() )
        {
            int x = coordinate.getX();
            int y = coordinate.getY();
            if ( first && lastBoundaries != null )
            {
                // we'll seed with last boundaries to make sure we never get smaller...
                minX = lastBoundaries.getMinX();
                maxX = lastBoundaries.getMaxX();
                minY = lastBoundaries.getMinY();
                maxY = lastBoundaries.getMaxY();
                first = false;
            }
            else if ( first )
            {
                minX = x;
                maxX = x;
                minY = y;
                maxY = y;
                first = false;
            }
            if ( x <= minX ) { minX = x; }
            if ( x >= maxX ) { maxX = x; }
            if ( y <= minY ) { minY = y; }
            if ( y >= maxY ) { maxY = y; }
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
        return "Boundaries{" +
                "minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                '}';
    }
}
