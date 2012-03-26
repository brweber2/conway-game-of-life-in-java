/*
 * Copyright (C) 2012 brweber2
 */
package com.brweber2.conway;

public enum Cell
{
    ALIVE(true),
    RESURRECTED(true),
    DEAD(false);

    private final boolean alive;

    private Cell( boolean alive )
    {
        this.alive = alive;
    }

    public boolean alive()
    {
        return alive;
    }

    @Override
    public String toString()
    {
        return "Cell." + this.name() + "{" +
                "alive=" + alive +
                '}';
    }
}
