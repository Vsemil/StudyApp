package ru.firstline.studyapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Sql(value = "/create-doctor.sql")
public class DoctorControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenHomePageTest() throws Exception{
        mockMvc.perform(get("/"))
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
    public void saveTest() throws Exception {
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
}
