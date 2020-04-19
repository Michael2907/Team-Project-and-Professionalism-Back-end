package com.groupProject.ANPRAPI.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupProject.ANPRAPI.Domain.Blacklist;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BlacklistControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getBlacklist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/blacklist"))
                .andExpect(status().isOk());
    }

    @Test
    public void TEST_1addUserToBlacklist() throws Exception {
        Blacklist blacklist = new Blacklist();
        blacklist.setDescription("TEST");
        blacklist.setNumberPlate("NL62AMK");

        mvc.perform(MockMvcRequestBuilders.put("/api/blacklist")
                .param("userID", "36")
                .content(asJsonString(blacklist))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void TEST_2removeUserFromBlacklist() throws Exception {
        Blacklist blacklist = new Blacklist();
        blacklist.setDescription("TEST");
        blacklist.setNumberPlate("NL62AMK");

        mvc.perform(MockMvcRequestBuilders.delete("/api/blacklist")
                .param("userID", "36")
                .param("numberPlate", "NL62AMK"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
