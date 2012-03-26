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
    private Map<Coordinate, Cell> currentLivingCells;
    private Map<Coordinate, Cell> nextRoundOfLivingCells = new HashMap<Coordinate, Cell>();
    private Set<Coordinate> visitedNeighborCoordinates = new HashSet<Coordinate>();
    private Boundaries lastBoundaries = null;

    public Game( List<Coordinate> currentLivingCells )
    {
        // initialize our game state
        for ( Coordinate currentLivingCell : currentLivingCells )
        {
            nextRoundOfLivingCells.put( currentLivingCell, new Cell( CellState.ALIVE ) );
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
        Map<Cell, Coordinate> neighboringCells = getNeighboringCells( coordinate );
        CellState nextState = cell.getNextState( neighboringCells.keySet() );
        if ( nextState.alive() )
        {
            nextRoundOfLivingCells.put( coordinate, new Cell( nextState ) );
        }
        if ( cell.getCellState().alive() )
        {
            // we have have to raise some of our dead neighbor cells...
            for ( Cell neighboringCell : getDeadNeighbors( neighboringCells.keySet() ) )
            {
                Coordinate neighborCoordinate = neighboringCells.get( neighboringCell );
                if ( !visitedNeighborCoordinates.contains( neighborCoordinate ) )
                {
                    visitedNeighborCoordinates.add( neighborCoordinate );
                    handle( neighborCoordinate, neighboringCell );
                }
            }
        }
    }

    private Map<Cell, Coordinate> getNeighboringCells( Coordinate coordinate )
    {
        int x = coordinate.getX();
        int y = coordinate.getY();

        Map<Cell,Coordinate> result = new HashMap<Cell, Coordinate>();
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

    private void addToResult( Map<Cell,Coordinate> result, Coordinate next )
    {
        if ( currentLivingCells.containsKey( next ) )
        {
            result.put( currentLivingCells.get( next ), next );
        }
        else
        {
            result.put( new Cell( CellState.DEAD ), next );
        }
    }
    
    private Set<Cell> getDeadNeighbors( Set<Cell> neighbors )
    {
        Set<Cell> list = new HashSet<Cell>();
        for ( Cell neighbor : neighbors )
        {
            if ( ! neighbor.getCellState().alive() )
            {
                list.add( neighbor );
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
