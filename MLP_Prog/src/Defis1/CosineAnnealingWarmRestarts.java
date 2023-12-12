package Defis1;

public class CosineAnnealingWarmRestarts {
    private double T0;
    private double Tmult;
    private double etaMin;
    private double etaMax;
    private int currentIteration;

    public CosineAnnealingWarmRestarts(double T0, double Tmult, double etaMin, double etaMax) {
        this.T0 = T0;
        this.Tmult = Tmult;
        this.etaMin = etaMin;
        this.etaMax = etaMax;
        this.currentIteration = 0;
    }

    public double getNext() {
        if (currentIteration == 0 || currentIteration > T0) {
            currentIteration = 0;
            T0 *= Tmult;
        }

        double cosInternal = Math.PI * (currentIteration / T0);
        double cosRight = Math.cos(cosInternal) + 1;
        double newLearningRate = etaMin + (etaMax - etaMin) / 2 * cosRight;

        currentIteration++;
        return newLearningRate;
    }
}

