package Defis1;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trajets implements Comparable<Trajets>{
    private Ville depart;
    private Ville arrivee;
    private List<Ville> etapes;
    private double totalTime;
    private static Carte carte;

    public Trajets(Ville depart, Ville arrivee) throws IOException, ParseException {
        this.depart = depart;
        this.arrivee = arrivee;
        if (carte == null) {
            carte = new Carte();
        }
        List<Ville> villes = carte.getVilles();
        villes.remove(depart);
        villes.remove(arrivee);
        this.initEtapes(villes);
        this.totalTime = this.totalTime();
    }

    public Trajets(Ville depart, Ville arrivee, List<Ville> etapes) throws IOException, ParseException {
        this.depart = depart;
        this.arrivee = arrivee;
        if (carte == null) {
            carte = new Carte();
        }
        this.etapes = etapes;
        this.totalTime = this.totalTime();
    }

    private void initEtapes(List<Ville> villes) {
        this.etapes = new ArrayList<>();
        while (villes.size()>0) {
            Ville ville = villes.remove(Math.round((float) Math.random() * (villes.size() - 1)));
            this.etapes.add(ville);
        }
    }

    private double totalTime() {
        double totalTime = 0;
        totalTime += carte.temps(this.depart, this.etapes.get(0));
        for (int i = 0; i < this.etapes.size() - 1; i++) {
            totalTime += carte.temps(this.etapes.get(i), this.etapes.get(i + 1));
        }
        totalTime += carte.temps(this.etapes.get(this.etapes.size() - 1), this.arrivee);
        return totalTime;
    }

    public Trajets mutation() {
        Trajets newTrajet = this.clone();
        int random1 = (int) (Math.random() * newTrajet.etapes.size());
        int random2 = (int) (Math.random() * newTrajet.etapes.size());
        Ville ville1 = newTrajet.etapes.get(random1);
        Ville ville2 = newTrajet.etapes.get(random2);
        newTrajet.etapes.set(random1, ville2);
        newTrajet.etapes.set(random2, ville1);
        newTrajet.totalTime = newTrajet.totalTime();
        return newTrajet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trajets trajets = (Trajets) o;
        return Objects.equals(depart, trajets.depart) && Objects.equals(arrivee, trajets.arrivee) && Objects.equals(etapes, trajets.etapes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depart, arrivee, etapes);
    }

    @Override
    public int compareTo(Trajets o) {
        return Double.compare(this.totalTime, o.totalTime);
    }

    public double getScore(){
        return 1/this.totalTime;
    }

    public List<Ville> getEtapes(){
        return new ArrayList<>(this.etapes);
    }

    public Ville getDepart() {
        return depart;
    }

    public Ville getArrivee() {
        return arrivee;
    }

    public Trajets clone() {
        try {
            return new Trajets(this.depart, this.arrivee, this.etapes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public double getTotalTime() {
        return totalTime;
    }
}