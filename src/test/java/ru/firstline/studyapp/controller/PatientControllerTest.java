package ru.firstline.studyapp.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(value = "/create-patient.sql")
public class PatientControllerTest extends WebTest {

    @Test
    public void getAllTest() throws Exception{
        this.mockMvc.perform(get("/patients/all")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.content.length()", is(4)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("alex")))
                .andExpect(jsonPath("$.content[0].dayOfBirth", is("20.11.1999")))
                .andExpect(jsonPath("$.content[0].sex", is(true)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.totalPages", is(1)));
    }

    @Test
    public void getAllListTest() throws Exception{
        this.mockMvc.perform(get("/patients/allList")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.length()", is(4)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("ola")))
                .andExpect(jsonPath("$[1].dayOfBirth", is("15.01.2015")))
                .andExpect(jsonPath("$[1].sex", is(false)));
    }

    @Test
    public void getByIdTest() throws Exception{
        this.mockMvc.perform(get("/patients/{id}", 2)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("ola")))
                .andExpect(jsonPath("$.dayOfBirth", is("15.01.2015")))
                .andExpect(jsonPath("$.sex", is(false)));
    }

    @Test
    public void getByIdFailTest() throws Exception{
        this.mockMvc.perform(get("/patients/{id}", 6)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addTest() throws Exception {
        this.mockMvc.perform(post("/patients/save")
                .content("{\"name\":\"gary\", \"sex\":\"true\", \"dayOfBirth\":\"20.11.2018\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("gary")))
                .andExpect(jsonPath("$.dayOfBirth", is("20.11.2018")))
                .andExpect(jsonPath("$.sex", is(true)));
    }

    @Test
    public void updateTest() throws Exception {
        this.mockMvc.perform(post("/patients/save")
                .content("{\"id\":\"4\", \"name\":\"gary\", \"sex\":\"true\", \"dayOfBirth\":\"20.11.2018\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.name", is("gary")))
                .andExpect(jsonPath("$.dayOfBirth", is("20.11.2018")))
                .andExpect(jsonPath("$.sex", is(true)));
    }

    @Test
    public void deleteTest() throws Exception {
        this.mockMvc.perform(delete("/patients/{id}", 1)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFailTest() throws Exception {
        this.mockMvc.perform(delete("/patients/{id}", 6)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(delete("/patients/{id}", 3)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError());
    }
}