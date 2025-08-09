package com.madimadica.gaming;

import org.tukaani.xz.LZMAInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WarframeApi {

    private final HttpClient client;

    public WarframeApi() {
        client = HttpClient.newHttpClient();
    }

    public List<String> getIndexFiles() {
        return getIndexFiles("en");
    }

    public List<String> getIndexFiles(String languageCode) {
        URI url = URI.create("https://origin.warframe.com/PublicExport/index_" + languageCode + ".txt.lzma");
        HttpRequest req = HttpRequest.newBuilder(url).build();
        try {
            HttpResponse<InputStream> resp = client.send(req, HttpResponse.BodyHandlers.ofInputStream());
            if (resp.statusCode() != 200) {
                throw new RuntimeException("Error calling API: " + resp.statusCode());
            }
            try (LZMAInputStream lzmaIn = new LZMAInputStream(resp.body()); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                lzmaIn.transferTo(baos);
                String indexContent = baos.toString(StandardCharsets.UTF_8);
                String[] lines = indexContent.split("\\R");
                return List.of(lines);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error calling API: " + e.getMessage(), e);
        }
    }

    public String getManifestExport(String key) {
        URI url = URI.create("http://content.warframe.com/PublicExport/Manifest/" + key);
        HttpRequest req = HttpRequest.newBuilder(url).build();
        try {
            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() != 200) {
                throw new RuntimeException("Error calling API: " + resp.statusCode() + ": " + resp.body());
            }
            return resp.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error calling API: " + e.getMessage(), e);
        }
    }

}
