package ru.firstline.studyapp.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Sql(value = "/create-doctor.sql")
public class DoctorControllerTest extends WebTest{

    @Test
    public void givenHomePageTest() throws Exception{
        this.mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    public void getAllTest() throws Exception {
        this.mockMvc.perform(get("/doctors/all")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.content.length()", is(2)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("alex")))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.totalPages", is(1)));
    }

    @Test
    public void getByIdTest() throws Exception {
        this.mockMvc.perform(get("/doctors/{id}", 2)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("mike")));
    }

    @Test
    public void getByIdFailTest() throws Exception {
        this.mockMvc.perform(get("/doctors/{id}", 5)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void addTest() throws Exception {
        this.mockMvc.perform(post("/doctors/save")
                .content("{\"name\":\"gary\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("gary")));
    }

    @Test
    public void updateTest() throws Exception {
        this.mockMvc.perform(post("/doctors/save")
                .content("{\"name\":\"gary\", \"id\":\"2\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("gary")));
    }

    @Test
    public void deleteTest() throws Exception {
        this.mockMvc.perform(delete("/doctors/{id}", 1)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFailTest() throws Exception {
        this.mockMvc.perform(delete("/doctors/{id}", 5)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError());
    }
}
