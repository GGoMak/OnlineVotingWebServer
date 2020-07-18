package com.ggomak.vote.springboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void main이_리턴된다() throws Exception {
        String testStr = "main";

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(testStr));
    }
}
