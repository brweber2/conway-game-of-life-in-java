/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.HashSet;
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

    public CellState getNextState( Set<CellState> neighbors )
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

    public Set<CellState> getLiveNeighbors( Set<CellState> neighbors )
    {
        Set<CellState> list = new HashSet<CellState>();
        for ( CellState neighbor : neighbors )
        {
            if ( neighbor.alive() )
            {
                list.add( neighbor );
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
