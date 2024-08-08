package com.learn.spath.utils;

import com.learn.spath.models.Coords;

public class DistanceUtils {

    public static double getLinearDistance(Coords one, Coords two) {
        final int R = 6371; // Radius of the Earth in kilometers

        double lat1 = Math.toRadians(one.getLattitude());
        double lon1 = Math.toRadians(one.getLongitude());
        double lat2 = Math.toRadians(two.getLattitude());
        double lon2 = Math.toRadians(two.getLongitude());

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c*1000; // Distance in kilometers
    }


}
