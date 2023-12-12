package Defis1;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class Carte {
    private List<Ville> villes;

    public Carte() throws IOException, ParseException {
        this.villes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Ville ville = new Ville("193.50." + i + ".38");
            this.villes.add(ville);
        }
        Collections.sort(this.villes, Comparator.comparingInt(Ville::getPopulation));
    }

    public List<Ville> getVilles() {
        return new ArrayList<>(this.villes);
    }

    public double distance(Ville v1, Ville v2) {
        return v1.distance(v2);
    }

    public double vitesse(Ville v1, Ville v2) {
        if (this.villes.indexOf(v1) > this.villes.size() / 2 && this.villes.indexOf(v2) > this.villes.size() / 2) {
            return 130;
        } else if (this.villes.indexOf(v1) < this.villes.size() / 2 && this.villes.indexOf(v2) < this.villes.size() / 2) {
            return 100;
        } else {
            return 90;
        }
    }

    public double temps(Ville v1, Ville v2) {
        return this.distance(v1, v2) / this.vitesse(v1, v2);
    }

}
