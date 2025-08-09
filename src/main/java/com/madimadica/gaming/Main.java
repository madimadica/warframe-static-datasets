package com.madimadica.gaming;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        WarframeApi api = new WarframeApi();
        var index = api.getIndexFiles();
        for (var key : index) {
            String baseName = key.split("!")[0];
            System.out.println(key);
            String rawJson = api.getManifestExport(key);
            var dirPath = Paths.get(System.getProperty("user.home"), ".madimadica", "warframe", "exports");
            var filePath = dirPath.resolve(baseName);
            try {
                Files.createDirectories(dirPath);
                Files.writeString(filePath, rawJson);
            } catch (IOException e) {
                throw new RuntimeException("Error writing file " + filePath + ": " + e.getMessage(), e) ;
            }
        }
    }

}
