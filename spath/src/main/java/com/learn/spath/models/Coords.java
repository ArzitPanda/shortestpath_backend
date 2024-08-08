package com.learn.spath.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coords {

    private double lattitude;
    private double longitude;

}
