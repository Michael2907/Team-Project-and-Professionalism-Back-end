package com.groupProject.ANPRAPI.resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActivityControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void GetActivityForDate() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/activity")
                .param("startDate", "2010-01-01")
                .param("endDate", "2020-01-01"))
                .andExpect(status().isOk());
    }

    @Test
    public void getActivity() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/activity"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCurrentlyParked() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/activity/currentlyParked"))
                .andExpect(status().isOk());
    }

    @Test
    public void postActivity() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/activity")
                .param("numberPlate", "NL62AMK")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
