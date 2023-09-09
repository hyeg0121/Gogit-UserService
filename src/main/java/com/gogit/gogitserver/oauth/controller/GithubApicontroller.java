package com.gogit.gogitserver.oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

@Controller
public class GithubApicontroller {

    @GetMapping(value = "/success")
    public String success(HttpServletRequest request, Model model) throws JsonProcessingException {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        String response = null;

        if(inputFlashMap != null) {
            response = (String) inputFlashMap.get("result");
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> result = objectMapper.readValue(response, Map.class);

        model.addAttribute("result", result);

        return "success";
    }

    @GetMapping("/auth/github/callback")
    public String getCode(@RequestParam String code) throws IOException {
        URL url = new URL("https://github.com/login/oauth/access_token");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");
        Properties properties = new Properties();
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))){
            bw.write("client_id="+properties.getProperty("clientId") +
                    "&client_secret="+properties.getProperty("clientSecret") +
                    "&code=" + code);
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int responseCode = conn.getResponseCode();

        String responseData = getResponse(conn, responseCode);

        conn.disconnect();

        System.out.println(responseData);
        return "redirect:/success";

    }

    private String getResponse(HttpURLConnection conn, int responseCode) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (responseCode == 200) {
            try (InputStream is = conn.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    sb.append(line);
                }
            }
        }
        return sb.toString();
    }

    public void access(String response, RedirectAttributes redirectAttributes) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(response, Map.class);
        String access_token = map.get("access_token");

        URL url = new URL("https://api.github.com/user");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/96.0.4664.45 Safari/537.36");
        conn.setRequestProperty("Authorization", "token " + access_token);

        int responseCode = conn.getResponseCode();

        String result = getResponse(conn, responseCode);

        conn.disconnect();

        redirectAttributes.addFlashAttribute("result", result);
    }
}
