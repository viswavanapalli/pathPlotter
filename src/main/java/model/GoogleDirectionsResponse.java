package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.google.Route;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleDirectionsResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private List<Route> routes;
}
