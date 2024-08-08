package com.learn.spath.dtos;

import com.learn.spath.models.Coords;
import com.learn.spath.models.PoiType;
import lombok.Data;

@Data
public class PointDto {

    private String  name;
    private PoiType poiType;
    private Coords coords;

}
