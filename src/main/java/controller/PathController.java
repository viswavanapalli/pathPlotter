package controller;

import model.PathRequest;
import service.PathService;
import service.impl.PathServiceImpl;

public class PathController {

    private PathService pathService = new PathServiceImpl();

    public void findPath(PathRequest pathRequest){
        try {
            pathService.findEvenSpacedPaths(pathRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
