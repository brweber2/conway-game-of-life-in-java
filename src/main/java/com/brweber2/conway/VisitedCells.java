/*
 * Copyright (C) 2012 brweber
 */
package com.brweber2.conway;

import java.util.HashSet;
import java.util.Set;

public class VisitedCells
{
    private Set<Coordinate> visitedNeighborCoordinates = new HashSet<Coordinate>();
    private Board previousBoard;
    private Board nextBoard;

    public VisitedCells( Board previousBoard, Board nextBoard )
    {
        this.previousBoard = previousBoard;
        this.nextBoard = nextBoard;
    }

    public void checkDeadNeighbors( NeighboringCells neighboringCells )
    {
        for ( Coordinate deadNeighborCoordinate : neighboringCells.getDeadNeighbors().keySet() )
        {
            if ( !visitedNeighborCoordinates.contains( deadNeighborCoordinate ) )
            {
                Cell neighboringCell = neighboringCells.getDeadNeighbors().get( deadNeighborCoordinate );
                NeighboringCells deadCellNeighbors = new NeighboringCells( previousBoard, deadNeighborCoordinate );
                Cell nextCell = new CellTransition( neighboringCell ).getNextCell( deadCellNeighbors.getLiveNeighbors() );
                if ( nextCell.alive() )
                {
                    nextBoard.put( deadNeighborCoordinate, nextCell );
                }
                visitedNeighborCoordinates.add( deadNeighborCoordinate );
            }
        }
    }
}
