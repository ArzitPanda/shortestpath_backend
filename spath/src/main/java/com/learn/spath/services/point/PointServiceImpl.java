package com.learn.spath.services.point;

import com.learn.spath.dtos.PointDto;
import com.learn.spath.models.Point;
import com.learn.spath.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointServiceImpl implements  PointsService{



        @Autowired
        private PointRepository pointRepository;

        @Override
        public List<Point> getAllPoints() {
            return pointRepository.findAll();
        }

        @Override
        public Point addPoint(PointDto pointDto) {
            Point point = new Point();
            point.setName(pointDto.getName());
            point.setPoiType(pointDto.getPoiType());
            point.setCoords(pointDto.getCoords());
            return pointRepository.save(point);
        }

        @Override
        public Point deletePoint(Long pointId) {
            Optional<Point> point = pointRepository.findById(pointId);
            if (point.isPresent()) {
                pointRepository.delete(point.get());
                return point.get();
            }
            return null;
        }

    @Override
    public Point addConnectionToPoint(List<Long> pointIds, Long parentId) {
        Optional<Point> parentPointOptional = pointRepository.findById(parentId);
        if (parentPointOptional.isPresent()) {
            Point parentPoint = parentPointOptional.get();
            List<Point> connectedPoints = pointRepository.findAllById(pointIds);

            for (Point connectedPoint : connectedPoints) {
                if (!parentPoint.getConnectedNodes().contains(connectedPoint)) {
                    parentPoint.getConnectedNodes().add(connectedPoint);
                    connectedPoint.getConnectedNodes().add(parentPoint);
                    pointRepository.save(connectedPoint);
                }
            }

            return pointRepository.save(parentPoint);
        }
        return null;
    }



    @Override
    public Point getPointById(Long id) {
        Optional<Point> point = pointRepository.findById(id);
        return point.orElse(null);
    }

    @Override
    public List<Point> getConnectedPointsById(Long id) {
        Optional<Point> point = pointRepository.findById(id);

       return point.isPresent()? point.get().getConnectedNodes():null;

    }
}


