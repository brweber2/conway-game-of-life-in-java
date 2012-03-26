/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum CellState
{
    ALIVE(true),
    RESURRECTED(true),
    DEAD(false);

    private final boolean alive;

    private CellState( boolean alive )
    {
        this.alive = alive;
    }

    public boolean alive()
    {
        return alive;
    }

    public CellState getNextState( Map<Coordinate,CellState> neighbors )
    {
        int numberOfLiveNeighbors = getLiveNeighbors( neighbors ).size();
        if ( alive() )
        {

            if ( numberOfLiveNeighbors < 2 )
            {
                return CellState.DEAD;
            }
            else if ( numberOfLiveNeighbors == 2 || numberOfLiveNeighbors == 3 )
            {
                return CellState.ALIVE;
            }
            else
            {
                return CellState.DEAD;
            }
        }
        else
        {
            if ( numberOfLiveNeighbors == 3 )
            {
                return CellState.RESURRECTED;
            }
            return CellState.DEAD;
        }
    }

    public Set<Coordinate> getLiveNeighbors( Map<Coordinate,CellState> neighbors )
    {
        Set<Coordinate> list = new HashSet<Coordinate>();
        for ( Coordinate coordinate : neighbors.keySet() )
        {
            CellState neighbor = neighbors.get( coordinate );
            if ( neighbor.alive() )
            {
                list.add( coordinate );
            }
        }
        return list;
    }

    @Override
    public String toString()
    {
        return "CellState." + this.name() + "{" +
                "alive=" + alive +
                '}';
    }
}
