package Defis1;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Ville depart = new Ville("193.0.1.38");
        Ville arrivee = new Ville("193.99.1.38");
        AlgoGenetic algoGenetic = new AlgoGenetic(0, 500, depart, arrivee);
        CosineAnnealingWarmRestarts scheduler = new CosineAnnealingWarmRestarts(1, 2, 0.5, 0.1);
        algoGenetic.setMutationRate(scheduler.getNext());
        for (int i = 0; i < 1000; i++) {
            algoGenetic.run();
            System.out.println("Generation "+i+" time: "+algoGenetic.getBest().getTotalTime());
            algoGenetic.setMutationRate(scheduler.getNext());
        }
        System.out.println("Best path: "+algoGenetic.getBest());
        //System.out.println(new HashSet<>(algoGenetic.getBest().getEtapes()).size());
    }
}
