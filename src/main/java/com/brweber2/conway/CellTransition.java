/*
 * Copyright (C) 2012 brweber
 */
package com.brweber2.conway;

public class CellTransition
{
    private final Cell cell;

    public CellTransition( Cell cell )
    {
        this.cell = cell;
    }

    public Cell getNextCell( NeighboringCells neighbors )
    {
        int numberOfLiveNeighbors = neighbors.getLiveNeighbors().size();
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
}
