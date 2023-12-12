package Defis1;

/**
 * Coordonnee
 */
public class Coordonnee {

    /**
     * latitude
     */
    private double latitude;

    /**
     * longitude
     */
    private double longitude;

    /**
     * rayonTerre
     */
    private static double rayonTerre = 6.371;

    /**
     * Constructeur
     * @param latitude
     * @param longitude
     */
    public Coordonnee(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * distance
     * @param c
     * @return
     */
    public double distance(Coordonnee c) {
        double lat1 = Math.toRadians(this.latitude);
        double lat2 = Math.toRadians(c.latitude);
        double long1 = Math.toRadians(this.longitude);
        double long2 = Math.toRadians(c.longitude);
        double d = Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(long1-long2));
        return d*rayonTerre;
    }
}
