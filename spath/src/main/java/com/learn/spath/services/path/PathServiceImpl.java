package com.learn.spath.services.path;

import com.learn.spath.dtos.ShortestDistanceResponse;
import com.learn.spath.models.Point;
import com.learn.spath.models.PointDistance;
import com.learn.spath.services.point.PointsService;
import com.learn.spath.utils.DistanceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PathServiceImpl implements PathService {

    @Autowired
    private PointsService pointsService;

    @Autowired
    public PriorityQueue<PointDistance> Legs;

    @Override
    public ShortestDistanceResponse shortestDistance(Long sourceId, Long destinationId) {
        Map<Long, Double> distances = new HashMap<>();
        Map<Long, Point> previousPoints = new HashMap<>();

        Point source = pointsService.getPointById(sourceId);
        Point destination = pointsService.getPointById(destinationId);

        distances.put(sourceId, 0.0);
        Legs.add(new PointDistance(0.0, source));

        while (!Legs.isEmpty()) {
            PointDistance current = Legs.poll();
            Point currentPoint = pointsService.getPointById(current.getPoint().getId());




            for (Point neighbor : currentPoint.getConnectedNodes()) {


                double newDist = distances.get(currentPoint.getId()) + DistanceUtils.getLinearDistance(currentPoint.getCoords(), neighbor.getCoords());

                if (newDist < distances.getOrDefault(neighbor.getId(), Double.MAX_VALUE)) {
                    distances.put(neighbor.getId(), newDist);
                    previousPoints.put(neighbor.getId(), currentPoint);
                    Legs.add(new PointDistance(newDist, neighbor));
                }
            }
        }

        List<Point> path = new ArrayList<>();
        for (Point at = destination; at != null; at = previousPoints.get(at.getId())) {
            path.add(at);
        }
        Collections.reverse(path);

        return  new ShortestDistanceResponse(distances.getOrDefault(destinationId,0.0),path);
    }
}
