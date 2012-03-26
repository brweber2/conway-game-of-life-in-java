/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Board
{
    private Map<Coordinate, Cell> livingCells;
    private Board previousBoard = null;

    public Board()
    {
        this.livingCells = new HashMap<Coordinate, Cell>();
    }

    public Board( Board board )
    {
        this();
        this.previousBoard = board;
    }

    public Board( Map<Coordinate, Cell> livingCells )
    {
        this.livingCells = livingCells;
    }

    public boolean hasCoordinate( Coordinate coordinate )
    {
        return livingCells.containsKey( coordinate );
    }

    public Collection<Coordinate> getCoordinates()
    {
        return livingCells.keySet();
    }

    public Cell get( Coordinate coordinate )
    {
        return livingCells.get( coordinate );
    }

    public boolean isEmpty()
    {
        return livingCells.isEmpty();
    }

    public void put( Coordinate coordinate, Cell cell )
    {
        livingCells.put( coordinate, cell );
    }

    public Boundaries getBoundaries()
    {
        if ( previousBoard != null )
        {
            return new Boundaries( previousBoard.getBoundaries(), getCoordinates() );
        }
        else
        {
            return new Boundaries( getCoordinates() );
        }
    }

    public Map<Coordinate, Cell> getLivingCells()
    {
        return livingCells;
    }
}
