package client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsResult;
import constants.PathPlotterConstants;
import model.GoogleDirectionsRequest;
import model.GoogleDirectionsResponse;

public class GoogleDirectionsClient {

    private RESTClient restClient = new RESTClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    private GeoApiContext geoApiContext = new GeoApiContext.Builder()
            .apiKey(PathPlotterConstants.apiKey)
            .build();

    public GoogleDirectionsResponse fetchGoogleDiections(GoogleDirectionsRequest googleDirectionsRequest) throws Exception {
        String requestParameters = googleDirectionsRequest.toString();
        String api = PathPlotterConstants.requestAPI.concat("?").concat(requestParameters);
        String response = restClient.sendGet(api);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GoogleDirectionsResponse googleDirectionsResponse = objectMapper.readValue(response, GoogleDirectionsResponse.class);
        return googleDirectionsResponse;
    }




}