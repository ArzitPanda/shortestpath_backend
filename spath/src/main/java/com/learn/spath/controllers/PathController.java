package com.learn.spath.controllers;

import com.learn.spath.dtos.ShortestDistanceResponse;
import com.learn.spath.models.Point;
import com.learn.spath.services.path.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/paths")
public class PathController {

    @Autowired
    private PathService pathService;

    @GetMapping("/shortest")
    public ResponseEntity<ShortestDistanceResponse> getShortestPath(@RequestParam Long source, @RequestParam Long destination) {
       ShortestDistanceResponse path = pathService.shortestDistance(source, destination);
        if (path != null) {
            return ResponseEntity.ok(path);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

