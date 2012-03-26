/*
 * Copyright (C) 2012 brweber
 */
package com.brweber2.conway;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CellTransition
{
    private final Cell cell;

    public CellTransition( Cell cell )
    {
        this.cell = cell;
    }

    public Cell getNextCell( Map<Coordinate, Cell> neighbors )
    {
        int numberOfLiveNeighbors = getLiveNeighbors( neighbors ).size();
        if ( cell.alive() )
        {

            if ( numberOfLiveNeighbors < 2 )
            {
                return Cell.DEAD;
            }
            else if ( numberOfLiveNeighbors == 2 || numberOfLiveNeighbors == 3 )
            {
                return Cell.ALIVE;
            }
            else
            {
                return Cell.DEAD;
            }
        }
        else
        {
            if ( numberOfLiveNeighbors == 3 )
            {
                return Cell.RESURRECTED;
            }
            return Cell.DEAD;
        }
    }

    private Set<Coordinate> getLiveNeighbors( Map<Coordinate,Cell> neighbors )
    {
        Set<Coordinate> list = new HashSet<Coordinate>();
        for ( Coordinate coordinate : neighbors.keySet() )
        {
            Cell neighbor = neighbors.get( coordinate );
            if ( neighbor.alive() )
            {
                list.add( coordinate );
            }
        }
        return list;
    }
}
