/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board extends AbstractMap<Coordinate,Cell>
{
    private Board previousBoard;
    private Map<Coordinate, Cell> livingCells;
    private Set<Coordinate> visitedNeighborCoordinates = new HashSet<Coordinate>();

    public Board( Board previousBoard )
    {
        this.previousBoard = previousBoard;
        livingCells = new HashMap<Coordinate, Cell>();
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
        return new BoundingBox( previousBoard, this );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        if ( !super.equals( o ) )
        {
            return false;
        }

        Board board = (Board) o;

        if ( livingCells != null ? !livingCells.equals( board.livingCells ) : board.livingCells != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + ( livingCells != null ? livingCells.hashCode() : 0 );
        return result;
    }

    public boolean visited( Coordinate neighborCoordinate )
    {
        return visitedNeighborCoordinates.contains( neighborCoordinate );
    }

    public void visit( Coordinate neighborCoordinate )
    {
        visitedNeighborCoordinates.add( neighborCoordinate );
    }
}
