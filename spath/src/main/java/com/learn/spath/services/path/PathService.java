package com.learn.spath.services.path;

import com.learn.spath.dtos.ShortestDistanceResponse;
import com.learn.spath.models.Point;

import java.util.List;


public interface PathService {


    public ShortestDistanceResponse shortestDistance(Long source, Long destination);

}
