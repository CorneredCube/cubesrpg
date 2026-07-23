package net.corneredcube.cubesrpg.entity.custom;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataManager {

    InputStream stream = getClass().getResourceAsStream("/assets/cubesrpg/goobData/goobdata.json");

    InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);

    Gson gson = new Gson();

    Type type = new TypeToken<Map<String, List<String>>>(){}.getType();
    Map<String, ArrayList<String>> data = gson.fromJson(reader, type);


    public ArrayList<String> getData(String id) {
        return data.get(id);
    }
}