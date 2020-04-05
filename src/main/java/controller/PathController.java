package controller;

import com.google.maps.model.LatLng;
import model.PathRequest;
import service.PathService;
import service.impl.PathServiceImpl;
import utils.PathUtils;

import java.util.Iterator;
import java.util.List;

public class PathController {

    private PathService pathService = new PathServiceImpl();

    public List<LatLng> findPath(PathRequest pathRequest) throws Exception {
        List<LatLng> evenSpacedPoints = pathService.findEvenSpacedPaths(pathRequest);
        System.out.println("Evenly Spaced Points are : ");
        System.out.println(evenSpacedPoints);
        return evenSpacedPoints;
    }

    public void findDistance(List<LatLng> latLngs) {
        pathService.findDistanceBetweenConsecutiveLatlng(latLngs);
    }
}
