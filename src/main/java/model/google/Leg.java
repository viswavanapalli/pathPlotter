package model.google;

import lombok.Data;

import java.util.List;

@Data
public class Leg {
    private List<Step> steps;
}
