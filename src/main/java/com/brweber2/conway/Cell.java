/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.HashSet;
import java.util.Set;

public class Cell
{
    private final CellState cellState;

    public Cell( CellState cellState )
    {
        this.cellState = cellState;
    }

    public CellState getCellState()
    {
        return cellState;
    }

    public CellState getNextState( Set<Cell> neighbors )
    {
        int numberOfLiveNeighbors = getLiveNeighbors( neighbors ).size();
        if ( cellState.alive() )
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
    
    public Set<Cell> getLiveNeighbors( Set<Cell> neighbors )
    {
        Set<Cell> list = new HashSet<Cell>();
        for ( Cell neighbor : neighbors )
        {
            if ( neighbor.getCellState().alive() )
            {
                list.add( neighbor );
            }
        }
        return list;
    }

    @Override
    public String toString()
    {
        return "Cell{" +
                "cellState=" + cellState +
                '}';
    }
}
