package library;

import Exeption.BadTypeExeption;
import Exeption.NotEnoughtImageExeption;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Charger {
    public List<Imagette> imagettes;
    public int nbLine;
    public int nbCol;


    public void chargerImagette(String path, int neededImage) throws IOException, BadTypeExeption, NotEnoughtImageExeption {
        // Open stream
        DataInputStream data = new DataInputStream(new FileInputStream(path));

        // Check file type int
        if (data.readInt() != 2051){
            throw new BadTypeExeption();
        }

        // Nb image,
        int nbImage = data.readInt();
        if (nbImage < neededImage){
            throw new NotEnoughtImageExeption();
        }

        // Get nb line and col
        nbLine = data.readInt();
        nbCol = data.readInt();


        // create imagette tab
        imagettes = new ArrayList<Imagette>();

        // For each image
        for (int i=0; i< neededImage; i++){
            System.out.printf("%d%%\n", i*100/neededImage);
            // Create imagette
            Imagette im = new Imagette();
            im.pixels = new int[nbLine][nbCol];
            // For each line
            for (int j = 0; j < nbLine; j++){
                // For each col
                for (int k = 0; k < nbCol; k++ ){
                    // Read byte
                    // Set imagette attribut
                    im.pixels[j][k] = data.readUnsignedByte();
                }
            }
            // Add to list
            imagettes.add(im);
        }

        // Close stream
        data.close();
    }

    public void saveImage(Imagette imagette, String name) throws IOException {
            BufferedImage bufImage = new BufferedImage(nbCol, nbLine, BufferedImage.TYPE_3BYTE_BGR);
            // For each line
            for (int j = 0; j < nbLine; j++){
                // For each col
                for (int k = 0; k < nbCol; k++ ){
                    //Set image
                    bufImage.setRGB(k,j,imagette.pixels[j][k]);
                }
            }
            // Save image
            File f = new File("KNN-MLP/data/images" + name + ".png");
            ImageIO.write(bufImage, "png",f);
    }

    public void chargerEtiquettes(String path) throws IOException, BadTypeExeption {
        // Open stream
        DataInputStream data = new DataInputStream(new FileInputStream(path));

        // Check file type int
        if (data.readInt() != 2049){
            throw new BadTypeExeption();
        }

        // Nb image, line and col
        int nbEtiquette = data.readInt();

        // For each image
        for (Imagette  items: imagettes){
            // Read and set etiquettes
            items.etiquettes = data.readUnsignedByte();
        }

        // Close stream
        data.close();
    }


}