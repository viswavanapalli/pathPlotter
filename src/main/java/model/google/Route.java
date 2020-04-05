package model.google;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;
    public List<Leg> legs;


}
