/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

public class Coordinate
{
    private final int x;
    private final int y;

    public Coordinate( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        Coordinate that = (Coordinate) o;

        if ( x != that.x )
        {
            return false;
        }
        if ( y != that.y )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString()
    {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
