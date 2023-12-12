package Defis1;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Classe représentant une ville
 */
public class Ville{
    private String ip;
    private String network;
    private String city;
    private String region;
    private String region_code;
    private String country;
    private String country_name;
    private String country_code;
    private String country_code_iso3;
    private String country_capital;
    private String country_tld;
    private String continent_code;
    private boolean in_eu;
    private String postal;
    private double latitude;
    private double longitude;
    private String timezone;
    private String utc_offset;
    private String country_calling_code;
    private String currency;
    private String currency_name;
    private String languages;
    private double country_area;
    private Long country_population;
    private String asn;
    private String org;
    private long population;

    private static JSONArray communes;

    /**
     * Actualise les informations de la ville via l'API ipapi.co
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public void actualiser() throws IOException, InterruptedException, ParseException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://ipapi.co/" + ip + "/json")).header("Content-Type", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject obj = (JSONObject) new JSONParser().parse(response.body());
        this.ip = (String) obj.get("ip");
        this.network = (String) obj.get("network");
        this.city = (String) obj.get("city");
        this.region = (String) obj.get("region");
        this.region_code = (String) obj.get("region_code");
        this.country = (String) obj.get("country");
        this.country_name = (String) obj.get("country_name");
        this.country_code = (String) obj.get("country_code");
        this.country_code_iso3 = (String) obj.get("country_code_iso3");
        this.country_capital = (String) obj.get("country_capital");
        this.country_tld = (String) obj.get("country_tld");
        this.continent_code = (String) obj.get("continent_code");
        this.in_eu = (boolean) obj.get("in_eu");
        this.postal = (String) obj.get("postal");
        this.latitude = (double) obj.get("latitude");
        this.longitude = (double) obj.get("longitude");
        this.timezone = (String) obj.get("timezone");
        this.utc_offset = (String) obj.get("utc_offset");
        this.country_calling_code = (String) obj.get("country_calling_code");
        this.currency = (String) obj.get("currency");
        this.currency_name = (String) obj.get("currency_name");
        this.languages = (String) obj.get("languages");
        this.country_area = (double) obj.get("country_area");
        this.country_population = (Long) obj.get("country_population");
        this.asn = (String) obj.get("asn");
        this.org = (String) obj.get("org");

        if (communes == null) {
            communes = (JSONArray) new JSONParser().parse(new FileReader("MLP_Prog/src/Defis1/ressource/communes.json"));
        }
        for (Object o : communes) {
            JSONObject commune = (JSONObject) o;
            if (commune.get("nom").equals(this.city)) {
                this.population = (Long) commune.get("population");
                return;
            }
        }
    }

    /**
     * Constructeur
     * @param ip
     * @throws IOException
     * @throws ParseException
     */
    public Ville(String ip) throws IOException, ParseException {
        this.ip = ip;
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader("MLP_Prog/src/Defis1/ressource/villes.json"));
        for (Object o : jsonArray) {
            JSONObject obj = (JSONObject) o;
            if (obj.get("ip").equals(ip)) {
                this.ip = (String) obj.get("ip");
                this.network = (String) obj.get("network");
                this.city = (String) obj.get("city");
                this.region = (String) obj.get("region");
                this.region_code = (String) obj.get("region_code");
                this.country = (String) obj.get("country");
                this.country_name = (String) obj.get("country_name");
                this.country_code = (String) obj.get("country_code");
                this.country_code_iso3 = (String) obj.get("country_code_iso3");
                this.country_capital = (String) obj.get("country_capital");
                this.country_tld = (String) obj.get("country_tld");
                this.continent_code = (String) obj.get("continent_code");
                this.in_eu = (boolean) obj.get("in_eu");
                this.postal = (String) obj.get("postal");
                this.latitude = (double) obj.get("latitude");
                this.longitude = (double) obj.get("longitude");
                this.timezone = (String) obj.get("timezone");
                this.utc_offset = (String) obj.get("utc_offset");
                this.country_calling_code = (String) obj.get("country_calling_code");
                this.currency = (String) obj.get("currency");
                this.currency_name = (String) obj.get("currency_name");
                this.languages = (String) obj.get("languages");
                this.country_area = (double) obj.get("country_area");
                this.country_population = (Long) obj.get("country_population");
                this.asn = (String) obj.get("asn");
                this.org = (String) obj.get("org");
                this.population = (Long) obj.get("population");
                return;
            }
        }
    }

    /**
     * Calcule la distance entre deux villes
     * @param v ville 2
     * @return distance entre les deux villes
     */
    public double distance(Ville v) {
        Coordonnee c1 = new Coordonnee(this.latitude, this.longitude);
        Coordonnee c2 = new Coordonnee(v.latitude, v.longitude);
        return c1.distance(c2);
    }

    @Override
    public String toString() {
        return "Ville{" +
                "ip='" + ip + '\'' +
                ", network='" + network + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", region_code='" + region_code + '\'' +
                ", country='" + country + '\'' +
                ", country_name='" + country_name + '\'' +
                ", country_code='" + country_code + '\'' +
                ", country_code_iso3='" + country_code_iso3 + '\'' +
                ", country_capital='" + country_capital + '\'' +
                ", country_tld='" + country_tld + '\'' +
                ", continent_code='" + continent_code + '\'' +
                ", in_eu=" + in_eu +
                ", postal='" + postal + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone='" + timezone + '\'' +
                ", utc_offset='" + utc_offset + '\'' +
                ", country_calling_code='" + country_calling_code + '\'' +
                ", currency='" + currency + '\'' +
                ", currency_name='" + currency_name + '\'' +
                ", languages='" + languages + '\'' +
                ", country_area=" + country_area +
                ", country_population=" + country_population +
                ", asn='" + asn + '\'' +
                ", org='" + org + '\'' +
                ", population=" + population +
                '}';
    }

    /**
     * Convertit la ville en JSONObject
     * @return
     */
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("ip", this.ip);
        obj.put("network", this.network);
        obj.put("city", this.city);
        obj.put("region", this.region);
        obj.put("region_code", this.region_code);
        obj.put("country", this.country);
        obj.put("country_name", this.country_name);
        obj.put("country_code", this.country_code);
        obj.put("country_code_iso3", this.country_code_iso3);
        obj.put("country_capital", this.country_capital);
        obj.put("country_tld", this.country_tld);
        obj.put("continent_code", this.continent_code);
        obj.put("in_eu", this.in_eu);
        obj.put("postal", this.postal);
        obj.put("latitude", this.latitude);
        obj.put("longitude", this.longitude);
        obj.put("timezone", this.timezone);
        obj.put("utc_offset", this.utc_offset);
        obj.put("country_calling_code", this.country_calling_code);
        obj.put("currency", this.currency);
        obj.put("currency_name", this.currency_name);
        obj.put("languages", this.languages);
        obj.put("country_area", this.country_area);
        obj.put("country_population", this.country_population);
        obj.put("asn", this.asn);
        obj.put("org", this.org);
        obj.put("population", this.population);
        return obj;
    }

    public String getRegion() {
        return region;
    }

    public String getRegionCode() {
        return region_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return Objects.equals(ip, ville.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    public int getPopulation() {
        return (int) population;
    }

    public String getCity() {
        return city;
    }

}
