package com.harrydrummond.is.core.protection.octree;

import com.harrydrummond.is.core.protection.Point3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public final class OctreeHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OctreeHandler.class);

    private final Map<String, Octree> trees = new HashMap<>();

    /**
     * Creates new octree with bounds provided under unique name. If name
     * is in use, method returns false
     * @param bound1 Boundary 1
     * @param bound2 Boundary 2
     * @param name Unique tree name
     * @return False if name is already in use. True if successfully made octree
     */
    public boolean monitorNewTree(Point3D bound1, Point3D bound2, String name) {
        if (treeExists(name)) return false;
        Octree octree = new Octree(bound1, bound2);
        trees.put(name, octree);
        LOGGER.info("New octree ({}) registered with bounds ({},{},{}), ({},{},{})", name, bound1.x, bound1.y, bound1.z,bound2.x,bound2.y,bound2.z);
        return true;
    }

    /**
     * Retrieves tree with provided name
     * @param name Name of tree
     * @return Tree object if exists else null
     */
    public Octree getTree(String name) {
        return trees.get(name);
    }

    /**
     * Checks if tree with specified name is already being monitored
     * @param name Name to check
     * @return True if tree key was found in monitored map. False if key could not be found
     */
    public boolean treeExists(String name) {
        return trees.containsKey(name);
    }
}