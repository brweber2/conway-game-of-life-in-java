/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game
{
    private Board previousBoard;
    private Board nextBoard = new Board();
    private Set<Coordinate> visitedNeighborCoordinates = new HashSet<Coordinate>();

    public Game( Collection<Coordinate> initialBoard )
    {
        // initialize our game state
        for ( Coordinate coordinate : initialBoard )
        {
            nextBoard.put( coordinate, Cell.ALIVE );
        }
    }
    
    public BoardPrinter getBoard()
    {
        return new BoardPrinter( nextBoard );
    }

    public void advanceToNextRound()
    {
        visitedNeighborCoordinates.clear();
        previousBoard = new Board( nextBoard.getLivingCells() );
        nextBoard = new Board( previousBoard );
        for ( Coordinate coordinate : previousBoard.getCoordinates() )
        {
            handle( coordinate, previousBoard.get( coordinate ) );
        }
    }

    public void handle( Coordinate coordinate, Cell cell )
    {
        Map<Coordinate,Cell> neighboringCells = getNeighboringCells( coordinate );
        Cell next = new CellTransition( cell ).getNextCell( neighboringCells );
        if ( next.alive() )
        {
            nextBoard.put( coordinate, next );
        }
        if ( cell.alive() )
        {
            // we have have to raise some of our dead neighbor cells...
            for ( Coordinate neighborCoordinate : getDeadNeighbors( neighboringCells ) )
            {
                Cell neighboringCell = neighboringCells.get( neighborCoordinate );
                if ( !visitedNeighborCoordinates.contains( neighborCoordinate ) )
                {
                    visitedNeighborCoordinates.add( neighborCoordinate );
                    handle( neighborCoordinate, neighboringCell );
                }
            }
        }
    }

    private Map<Coordinate,Cell> getNeighboringCells( Coordinate coordinate )
    {
        int x = coordinate.getX();
        int y = coordinate.getY();

        Map<Coordinate,Cell> result = new HashMap<Coordinate,Cell>();
        addToResult( result, new Coordinate( x - 1, y - 1 ) );
        addToResult( result, new Coordinate( x - 1, y     ) );
        addToResult( result, new Coordinate( x - 1, y + 1 ) );
        addToResult( result, new Coordinate( x    , y - 1 ) );
        addToResult( result, new Coordinate( x    , y + 1 ) );
        addToResult( result, new Coordinate( x + 1, y - 1 ) );
        addToResult( result, new Coordinate( x + 1, y     ) );
        addToResult( result, new Coordinate( x + 1, y + 1 ) );

        return result;
    }

    private void addToResult( Map<Coordinate,Cell> result, Coordinate next )
    {
        if ( previousBoard.hasCoordinate( next ) )
        {
            result.put( next, previousBoard.get( next ) );
        }
        else
        {
            result.put( next, Cell.DEAD );
        }
    }
    
    private Set<Coordinate> getDeadNeighbors( Map<Coordinate,Cell> neighbors )
    {
        Set<Coordinate> list = new HashSet<Coordinate>();
        for ( Coordinate coordinate : neighbors.keySet() )
        {
            Cell neighbor = neighbors.get( coordinate );
            if ( ! neighbor.alive() )
            {
                list.add( coordinate );
            }
        }
        return list;
    }


    public boolean over()
    {
        return nextBoard.isEmpty();
    }

    public void printBoard()
    {
        getBoard().print();
    }
}
