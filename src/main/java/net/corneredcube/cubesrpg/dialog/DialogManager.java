package net.corneredcube.cubesrpg.dialog;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DialogManager {

    InputStream stream = getClass().getResourceAsStream("/assets/cubesrpg/dialogs/dialogs.json");

    InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
    InputStream stream2 = getClass().getResourceAsStream("/assets/cubesrpg/dialogs/jokes.json");

    InputStreamReader reader2 = new InputStreamReader(stream2, StandardCharsets.UTF_8);

    Gson gson = new Gson();

    Type type = new TypeToken<Map<String, List<String>>>(){}.getType();
    Map<String, ArrayList<String>> data = gson.fromJson(reader, type);
    Map<String, ArrayList<String>> jokes = gson.fromJson(reader2, type);


    public ArrayList<String> getDialog(String id) {
        return data.get(id);
    }
    public ArrayList<String>getJoke(String id) {
        return jokes.get(id);
    }
}