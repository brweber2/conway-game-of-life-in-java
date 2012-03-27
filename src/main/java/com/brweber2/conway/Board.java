/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board extends AbstractMap<Coordinate,Cell>
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

    public Board( Board board, Map<Coordinate, Cell> livingCells )
    {
        this.previousBoard = board;
        this.livingCells = livingCells;
    }

    @Override
    public Cell put( Coordinate coordinate, Cell cell )
    {
        return livingCells.put(coordinate,cell);
    }

    @Override
    public Set<Entry<Coordinate, Cell>> entrySet()
    {
        return livingCells.entrySet();
    }

    public BoundingBox getBoundingBox()
    {
        if ( previousBoard != null )
        {
            return new BoundingBox( previousBoard.getBoundingBox(), keySet() );
        }
        else
        {
            return new BoundingBox( keySet() );
        }
    }
}
