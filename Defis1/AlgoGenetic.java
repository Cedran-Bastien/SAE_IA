import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Algorithme génétique
 */
public class AlgoGenetic {

    /**
     * Taux de mutation
     */
    private double mutationRate;

    /**
     * Taille de la population
     */
    private int populationSize;

    /**
     * Population
     */
    private List<Trajets> population;

    /**
     * @param mutationRate  taux de mutation
     * @param populationSize taille de la population
     * @param depart         ville de départ
     * @param arrivee        ville d'arrivée
     * @throws IOException
     * @throws ParseException
     */
    public AlgoGenetic(double mutationRate, int populationSize, Ville depart, Ville arrivee) throws IOException, ParseException {
        this.mutationRate = mutationRate;
        this.populationSize = populationSize;
        this.population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            this.population.add(new Trajets(depart, arrivee));
        }
    }

    /**
     * Sélection d'un trajet, plus le score est élevé plus il a de chance d'être sélectionné
     * @return trajet sélectionné
     */
    public Trajets selection(List<Trajets> population) {
        double totalScore = 0;
        for (Trajets trajet : population) {
            totalScore += trajet.getScore();
        }
        double random = Math.random() * totalScore;
        Collections.sort(population);
        for (Trajets trajet : population) {
            random -= trajet.getScore();
            if (random <= 0) {
                return trajet.clone();
            }
        }
        return null;
    }

    public Trajets selection(List<Trajets> population, Trajets whithout) {
        population.remove(whithout);
        return this.selection(population);
    }

    /**
     * Multiplication de deux trajets
     * @param t1 trajet 1
     * @param t2 trajet 2
     * @return trajet résultant de la multiplication
     * @throws IOException
     * @throws ParseException
     */
    public Trajets multiplication(Trajets t1, Trajets t2) throws IOException, ParseException {
        int random = (int) (Math.random() * t1.getEtapes().size());
        List<Ville> etapes1 = t1.getEtapes().subList(0, random);
        List<Ville> etapes2 = t2.getEtapes();
        etapes2.removeAll(etapes1);
        etapes1.addAll(etapes2);
        return new Trajets(t1.getDepart(), t1.getArrivee(), etapes1);
    }

    /**
     * Mutation d'un trajet
     * @param trajet trajet à muter
     * @return trajet muté
     */
    public Trajets mutation(Trajets trajet) {
        Trajets newTrajet = trajet.clone();
        if (Math.random() < this.mutationRate) {
            newTrajet.mutation();
        }
        return newTrajet;
    }

    /**
     * Nettoyage de la population
     * @return liste des meilleurs trajets
     */
    public List<Trajets> cliner() {
        Collections.sort(this.population);
        return new ArrayList<>(this.population.subList(0, this.populationSize));
    }

    /**
     * Renouvellement de la population
     * @throws IOException
     * @throws ParseException
     */
    public void run() throws IOException, ParseException {
        List<Trajets> newPopulation = new ArrayList<>();
        for (int i = 0; i < this.populationSize/2; i++) {
            Trajets t1 = this.selection(new ArrayList<>(this.population));
            //System.out.println("t1: "+t1.getTotalTime());
            Trajets t2 = this.selection(new ArrayList<>(this.population), t1);
            //System.out.println("t2: "+t2.getTotalTime());
            Trajets t3 = this.multiplication(t1, t2);
            //System.out.println("t3: "+t3.getTotalTime());
            t3 = this.mutation(t3);
            //System.out.println("t3: "+t3.getTotalTime());
            newPopulation.add(t3);
        }
        this.population.addAll(newPopulation);
        //System.out.println("Population size: "+this.population.size());
        this.population = this.cliner();
    }

    public Trajets getBest() {
        Collections.sort(this.population);
        return this.population.get(0);
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }
}
