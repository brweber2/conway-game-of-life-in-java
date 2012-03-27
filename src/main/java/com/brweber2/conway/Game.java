/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

import java.util.Collection;

public class Game
{
    private Board previousBoard = null;
    private Board nextBoard = new Board(previousBoard);

    public Game( Collection<Coordinate> initialBoard )
    {
        // initialize our game state
        for ( Coordinate coordinate : initialBoard )
        {
            nextBoard.put( coordinate, Cell.ALIVE );
        }
    }

    public void advanceToNextRound()
    {
        previousBoard = nextBoard;
        nextBoard = new Board(previousBoard);
        for ( Coordinate coordinate : previousBoard.keySet() )
        {
            handle( coordinate, previousBoard.get( coordinate ) );
        }
    }

    public void handle( Coordinate coordinate, Cell cell )
    {
        NeighboringCells neighbors = new NeighboringCells( previousBoard, coordinate );
        Cell nextCell = new CellTransition( cell ).getNextCell( neighbors.getLiveNeighbors() );
        if ( nextCell.alive() )
        {
            nextBoard.put( coordinate, nextCell );
        }
        // we have have to raise some of our dead neighbor cells...
        nextBoard.getVisitedCells().checkDeadNeighbors( neighbors );
    }

    public boolean over()
    {
        return nextBoard.isEmpty() || nextBoard.equals( previousBoard );
    }

    public void printBoard()
    {
        new BoardPrinter( nextBoard ).print();
    }
}
