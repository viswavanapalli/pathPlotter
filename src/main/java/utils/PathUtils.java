package utils;

/**
 *
 * TO Find Radians From latitude and longitude
 * latitude  in radians :- latitudeInDegrees * pi / 180
 * longitude in radians :- longitudeInDegrees* pi / 180
 *
 *
 * TO GET X,Y,Z Coordinates from Latitude, Longitude
 * R -> radius of earch
 * p-> latitude in radians
 * q-> longitude in radians
 * Then,
 * x-> R*Cos(p)*Sin(q)
 * y-> R*Sin(p)
 * z-> R*Cos(p)*Cos(q)
 *
 *
 *
 *
 * displacement between two points in coordinate system
 * sqrt( (diffInX)*(diffInX) + (diffInY)*(diffInY) + (diffInZ)*(diffInZ) )
 *
 * Find angle projected at center by
 * displacement = 2R*Sin(0/2)
 *
 *
 * if this projects an angle 0 onto center,
 * length of arc = R0
 *
 * Assume infinitesimal latitude and longitude grid as a rectangle
 *
 */

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import constants.PathPlotterConstants;
import enums.Parameter;
import model.PathRequest;
import model.Location;
import model.Point;
import java.util.*;

public class PathUtils {

    //FETCHES KEY VALUE PAIR MAP FROM Input string
    public static Map<Parameter, String> getParameterValues(String command) {
        Map<Parameter, String> parameterValues = new HashMap<Parameter, String>();
        String[] data = command.split(" ");
        int i = 0;
        while (i < data.length) {
            String[] parameterKeyValue = data[i].split("=");
            if(parameterKeyValue.length==2){
                parameterValues.put(Parameter.valueOf(parameterKeyValue[0].toUpperCase())
                        , parameterKeyValue[1]);
            }
            i++;
        }
        return parameterValues;
    }

    // Input Parsing
    public static PathRequest getInputRequestFromParameters(Map<Parameter, String> parameterValues){
        PathRequest pathRequest = new PathRequest();
        Location startLocation = new Location();
        Location destLocation = new Location();
        Iterator<Map.Entry<Parameter,String>> mapIterator = parameterValues.entrySet().iterator();
        while(mapIterator.hasNext()){
            Map.Entry<Parameter, String> entry = mapIterator.next();
            switch(entry.getKey()){
                case START_LATITUDE:
                    startLocation.setLatitude(Double.parseDouble(entry.getValue()));
                    break;
                case START_LONGITUDE:
                    startLocation.setLongitude(Double.parseDouble(entry.getValue()));
                    break;
                case DEST_LATITUDE:
                    destLocation.setLatitude(Double.parseDouble(entry.getValue()));
                    break;
                case DEST_LONGITUDE:
                    destLocation.setLongitude(Double.parseDouble(entry.getValue()));
                    break;
            }
        }
        pathRequest.setStartLocation(startLocation);
        pathRequest.setDestLocation(destLocation);
        return pathRequest;
    }

    // Input parsing
    public static LatLng getLatLngFromParameters(Map<Parameter, String> parameterValues){
        LatLng latLng = new LatLng();
        Iterator<Map.Entry<Parameter,String>> mapIterator = parameterValues.entrySet().iterator();
        while(mapIterator.hasNext()){
            Map.Entry<Parameter, String> entry = mapIterator.next();
            switch(entry.getKey()){
                case LATITUDE:
                    latLng.lat = Double.parseDouble(entry.getValue());
                    break;
                case LONGITUDE:
                    latLng.lng = Double.parseDouble(entry.getValue());
                    break;
            }
        }
        return latLng;
    }

    public static double findDistanceBetweenTwoLatLngs(LatLng latLng1, LatLng latLng2){
        double distance;

        double latRadians = latLng1.lat*Math.PI/180;

        double diffLatInRadians = ((latLng2.lat - latLng1.lat)*Math.PI)/180;
        double diffLongInRadians = ((latLng2.lng - latLng1.lng)*Math.PI)/180;

        Point point1 = getPointFromLatLng(latLng1);
        Point point2 = getPointFromLatLng(latLng2);

        //approximated distacne assuming the grid is rectangle
        double distance2 =  Math.sqrt(
                Math.pow(diffLatInRadians*PathPlotterConstants.radius, 2)
                        + Math.pow(diffLongInRadians*PathPlotterConstants.radius*Math.cos(latRadians), 2)
        );

        //more accurate distance calculated from Coordinate geometry
        double displacement = findDisplacement(point1, point2);
        double angleAtCenter = 2* Math.asin(displacement/(2*PathPlotterConstants.radius));
        //distance calculated from Math (coordinate and polar system)
        distance = angleAtCenter *PathPlotterConstants.radius;
//        System.out.println("******Distance between Points ****");
//        System.out.println(distance2 + " :- approx distance");
//        System.out.println(displacement + " :- xyz displacement");
//        System.out.println(distance + " :- xyz distance");
        return distance;
    }

    //Finding displacement between two (X,Y,Z) coordinate points
    public static double findDisplacement(Point point1, Point point2){
        return Math.sqrt(
                Math.pow((point1.getX() - point2.getX()), 2)
                +Math.pow((point1.getY() - point2.getY()), 2)
                +Math.pow((point1.getZ() - point2.getZ()), 2)
        );
    }

    //Finding point at distance d, assuming the grid is a square grid
    public static LatLng findPointAtDistanceD(LatLng latLng1, LatLng latLng2, Double d){
        double distance = findDistanceBetweenTwoLatLngs(latLng1, latLng2);
        double diffLatInRadians = ((latLng2.lat - latLng1.lat)*Math.PI)/180;
        double diffLongInRadians = ((latLng2.lng - latLng1.lng)*Math.PI)/180;


        double distanceInLat = diffLatInRadians * PathPlotterConstants.radius;
        double distanceInLong = diffLongInRadians * PathPlotterConstants.radius*Math.cos(latLng1.lat*Math.PI/180);

        double tan0 = distanceInLat/distanceInLong;
        double sin0 = distanceInLat/distance;
        double cos0 = distanceInLong/distance;

        double dLatDistance = d*sin0;
        double dLongDistance = d*cos0;

        double dLatRadians = dLatDistance/PathPlotterConstants.radius;
        double dLongRadians = dLongDistance/PathPlotterConstants.radius*Math.cos(latLng1.lat*Math.PI/180);

        double dLatDegrees = dLatRadians * 180/Math.PI;
        double dLongDegrees = dLongRadians * 180/Math.PI;

        LatLng latLng = new LatLng();
        latLng.lat = latLng1.lat + dLatDegrees;
        latLng.lng = latLng1.lng + dLongDegrees;
        return latLng;
    }

    // GET (X,Y,Z) coordinates from latitude,longitude
    public static Point getPointFromLatLng(LatLng latLng){
        Double latInRadians = (latLng.lat*Math.PI)/180;
        Double longInRadians = (latLng.lng*Math.PI)/180;
        Point point = new Point();
        point.setY(PathPlotterConstants.radius*Math.sin(latInRadians));
        point.setX(PathPlotterConstants.radius*Math.cos(latInRadians)*Math.sin(longInRadians));
        point.setZ(PathPlotterConstants.radius*Math.cos(latInRadians)*Math.cos(longInRadians));
        return point;
    }

    // Decode points from polyline
    public static List<LatLng> getLocationsFromPolyline(String latLngs) {
        List<LatLng> listOfLatLngs = PolylineEncoding.decode(latLngs);
        return listOfLatLngs;
    }


}
