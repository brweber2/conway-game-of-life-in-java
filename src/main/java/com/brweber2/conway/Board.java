/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.HashMap;
import java.util.Map;

public class Board
{
    private Map<Coordinate, Cell> livingCells = new HashMap<Coordinate, Cell>();
    private Boundaries lastBoundaries;

    public Board( Map<Coordinate, Cell> livingCells, Boundaries lastBoundaries )
    {
        this.livingCells = livingCells;
        this.lastBoundaries = lastBoundaries;
    }

    public Boundaries print()
    {
        Boundaries boundaries = new Boundaries( livingCells, lastBoundaries );
        for ( int y = boundaries.getMinY(); y <= boundaries.getMaxY(); y++ )
        {
            for ( int x = boundaries.getMinX(); x <= boundaries.getMaxX(); x++ )
            {
                Coordinate coordinate = new Coordinate( x, y );
                if ( livingCells.containsKey( coordinate ) )
                {
                    System.err.print( "*" );
                }
                else
                {
                    System.err.print( "." );
                }
            }
            System.err.println();
        }
        System.err.println();
        return boundaries;
    }
}
