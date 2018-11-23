package ru.firstline.studyapp.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql("/create-study.sql")
public class StudyControllerTest extends WebTest {

    @Test
    public void getAllTest() throws Exception{
        this.mockMvc.perform(get("/study/all")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.content.length()", is(2)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].description", is("111")))
                .andExpect(jsonPath("$.content[0].plannedStartTime", is("12.11.2018 11:00")))
                .andExpect(jsonPath("$.content[0].status", is("PLANNED")))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.totalPages", is(1)));
    }

    @Test
    public void getByIdTest() throws Exception{
        this.mockMvc.perform(get("/study/{id}", 2)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.description", is("222")))
                .andExpect(jsonPath("$.plannedStartTime", is("01.01.2018 20:00")))
                .andExpect(jsonPath("$.estimatedEndTime", is("12.11.2018 11:00")))
                .andExpect(jsonPath("$.status", is("IN_PROGRESS")))
                .andExpect(jsonPath("$.patient.id", is(1)))
                .andExpect(jsonPath("$.patient.name", is("alex")));
    }

    @Test
    public void getByIdFailTest() throws Exception{
        this.mockMvc.perform(get("/study/{id}", 5)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addTest() throws Exception{
        this.mockMvc.perform(post("/study/save")
                .content("{\"description\":\"333\", \"status\":\"FINISHED\", \"plannedStartTime\":\"07.11.2018 08:00\"," +
                        "\"patient\":{\"id\":\"1\"}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.description", is("333")))
                .andExpect(jsonPath("$.status", is("FINISHED")))
                .andExpect(jsonPath("$.plannedStartTime", is("07.11.2018 08:00")))
                .andExpect(jsonPath("$.patient.id", is(1)));
    }

    @Test
    public void updateTest() throws Exception{
        this.mockMvc.perform(post("/study/save")
                .content("{\"id\":\"1\", \"description\":\"333\", \"status\":\"FINISHED\", \"plannedStartTime\":\"07.11.2018 08:00\"," +
                        "\"patient\":{\"id\":\"1\"}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("333")))
                .andExpect(jsonPath("$.status", is("FINISHED")))
                .andExpect(jsonPath("$.plannedStartTime", is("07.11.2018 08:00")))
                .andExpect(jsonPath("$.patient.id", is(1)));
    }

    @Test
    public void deleteTest() throws Exception {
        this.mockMvc.perform(delete("/study/{id}", 2)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFailTest() throws Exception {
        this.mockMvc.perform(delete("/study/{id}", 6)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void setStatusTest() throws Exception{
        this.mockMvc.perform(put("/study/setStatus")
                .content("{\"id\":\"2\", \"status\":\"IN_PROGRESS\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void setStatusFailTest() throws Exception{
        this.mockMvc.perform(put("/study/setStatus")
                .content("{\"id\":\"5\", \"status\":\"IN_PROGRESS\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}