/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.HashMap;
import java.util.Map;

public class NeighboringCells
{
    private Board previousBoard;
    private Map<Coordinate,Cell> liveNeighbors = new HashMap<Coordinate, Cell>();
    private Map<Coordinate,Cell> deadNeighbors = new HashMap<Coordinate, Cell>();

    public NeighboringCells( Board previousBoard, Coordinate coordinate )
    {
        this.previousBoard = previousBoard;
        calcualteNeighbors( coordinate );
    }

    private void calcualteNeighbors( Coordinate coordinate )
    {
        int x = coordinate.getX();
        int y = coordinate.getY();

        addToResult( new Coordinate( x - 1, y - 1 ) );
        addToResult( new Coordinate( x - 1, y     ) );
        addToResult( new Coordinate( x - 1, y + 1 ) );
        addToResult( new Coordinate( x    , y - 1 ) );
        addToResult( new Coordinate( x    , y + 1 ) );
        addToResult( new Coordinate( x + 1, y - 1 ) );
        addToResult( new Coordinate( x + 1, y     ) );
        addToResult( new Coordinate( x + 1, y + 1 ) );
    }

    private void addToResult( Coordinate next )
    {
        if ( previousBoard.containsKey( next ) )
        {
            liveNeighbors.put( next, previousBoard.get( next ) );
        }
        else
        {
            deadNeighbors.put( next, Cell.DEAD );
        }
    }

    public Map<Coordinate, Cell> getLiveNeighbors()
    {
        return liveNeighbors;
    }

    public Map<Coordinate, Cell> getDeadNeighbors()
    {
        return deadNeighbors;
    }
}
