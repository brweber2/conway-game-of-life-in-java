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
        int offset = 2;
        BoundingBox boundingBox = board.getBoundingBox();
        for ( int y = boundingBox.getMinY() - offset; y <= boundingBox.getMaxY() + offset; y++ )
        {
            for ( int x = boundingBox.getMinX() - offset; x <= boundingBox.getMaxX() + offset; x++ )
            {
                if ( board.containsKey( new Coordinate( x, y ) ) )
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
