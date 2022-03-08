package com.example.tacocloud;

import com.example.tacocloud.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HomeController.class)
class TacoCloudApplicationTests {

    @Autowired
    private MockMvc mockMvc;
//
//    @Test
//    void contextLoads() throws Exception {
//        mockMvc.perform(get("/home"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("home"))
//            .andExpect(content().string(containsString("Welcome")));
//    }
}
