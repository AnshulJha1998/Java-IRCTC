package org.services;

import java.util.*;
import java.util.stream.Collectors;

import org.entities.Train;

public class TrainService{

    private List<Train> trainList;
     
     public List<Train> searchTrains(String source, String destination){
        return trainList.stream().filter(train -> validTrain(train,source,destination)).collect(Collectors.toList());
     }

     private boolean validTrain(Train train, String source, String destination){
         List<String> stations = train.getStations();
         
         // Here we are assuming that in db, source idx is lesser that destination idx
         // that means stations are all sorted already on the basis of train route. like A -> B -> C...
         int sourceIdx = stations.indexOf(source.toLowerCase());
         int destinationIdx = stations.indexOf(destination.toLowerCase());

         return sourceIdx != -1 && destinationIdx != -1 && sourceIdx < destinationIdx;
     }
}