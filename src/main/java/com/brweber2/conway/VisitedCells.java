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
    
    public void checkCell( Coordinate coordinate, Cell cell )
    {
        NeighboringCells neighbors = new NeighboringCells( previousBoard, coordinate );
        Cell nextCell = new CellTransition( cell ).getNextCell( neighbors.getLiveNeighbors() );
        if ( nextCell.alive() )
        {
            nextBoard.put( coordinate, nextCell );
        }
    }

    public void checkDeadNeighbors( Coordinate coordinate )
    {
        NeighboringCells neighboringCells = new NeighboringCells( previousBoard, coordinate );
        for ( Coordinate deadNeighborCoordinate : neighboringCells.getDeadNeighbors().keySet() )
        {
            if ( !visitedNeighborCoordinates.contains( deadNeighborCoordinate ) )
            {
                Cell deadNeighboringCell = neighboringCells.getDeadNeighbors().get( deadNeighborCoordinate );
                checkCell( deadNeighborCoordinate, deadNeighboringCell );
                visitedNeighborCoordinates.add( deadNeighborCoordinate );
            }
        }
    }
}
