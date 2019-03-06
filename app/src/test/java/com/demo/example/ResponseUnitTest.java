package com.demo.example;

import com.demo.example.networkservice.model.GameModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;

import static com.demo.example.AssetHelper.readJsonFile;
import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ResponseUnitTest {
    @Test
    public void response_isCorrect() {
        Type mapType = new TypeToken<GameModel>() {}.getType();
        try {
            GameModel result = new Gson().fromJson(readJsonFile("game.json"),
                    mapType);
            assertEquals("10174@greentube",
                    result.getGamesData().get("10174@greentube").getGameId());
            assertEquals("Hardwell",
                    result.getGamesData().get("10174@greentube").mGameName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}