package com.harrydrummond.is.core.protection.octree;

import com.harrydrummond.is.core.protection.Point3D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OctaneTest {


    //todo more tests with proper range bound checking.
    @Test
    public void OctaneBoundCheckingTest1() {
        Octane octane = new Octane(new Point3D(0,0,0), new Point3D(4,4,4));

        assertTrue(octane.isBetweenAxis(new Point3D(1,1,1)));
        assertTrue(octane.isBetweenAxis(new Point3D(2,2,2)));
        assertTrue(octane.isBetweenAxis(new Point3D(3,3,3)));

        assertFalse(octane.isBetweenAxis(new Point3D(-5,1,1)));
        assertFalse(octane.isBetweenAxis(new Point3D(5,1,1)));

        assertFalse(octane.isBetweenAxis(new Point3D(1,-5,1)));
        assertFalse(octane.isBetweenAxis(new Point3D(1,5,1)));

        assertFalse(octane.isBetweenAxis(new Point3D(1,1,-5)));
        assertFalse(octane.isBetweenAxis(new Point3D(1,1,5)));

        assertFalse(octane.isBetweenAxis(new Point3D(0,0,0)));
        assertFalse(octane.isBetweenAxis(new Point3D(4,4,4)));


    }

}