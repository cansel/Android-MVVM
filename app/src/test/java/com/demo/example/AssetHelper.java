package com.demo.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AssetHelper {

    private static final String ASSET_BASE_PATH = "../app/src/main/assets/";

    public static String readJsonFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new
                FileInputStream(ASSET_BASE_PATH + filename)));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }

        return sb.toString();
    }
}
