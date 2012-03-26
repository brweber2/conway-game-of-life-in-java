/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

public class BoardPrinter
{
    private final Board board;

    public BoardPrinter( Board board )
    {
        this.board = board;
    }

    public void print()
    {
        Boundaries boundaries = board.getBoundaries();
        for ( int y = boundaries.getMinY(); y <= boundaries.getMaxY(); y++ )
        {
            for ( int x = boundaries.getMinX(); x <= boundaries.getMaxX(); x++ )
            {
                if ( board.hasCoordinate( new Coordinate( x, y ) ) )
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
    }
}
