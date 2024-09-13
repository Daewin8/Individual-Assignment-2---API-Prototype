package com.csc340.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class RestAPIController {
    /**
     * Get a list of Whiskey Auctions and make them available at our own API endpoint.
     *
     * @return a list of Whiskery Auctions.
     */
    @GetMapping("/whiskey")
    public Object getWhiskeyHunter() {
        try {
            String url = "https://whiskyhunter.net/api/auctions_info";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            List<WhiskeyHunter> whiskeyHunterList = new ArrayList<>();

            //The response from the above API is a JSON Array, which we loop through.
            for (JsonNode rt : root) {
                //Extract relevant info from the response and use it for what you want, in this case build a Brewery object
                String name = rt.get("name").asText();
                String slug = rt.get("slug").asText();
                String base_currency = rt.get("base_currency").asText();

                WhiskeyHunter whiskeyHunter = new WhiskeyHunter(name, slug, base_currency);
                whiskeyHunterList.add(whiskeyHunter);
            }
            return whiskeyHunterList;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestAPIController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return "error in /whiskey";
        }
    }
}
