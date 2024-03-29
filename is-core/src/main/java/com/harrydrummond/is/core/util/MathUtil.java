package com.harrydrummond.is.core.util;

import com.harrydrummond.is.core.protection.Point3D;
import com.harrydrummond.is.core.protection.octree.Octane;

import java.util.Arrays;

public final class MathUtil {

    /**
     * Gets max value in integer array in O(n) time
     * @param inputArray Array to get max value from
     * @return Maximum value in array of ints
     */
    public static int getMax(int[] inputArray){
        int maxValue = inputArray[0];
        for(int i=1;i<inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

    /**
     * Gets min value in integer array in O(n) time
     * @param inputArray Array to get min value from
     * @return Minimum value in array of ints
     */
    public static int getMin(int[] inputArray){
        int minValue = inputArray[0];
        for(int i=1;i<inputArray.length;i++){
            if(inputArray[i] < minValue){
                minValue = inputArray[i];
            }
        }
        return minValue;
    }

    /**
     * Gets minimum and maximum values in O(n) time
     * @param inputArray Array to get min and max values from
     * @return Array[min][max] of values
     */
    public static int[] getMinAndMax(int[] inputArray) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int j : inputArray) {
            if (j < minValue) {
                minValue = j;
            }
            if (j > maxValue) {
                maxValue = j;
            }
        }
        return new int[]{minValue,maxValue};
    }

    /**
     * Checks if a cuboid overlaps another octane
     *
     * @param oct Octane to check for
     * @param min Bottom corner to check
     * @param max Top corner to check
     * @return true if overlapping / contained within octane
     */
    public static boolean isCuboidOverlapping(Octane oct, Point3D min, Point3D max) {

        Point3D min1 = oct.getMin();
        Point3D max2 = oct.getMax();

        return isCuboidOverlapping(
                new Point3D (min1.x, min1.y, min1.z),
                new Point3D (max2.x, max2.y, max2.z),
                min,
                max
        );
    }

    /**
     * Checks if a cuboid overlaps another cuboid
     *
     * @param min1 Bottom corner to check 1
     * @param max2 Top corner to check 1
     * @param min Bottom corner to check 2
     * @param max Bottom corner to check 2
     * @return true if is overlapping points
     */
    public static boolean isCuboidOverlapping(Point3D min1, Point3D max2, Point3D min, Point3D max) {

        if(!intersectsDimension(min.x, max.x, min1.x, max2.x))
            return false;

        if(!intersectsDimension(min.y, max.y, min1.y, max2.y))
            return false;

        if(!intersectsDimension(min.z, max.z,min1.z, max2.z))
            return false;

        return true;
    }

    private static boolean intersectsDimension(int aMin, int aMax, int bMin, int bMax) {
        return aMin <= bMax && aMax >= bMin;
    }
}