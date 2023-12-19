import Exeption.BadTypeExeption;
import Exeption.NotEnoughtImageExeption;
import main.Statistique;

import java.io.IOException;

public class Main {
    static final double LEARNING_RATE = 0.001;
    public static void main(String[] args) throws NotEnoughtImageExeption, BadTypeExeption, IOException {
    Statistique statistique = new Statistique(1000, 100, new int[]{300, 150, 10}, Main.LEARNING_RATE);
    long startTime = System.nanoTime();
    statistique.learn();
    long endTime = System.nanoTime();
    System.out.println((endTime - startTime)/1000000000 + " s");

    statistique.test();
    }
}