package service;

import com.google.maps.model.LatLng;
import model.Location;
import model.PathRequest;

import java.util.List;

public interface PathService {

    public List<LatLng> findEvenSpacedPaths(PathRequest pathRequest) throws Exception;

    public List<LatLng> getEvenSpacedListOfLatLng(List<LatLng> inputPoints, double baseDistance);

}