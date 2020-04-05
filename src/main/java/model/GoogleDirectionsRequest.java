package model;

import constants.PathPlotterConstants;
import enums.Mode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoogleDirectionsRequest {
    private String origin;
    private String destination;
    private String key;
    private String unit;
//    private String mode;

    @Override
    public String toString(){
        return "origin="
                .concat(origin.toString())
                .concat("&")
                .concat("destination=")
                .concat(destination.toString())
                .concat("&")
                .concat("key=")
                .concat(PathPlotterConstants.apiKey)
                .concat("&")
                .concat("unit=")
                .concat(unit);

    }
}
