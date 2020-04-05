package service.impl;

import client.GoogleDirectionsClient;
import client.RESTClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;
import constants.PathPlotterConstants;
import model.*;
import model.google.Leg;
import model.google.Route;
import model.google.Step;
import service.PathService;
import utils.PathUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PathServiceImpl implements PathService {

    private RESTClient restClient = new RESTClient();

    private GoogleDirectionsClient googleDirectionsClient = new GoogleDirectionsClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<LatLng> findEvenSpacedPaths(PathRequest pathRequest) throws Exception {
        GoogleDirectionsRequest googleDirectionsRequest = GoogleDirectionsRequest.builder()
                .key(PathPlotterConstants.apiKey)
                .origin(pathRequest.getStartLocation().toString())
                .destination(pathRequest.getDestLocation().toString())
                .unit("metric")
                .build();

        GoogleDirectionsResponse googleDirectionsResponse = googleDirectionsClient.fetchGoogleDiections(googleDirectionsRequest);
        List<LatLng> latLngsInPolyline = new ArrayList<LatLng>();
        List<Route> routes = googleDirectionsResponse.getRoutes();
        for(Route route : routes){
            List<Leg> legs = route.getLegs();
            for(Leg leg : legs){
                List<Step> steps = leg.getSteps();
                for(Step step: steps){
                    List<LatLng> subLatLngs = PathUtils.getLocationsFromPolyline(step.getPolyline().getPoints());
                    latLngsInPolyline.addAll(subLatLngs);
                }
            }
        }

        List<LatLng> evenSpacedPoints = getEvenSpacedListOfLatLng(latLngsInPolyline, 50);
        return evenSpacedPoints;
    }


    @Override
    public List<LatLng> getEvenSpacedListOfLatLng(List<LatLng> inputPoints, double baseDistance){
        List<LatLng> evenSpacedPoints = new ArrayList<LatLng>();

        Iterator<LatLng> inputPointsItr = inputPoints.iterator();
        double offset= baseDistance;
        LatLng prevPoint = inputPointsItr.next();

        while(inputPointsItr.hasNext()){
            LatLng nextPoint = inputPointsItr.next();

//            System.out.println("===========================================================");
//            System.out.println("PrevPoint: " + prevPoint.toString());
//            System.out.println("NextPoint: " + nextPoint.toString());

            double currentDistance = PathUtils.findDistanceBetweenTwoLatLngs(prevPoint, nextPoint);
            if(currentDistance == 0){
                prevPoint = nextPoint;
                continue;
            }

            while(currentDistance > offset){

                LatLng requiredPoint = PathUtils.findPointAtDistanceD(prevPoint, nextPoint, offset);
                evenSpacedPoints.add(requiredPoint);
                offset = baseDistance;
                prevPoint = requiredPoint;
                currentDistance = PathUtils.findDistanceBetweenTwoLatLngs(prevPoint, nextPoint);
            }

            if(currentDistance < offset){
                offset = offset - currentDistance;
                prevPoint = nextPoint;
                continue;
            }

            if(currentDistance == offset){
                offset = baseDistance;
                evenSpacedPoints.add(nextPoint);
                prevPoint = nextPoint;
            }
//            System.out.println("Offset = "+ offset);
        }
        evenSpacedPoints.add(inputPoints.get(inputPoints.size()-1));
        return evenSpacedPoints;
    }

    @Override
    public void findDistanceBetweenConsecutiveLatlng(List<LatLng> latLngs) {
        LatLng prevLatLng = latLngs.get(0);
        LatLng nextLatLng;

        Iterator<LatLng> latLngIterator = latLngs.iterator();
        while(latLngIterator.hasNext()){
            nextLatLng = latLngIterator.next();
//                System.out.println("===========================================================");
//                System.out.println("PrevPoint: " + prevLatLng.toString());
//                System.out.println("NextPoint: " + nextLatLng.toString());
            PathUtils.findDistanceBetweenTwoLatLngs(prevLatLng, nextLatLng);
            prevLatLng = nextLatLng;
        }
    }


}
