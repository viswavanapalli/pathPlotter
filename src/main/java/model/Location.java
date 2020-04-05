package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Double latitude;
    private Double longitude;

    @Override
    public String toString(){
        return (String.valueOf(latitude).concat(",").concat(String.valueOf(longitude)));
    }
}
