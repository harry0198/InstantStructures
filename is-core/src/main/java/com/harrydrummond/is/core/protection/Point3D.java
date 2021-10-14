package com.harrydrummond.is.core.protection;

/**
 * POJO for defining a point in a coordinate based 3d space
 */
public final class Point3D {

    public final int x,y,z;

    /**
     * Class Constructor
     * @param x X Coordinate
     * @param y Y Coordinate
     * @param z Z Coordinate
     */
    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}