package com.learn.spath.services.point;

import com.learn.spath.dtos.PointDto;
import com.learn.spath.models.Point;

import java.util.List;

public interface PointsService {

    public List<Point> getAllPoints();
    public  Point addPoint(PointDto pointDto);
    public Point deletePoint(Long pointId);
    public Point addConnectionToPoint(List<Long> pointIds,Long parentId);
    public Point getPointById(Long id);

    public List<Point> getConnectedPointsById(Long id);

}
