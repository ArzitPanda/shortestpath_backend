package com.learn.spath.controllers;

import com.learn.spath.dtos.PointDto;
import com.learn.spath.models.Point;
import com.learn.spath.services.point.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/points")
public class PointsController {

    @Autowired
    private PointsService pointsService;

    @GetMapping
    public ResponseEntity<List<Point>> getAllPoints() {
        List<Point> points = pointsService.getAllPoints();
        return ResponseEntity.ok(points);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Point> getPointById(@PathVariable Long id)
    {
        Point point = pointsService.getPointById(id);
        if(point!=null)
        {
            return  ResponseEntity.ok(point);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("connection/{id}")
    public  ResponseEntity<List<Point>> getConnections(@PathVariable Long id)
    {
        List<Point> points = pointsService.getConnectedPointsById(id);
        if (points!=null && !points.isEmpty())
        {
            return  ResponseEntity.ok(points);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Point> addPoint(@RequestBody PointDto pointDto) {
        Point point = pointsService.addPoint(pointDto);
        return ResponseEntity.ok(point);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Point> deletePoint(@PathVariable Long id) {
        Point point = pointsService.deletePoint(id);
        if (point != null) {
            return ResponseEntity.ok(point);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addConnection")
    public ResponseEntity<Point> addConnectionToPoint(@RequestBody List<Long> pointIds, @RequestParam Long parentId) {
        Point point = pointsService.addConnectionToPoint(pointIds, parentId);
        if (point != null) {
            return ResponseEntity.ok(point);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
