import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.PrintWriter;

public class ActualiserVilleMain {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        Ville[] villes = new Ville[100];
        for (int i = 0; i < 100; i++) {
            villes[i] = new Ville("193."+i+".1.38");
            villes[i].actualiser();
        }
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 100; i++) {
            jsonArray.add(villes[i].toJSON());
        }
        PrintWriter writer = new PrintWriter("Defis1/ressource/villes.json", "UTF-8");
        writer.println(jsonArray.toJSONString());
        writer.close();
    }
}
