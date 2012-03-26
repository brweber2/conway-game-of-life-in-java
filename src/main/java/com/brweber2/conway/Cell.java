/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum Cell
{
    ALIVE(true),
    RESURRECTED(true),
    DEAD(false);

    private final boolean alive;

    private Cell( boolean alive )
    {
        this.alive = alive;
    }

    public boolean alive()
    {
        return alive;
    }

    public Cell getNextState( Map<Coordinate,Cell> neighbors )
    {
        int numberOfLiveNeighbors = getLiveNeighbors( neighbors ).size();
        if ( alive() )
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

    public Set<Coordinate> getLiveNeighbors( Map<Coordinate,Cell> neighbors )
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

    @Override
    public String toString()
    {
        return "Cell." + this.name() + "{" +
                "alive=" + alive +
                '}';
    }
}
