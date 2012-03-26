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
        BoardBoundaries boardBoundaries = board.getBoundaries();
        for ( int y = boardBoundaries.getMinY(); y <= boardBoundaries.getMaxY(); y++ )
        {
            for ( int x = boardBoundaries.getMinX(); x <= boardBoundaries.getMaxX(); x++ )
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
