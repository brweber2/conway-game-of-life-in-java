/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game
{
    private Map<Coordinate, CellState> currentLivingCells;
    private Map<Coordinate, CellState> nextRoundOfLivingCells = new HashMap<Coordinate, CellState>();
    private Set<Coordinate> visitedNeighborCoordinates = new HashSet<Coordinate>();
    private Boundaries lastBoundaries = null;

    public Game( List<Coordinate> currentLivingCells )
    {
        // initialize our game state
        for ( Coordinate currentLivingCell : currentLivingCells )
        {
            nextRoundOfLivingCells.put( currentLivingCell, CellState.ALIVE );
        }
    }
    
    public Board getBoard()
    {
        return new Board( nextRoundOfLivingCells, lastBoundaries );
    }

    public void advanceToNextRound()
    {
        visitedNeighborCoordinates.clear();
        currentLivingCells = new HashMap<Coordinate, CellState>( nextRoundOfLivingCells );
        nextRoundOfLivingCells.clear();
        for ( Coordinate coordinate : currentLivingCells.keySet() )
        {
            handle( coordinate, currentLivingCells.get( coordinate ) );
        }
    }

    public void handle( Coordinate coordinate, CellState cell )
    {
        Map<Coordinate,CellState> neighboringCells = getNeighboringCells( coordinate );
        CellState nextState = cell.getNextState( neighboringCells );
        if ( nextState.alive() )
        {
            nextRoundOfLivingCells.put( coordinate, nextState );
        }
        if ( cell.alive() )
        {
            // we have have to raise some of our dead neighbor cells...
            for ( Coordinate neighborCoordinate : getDeadNeighbors( neighboringCells ) )
            {
                CellState neighboringCell = neighboringCells.get( neighborCoordinate );
                if ( !visitedNeighborCoordinates.contains( neighborCoordinate ) )
                {
                    visitedNeighborCoordinates.add( neighborCoordinate );
                    handle( neighborCoordinate, neighboringCell );
                }
            }
        }
    }

    private Map<Coordinate,CellState> getNeighboringCells( Coordinate coordinate )
    {
        int x = coordinate.getX();
        int y = coordinate.getY();

        Map<Coordinate,CellState> result = new HashMap<Coordinate,CellState>();
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

    private void addToResult( Map<Coordinate,CellState> result, Coordinate next )
    {
        if ( currentLivingCells.containsKey( next ) )
        {
            result.put( next, currentLivingCells.get( next ) );
        }
        else
        {
            result.put( next, CellState.DEAD );
        }
    }
    
    private Set<Coordinate> getDeadNeighbors( Map<Coordinate,CellState> neighbors )
    {
        Set<Coordinate> list = new HashSet<Coordinate>();
        for ( Coordinate coordinate : neighbors.keySet() )
        {
            CellState neighbor = neighbors.get( coordinate );
            if ( ! neighbor.alive() )
            {
                list.add( coordinate );
            }
        }
        return list;
    }


    public boolean over()
    {
        return nextRoundOfLivingCells.keySet().isEmpty();
    }

    public void printBoard()
    {
        lastBoundaries = getBoard().print();
    }
}
