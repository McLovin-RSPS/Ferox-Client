package net.runelite.client.game;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

@Singleton
public class NPCManager {
    private final Map<String, Integer> healthMap;

    @Inject
    private NPCManager() {
        Gson gson = new Gson();
        Type typeToken = (new TypeToken<Map<String, Integer>>() {

        }).getType();
        InputStream healthFile = getClass().getResourceAsStream("/npc_health.json");
        this.healthMap = (Map<String, Integer>)gson.fromJson(new InputStreamReader(healthFile), typeToken);
    }

    @Nullable
    public Integer getHealth(String name, int combatLevel) {
        return this.healthMap.get(name + "_" + combatLevel);
    }
}