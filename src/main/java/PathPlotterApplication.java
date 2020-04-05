import client.RESTClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;
import constants.PathPlotterConstants;
import controller.PathController;
import enums.Parameter;
import model.*;
import service.PathService;
import service.impl.PathServiceImpl;
import utils.PathUtils;

import java.util.*;

public class PathPlotterApplication {

    private static RESTClient RESTClient = new RESTClient();

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static PathService pathService = new PathServiceImpl();

    private static PathController pathController = new PathController();

    public static void main(String[] args){
        String inputString = "START_LATITUDE=12.94523 START_LONGITUDE=77.61896 DEST_LATITUDE=12.95944 DEST_LONGITUDE=77.66085";
        execute(inputString);
        testDistance();
        commandLineInputs();
    }

    public static void execute(String inputString){
        try{
            if(inputString != null && inputString.trim()!=""){
                System.out.println("For Query " + inputString);
                Map<Parameter, String> parameterValues = PathUtils.getParameterValues(inputString);
                PathRequest pathRequest = PathUtils.getInputRequestFromParameters(parameterValues);
                //To verify distances
                List<LatLng> evenSpacedLatLngs = pathController.findPath(pathRequest);
                pathController.findDistance(evenSpacedLatLngs);
            }
        } catch(Exception e){
            System.out.println("Error executing query " + inputString);
            e.printStackTrace();
        }
    }

    public static void commandLineInputs(){
        Scanner scanner = new Scanner(System.in);
        String inputString;
        do{
            System.out.println("-------------------------Enter Input Query here-------------------------");
            inputString = scanner.nextLine();
            if(inputString!= null && inputString.equals("exit"))
                continue;
            execute(inputString);

        } while(!inputString.equals("exit"));
    }


    //Checking if the outputs distances are at par with base Distance
    public static void testDistance(){
        try {

            List<LatLng> latLngs = new ArrayList<LatLng>();
            for(String inputString : PathPlotterConstants.testDistanceInputs){
                if (inputString != null && inputString.trim() != "") {
                    Map<Parameter, String> parameterValues = PathUtils.getParameterValues(inputString);
                    latLngs.add(PathUtils.getLatLngFromParameters(parameterValues));
                }
            }
            pathController.findDistance(latLngs);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}