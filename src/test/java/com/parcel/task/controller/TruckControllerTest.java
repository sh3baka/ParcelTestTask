package com.parcel.task.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TruckController.class)
@RunWith(SpringRunner.class)
@ComponentScan("com.parcel.task")
public class TruckControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAll() throws Exception {
        String expected = "[]";

        mvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void testAddNewTruck() throws Exception {
        String json = "{\n" +
                "    \"name\": \"TruckName\",\n" +
                "    \"weight\": 223.4\n" +
                "}";
        String expected = "[\n" +
                "    {\n" +
                "        \"load\": [],\n" +
                "        \"parcelCount\": 0,\n" +
                "        \"weight\": 223.4,\n" +
                "        \"name\": \"TruckName\"\n" +
                "    }\n" +
                "]";

        mvc.perform(post("/add").contentType("application/json").content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void testAddParcelToTruck() throws Exception {
        String json = "{\n" +
                "    \"name\": \"FirstParcel\",\n" +
                "    \"weight\": 555.34\n" +
                "}";
        String expected = "[\n" +
                "   {\n" +
                "      \"load\":[\n" +
                "         {\n" +
                "            \"name\":\"FirstParcel\",\n" +
                "            \"weight\":555.34\n" +
                "         }\n" +
                "      ],\n" +
                "      \"parcelCount\":1,\n" +
                "      \"weight\":778.74,\n" +
                "      \"name\":\"TruckName\"\n" +
                "   }\n" +
                "]";

        mvc.perform(post("/add-parcel/TruckName").contentType("application/json").content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void testRemoveParcelFromTruck() throws Exception {
        String expected = "[\n" +
                "    {\n" +
                "        \"load\": [],\n" +
                "        \"parcelCount\": 0,\n" +
                "        \"weight\": 223.4,\n" +
                "        \"name\": \"TruckName\"\n" +
                "    }\n" +
                "]";

        mvc.perform(delete("/remove-parcel?&parcelName=FirstParcel&truckName=TruckName"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}