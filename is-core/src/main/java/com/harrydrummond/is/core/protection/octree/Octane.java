package com.harrydrummond.is.core.protection.octree;

import com.harrydrummond.is.core.protection.Point3D;
import com.harrydrummond.is.core.protection.Polygon3D;

public class Octane extends Polygon3D {

    /**
     * Creates Octane cuboid from two points which must be minimum and maximum bounds
     * @param min Minimum corner of octane
     * @param max Maximum corner of octane
     */
    public Octane(final Point3D min, final Point3D max){
        super(min,max);
    }

    /**
     * Checks if the vector point is between the Octane Bounds
     * @param p Point to check
     * @return True If point is between the octane's boundaries
     */
    public boolean isBetweenAxis(Point3D p){
        return ((p.x > getMin().x) && (p.x < getMax().x) && (p.y > getMin().y) && (p.y < getMax().y) && (p.z > getMin().z) && (p.z < getMax().z));
    }

}