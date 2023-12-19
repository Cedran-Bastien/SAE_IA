/**
 * Scheduler
 */
public class CosineAnnealingWarmRestarts {

    /**
     * Number of iterations for the first restart
     */
    private double T0;

    /**
     * Multiplicative factor for the next restarts
     */
    private double Tmult;

    /**
     * Minimum value
     */
    private double etaMin;

    /**
     * Maximum value
     */
    private double etaMax;

    /**
     * Current iteration
     */
    private int currentIteration;

    /**
     * Constructor
     * @param T0
     * @param Tmult
     * @param etaMin
     * @param etaMax
     */
    public CosineAnnealingWarmRestarts(double T0, double Tmult, double etaMin, double etaMax) {
        this.T0 = T0;
        this.Tmult = Tmult;
        this.etaMin = etaMin;
        this.etaMax = etaMax;
        this.currentIteration = 0;
    }

    /**
     * Get next value
     * @return
     */
    public double getNext() {
        if (currentIteration == 0 || currentIteration > T0) {
            currentIteration = 0;
            T0 *= Tmult;
        }

        double cosInternal = Math.PI * (currentIteration / T0);
        double cosRight = Math.cos(cosInternal) + 1;
        double newValue = etaMin + (etaMax - etaMin) / 2 * cosRight;

        currentIteration++;
        return newValue;
    }
}

