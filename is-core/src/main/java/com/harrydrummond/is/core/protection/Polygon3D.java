package com.harrydrummond.is.core.protection;

import com.google.common.collect.ImmutableList;
import com.harrydrummond.is.core.util.MathUtil;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 3 Dimensional plane model for defining bounds and shape
 */
public class Polygon3D {

    private final ImmutableList<Point3D> points;
    private Point3D min, max;
    private Area area;

    /**
     * Constructor to create Polygon3D Object.
     * Creates a 3 dimensional polygon based off provided points with support of unlimited
     * points being provided. If 2 points are provided, it is converted into a cuboid. If less
     * than 2 points are provided an IllegalArgumentException is thrown.
     * <p>
     * Polygon will be constructed linearly to the order they appear in the list
     *
     * @param points points used to create the object
     * @throws IllegalArgumentException if less than 2 points are provided
     */
    public Polygon3D(final List<Point3D> points) {
        checkNotNull(points);
        if (points.size() < 2) throw new IllegalArgumentException("More than 2 points required!");
        // If we only have min and max corner of area
        if (points.size() == 2) {
            List<Point3D> pointList = new ArrayList<>();
            // get all 4 corners
            Point3D p1 = new Point3D(points.get(0).x, points.get(0).y, points.get(0).z);
            Point3D p2 = new Point3D(points.get(1).x, points.get(1).y, points.get(1).z);
            Point3D p3 = new Point3D(p1.x, Math.min(p1.y,p2.y), p2.y); // Sets Y to Min point of Y
            Point3D p4 = new Point3D(p2.x, Math.max(p1.y,p2.y), p1.y); // Sets Y to Max point Y

            pointList.add(p1);
            pointList.add(p2);
            pointList.add(p3);
            pointList.add(p4);

            this.points = ImmutableList.copyOf(pointList);

            return;
        }
        this.points = ImmutableList.copyOf(points);
    }

    public Polygon3D(final Point3D point1, final Point3D point2) {
        this(Arrays.asList(checkNotNull(point1), checkNotNull(point2)));
    }

    /**
     * Gets the minimum point bound in bounding box. Minimum point is calculated upon method call
     * and stored for later use (lazy initialization)
     * @return Minimum point of polygon bounding box
     */
    public Point3D getMin() {
        if (min != null) return min;

        min = new Point3D(
                MathUtil.getMin(getPoints().stream().mapToInt(p -> p.x).toArray()),
                MathUtil.getMin(getPoints().stream().mapToInt(p -> p.y).toArray()),
                MathUtil.getMin(getPoints().stream().mapToInt(p -> p.z).toArray())
        );
        return min;
    }

    /**
     * Gets the maximum point bound in bounding box. Minimum point is calculated upon method call
     * and stored for later use (lazy initialization)
     * @return Maximum point of polygon bounding box
     */
    public Point3D getMax() {
        if (max != null) return max;
        max = new Point3D(
                MathUtil.getMax(getPoints().stream().mapToInt(p -> p.x).toArray()),
                MathUtil.getMax(getPoints().stream().mapToInt(p -> p.y).toArray()),
                MathUtil.getMax(getPoints().stream().mapToInt(p -> p.z).toArray())
        );
        return max;
    }

    /**
     * Gets accurate area of polygon shape across 2d space in a coordinate based system.
     * Area is calculated upon method call and stored for later use (lazy initialization)
     * @return 2d Area of polygon points in coordinate based system
     */
    public Area getArea() {
        if (area != null) return area;

        int numPoints = getPoints().size();
        int[] xCoords = new int[numPoints];
        int[] zCoords = new int[numPoints];

        int i = 0;
        for (Point3D point : getPoints()) {
            xCoords[i] = point.x; // X
            zCoords[i] = point.z; // Z
            i++;
        }

        Polygon polygon = new Polygon(xCoords, zCoords, numPoints);
        area = new Area(polygon);
        return area;
    }

    /**
     * Returns immutable list of points that form the polygon shape
     * @return
     */
    public ImmutableList<Point3D> getPoints() {
        return points;
    }
}