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
    private Map<Coordinate, Cell> currentLivingCells;
    private Map<Coordinate, Cell> nextRoundOfLivingCells = new HashMap<Coordinate, Cell>();
    private Set<Coordinate> visitedNeighborCoordinates = new HashSet<Coordinate>();
    private Boundaries lastBoundaries = null;

    public Game( Collection<Coordinate> currentLivingCells )
    {
        // initialize our game state
        for ( Coordinate currentLivingCell : currentLivingCells )
        {
            nextRoundOfLivingCells.put( currentLivingCell, Cell.ALIVE );
        }
    }
    
    public Board getBoard()
    {
        return new Board( nextRoundOfLivingCells, lastBoundaries );
    }

    public void advanceToNextRound()
    {
        visitedNeighborCoordinates.clear();
        currentLivingCells = new HashMap<Coordinate, Cell>( nextRoundOfLivingCells );
        nextRoundOfLivingCells.clear();
        for ( Coordinate coordinate : currentLivingCells.keySet() )
        {
            handle( coordinate, currentLivingCells.get( coordinate ) );
        }
    }

    public void handle( Coordinate coordinate, Cell cell )
    {
        Map<Coordinate,Cell> neighboringCells = getNeighboringCells( coordinate );
        Cell next = cell.getNextCell( neighboringCells );
        if ( next.alive() )
        {
            nextRoundOfLivingCells.put( coordinate, next );
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
        if ( currentLivingCells.containsKey( next ) )
        {
            result.put( next, currentLivingCells.get( next ) );
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
        return nextRoundOfLivingCells.keySet().isEmpty();
    }

    public void printBoard()
    {
        lastBoundaries = getBoard().print();
    }
}
