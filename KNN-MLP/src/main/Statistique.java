package main;

import Exeption.BadTypeExeption;
import Exeption.NotEnoughtImageExeption;
import library.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Statistique {
    List<Imagette> domaineTrain;

    List<Imagette> domaineTest;

    MLP mlp;

    public Statistique(int nbTrain, int nbTest, int[] layers, double learningRate) throws BadTypeExeption, IOException, NotEnoughtImageExeption {
        final String dataset = "Fashion";
        final Charger data = new Charger();
        data.chargerImagette("KNN-MLP/data/"+dataset+"/train-images-idx3-ubyte", nbTrain);
        data.chargerEtiquettes("KNN-MLP/data/"+dataset+"/train-labels-idx1-ubyte");

        domaineTrain = data.imagettes;


        // Chargement test data
        data.chargerImagette("KNN-MLP/data/"+dataset+"/t10k-images-idx3-ubyte", nbTest);
        data.chargerEtiquettes("KNN-MLP/data/"+dataset+"/t10k-labels-idx1-ubyte");

        domaineTest = data.imagettes;

        List<Integer> lastLayers = new ArrayList<>(data.nbCol * data.nbLine);
        Arrays.stream(layers)
                .forEach(lastLayers::add);

        mlp = new MLP(lastLayers.stream().mapToInt(Integer::intValue).toArray(), learningRate, new TanH());
    }

    public void learn() {
        double error;

        for (int i = 0; i < 100; i++) {
            for (Imagette imagette : this.domaineTrain) {
                double[] input = Arrays.stream(imagette.pixels)
                        .flatMapToInt(Arrays::stream)
                        .asDoubleStream()
                        .toArray();

                double[] output = new double[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
                output[imagette.etiquettes] = 1;
                error = mlp.backPropagate(input, output);
                System.out.printf("loss: %f\n", error);
            }
        }
    }

    public void test() {
        int totalTrue = 0;
        for (Imagette imagette : this.domaineTest) {
            double[] input = Arrays.stream(imagette.pixels)
                    .flatMapToInt(Arrays::stream)
                    .asDoubleStream()
                    .toArray();

            double[] output = mlp.execute(input);


            int res = IntStream.range(0, output.length)
                    .reduce((a, b) -> output[a] < output[b] ? b : a)
                    .orElseThrow();

            if (imagette.etiquettes == res){
                totalTrue++;
            }
        }

        System.out.println(totalTrue*100/domaineTest.size());;
    }
}
