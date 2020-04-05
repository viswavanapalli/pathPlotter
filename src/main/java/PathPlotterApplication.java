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
//    A is 12.94523, 77.61896
//    B is 12.95944, 77.66085

    public static void main(String[] args){
        String inputString2 = "START_LATITUDE=12.94523 START_LONGITUDE=77.61896 DEST_LATITUDE=12.95944 DEST_LONGITUDE=77.66085";

        execute(inputString2);


        //testing output distance of sample output.. if consistent or not
        testExecute();



    }

    public static void execute(String inputString){
        try{
            if(inputString != null && inputString.trim()!=""){
                Map<Parameter, String> parameterValues = PathUtils.getParameterValues(inputString);
                PathRequest pathRequest = PathUtils.getInputRequestFromParameters(parameterValues);

                pathController.findPath(pathRequest);

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


//Checking if the outputs we
    public static void testExecute(){
        try {

            List<LatLng> latLngs = new ArrayList<LatLng>();
            for(String inputString : PathPlotterConstants.testDistanceInputs){
                if (inputString != null && inputString.trim() != "") {
                    Map<Parameter, String> parameterValues = PathUtils.getParameterValues(inputString);
                    latLngs.add(PathUtils.getLatLngFromParameters(parameterValues));
                }
            }
            LatLng prevLatLng = latLngs.get(0);
            LatLng nextLatLng;

            Iterator<LatLng> latLngIterator = latLngs.iterator();
            while(latLngIterator.hasNext()){
                nextLatLng = latLngIterator.next();
                System.out.println("===========================================================");
                System.out.println("PrevPoint: " + prevLatLng.toString());
                System.out.println("NextPoint: " + nextLatLng.toString());
                PathUtils.findDistanceBetweenTwoLatLngs(prevLatLng, nextLatLng);
                prevLatLng = nextLatLng;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}