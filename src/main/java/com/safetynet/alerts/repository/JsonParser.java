package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.*

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;


public class JsonParser {

    public void parseJsonFile() {    //à partir du fichier data.json
        JSONParser parser = new JSONParser("data.json");   //création d'un objet JSONParser pour utiliser  methodes d'analyse d'un fichier Json (notamment parse)

        try {
            FileReader reader = new FileReader("data.json"); //convertit fichier json en string
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Object obj = parser.parse(reader);  //création d'un objet puis initialisation avec les valeurs lues par le reader (methode parse)
        JSONObject jso = (JSONObject) obj;   //"conversion" de l'objet obj en un JSONObject jso
        // on peut lire et  chercher des valeurs particulières dans un objet JSON (jsonobject ou jsonarray)
    }

    public List<Person> getAllPersons() throws JSONException {   //choix jsonobject plutot que jsonarray car type map plus interessant
        String jsonString = "data.json"; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);

        JSONArray arr = obj.getJSONArray("persons"); // notice that `"posts": [...]`
//            for (int i = 0; i < arr.length(); i++) {
//             }
    }
    public List<FireStation> getAllFireStations() {

    }

    public List<MedicalRecord> getAllMedicalRecords() {

    }
}

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
