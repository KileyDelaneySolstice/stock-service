package com.kileydelaney.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Stock {

    @JsonProperty("symbol")
    @Id
    private int id;

    private String name;

    public Stock() {}

    public Stock(int id) {
        this.id = id;
        this.name = this.getNameFromId(id);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameFromId(int id) {
        Map<Integer, String> symbolDict = new HashMap<>();
        symbolDict.put(1, "AAPL");
        symbolDict.put(2, "GOOG");
        symbolDict.put(3, "MSFT");
        symbolDict.put(4, "PVTL");
        symbolDict.put(5, "AMZN");

        return symbolDict.get(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stock [ID=" + id + ", name=" + name + "]";
    }


    /**
     * Helper method to parse JSON data from URL
     */
    public static List<Stock> jsonToList(String url) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Stock[] stockList = mapper.readValue(new URL(url), Stock[].class);

        for (Stock s : stockList) {
            s.setName(s.getNameFromId(s.getId()));
        }

        return Arrays.asList(stockList);
    }

}
