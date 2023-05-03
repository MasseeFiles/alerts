package com.safetynet.alerts.repositories;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataParser {

    public void parseJsonIntoReader() { //creation d'un objet JSon à partir du fichier data.json
        JSONParser parser = new JSONParser("data.json");   //fournit les methodes d'analyse d'un fichier Json (notamment parse)

        try {
            Filereader reader = new FileReader("data.json"); //convertit fichier jsoin en string
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Object obj = parser.parse(reader);
        JSONObject jso = (JSONObject) obj;   //l'objet retourné par methode parse devient un objet Json sur lequel on peut chercher des données particuluères
    }
    public List<Person> getAllPersons() {
            String jsonString = "data.json"; //assign your JSON String here
            JSONObject obj = new JSONObject(jsonString);

            JSONArray arr = obj.getJSONArray("persons"); // notice that `"posts": [...]`
            for (int i = 0; i < arr.length(); i++)

    {
    }
}
    }
}
    public List<FireStations> getAllFireStations()
    {}



//    String jsonString = ... ; //assign your JSON String here
//    JSONObject obj = new JSONObject(jsonString);
//    String pageName = obj.getJSONObject("pageInfo").getString("pageName");
//
//    JSONArray arr = obj.getJSONArray("posts"); // notice that `"posts": [...]`
//for (int i = 0; i < arr.length(); i++)
//    {
//        String post_id = arr.getJSONObject(i).getString("post_id");
//    ......
//    }
//}
