/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.Collection;

public class Boundaries
{
    int minX, maxX, minY, maxY;

    public Boundaries( Boundaries lastBoundaries , Collection<Coordinate> coordinates )
    {
        minX = lastBoundaries.getMinX();
        maxX = lastBoundaries.getMaxX();
        minY = lastBoundaries.getMinY();
        maxY = lastBoundaries.getMaxY();
        init( true, coordinates );
    }

    public Boundaries( Collection<Coordinate> coordinates )
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
                minX = x;
                maxX = x;
                minY = y;
                maxY = y;
                seeded = true;
            }
            if ( x < minX ) { minX = x; }
            if ( x > maxX ) { maxX = x; }
            if ( y < minY ) { minY = y; }
            if ( y > maxY ) { maxY = y; }
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
