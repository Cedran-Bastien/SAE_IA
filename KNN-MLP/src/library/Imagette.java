package library;

import Exeption.NotSameLengthExeption;

public class Imagette {
    public int[][] pixels;
    public int etiquettes;

    public int comparerImagette(Imagette test) throws NotSameLengthExeption {
        int distance = 0;
        for (int i = 0; i < this.pixels.length; i++){
            int[] colTrain = this.pixels[i];
            int[] colTest = test.pixels[i];

            // Check same lenght
            if (colTest.length != colTrain.length){
                throw new NotSameLengthExeption();
            }

            // For each pixel
            for (int j = 0; j < colTrain.length; j++){
                // Get pixels
                int pixelTrain  = colTrain[j];
                int pixelTest = colTest[j];
                // Increment difference grey level
                distance += Math.abs(pixelTrain-pixelTest);
            }
        }
        return distance;
    }

}
