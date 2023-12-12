package Defis1;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

/**
 * Une carte est un ensemble de villes
 */
public class Carte {

    /**
     * La liste des villes de la carte
     */
    private List<Ville> villes;

    /**
     * Constructeur, initialise les villes du fichier ressource/villes.json
     * @throws IOException
     * @throws ParseException
     */
    public Carte() throws IOException, ParseException {
        this.villes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Ville ville = new Ville("193."+i+".1.38");
            this.villes.add(ville);
        }
        Collections.sort(this.villes, Comparator.comparingInt(Ville::getPopulation));
    }

    public List<Ville> getVilles() {
        return new ArrayList<>(this.villes);
    }

    /**
     * Calcule la distance entre deux villes
     * @param v1 ville 1
     * @param v2 ville 2
     * @return distance entre les deux villes
     */
    public double distance(Ville v1, Ville v2) {
        return v1.distance(v2);
    }

    /**
     * Calcule la vitesse entre deux villes,
     * les 50 villes les plus peuplées liées par des autoroutes,
     * les 50 suivantes (entre elles et avec les 50 premières) liées par des voies rapides,
     * les autres liaisons sont des routes départementales.
     * @param v1 ville 1
     * @param v2 ville 2
     * @return vitesse entre les deux villes
     */
    public double vitesse(Ville v1, Ville v2) {
        if (this.villes.indexOf(v1) > this.villes.size() / 2 && this.villes.indexOf(v2) > this.villes.size() / 2) {
            return 130;
        } else if (this.villes.indexOf(v1) < this.villes.size() / 2 && this.villes.indexOf(v2) < this.villes.size() / 2) {
            return 100;
        } else {
            return 90;
        }
    }

    /**
     * Calcule le temps entre deux villes
     * @param v1
     * @param v2
     * @return
     */
    public double temps(Ville v1, Ville v2) {
        return this.distance(v1, v2) / this.vitesse(v1, v2);
    }

}
