package com.learn.spath.dtos;

import com.learn.spath.models.Point;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor

public class ShortestDistanceResponse {

    private double distance;
    private List<Point> points;

}
