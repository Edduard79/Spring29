package com.example.Ex29;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StudentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateStudent() throws Exception {
        String studentJson = "{\"name\":\"Marta\",\"surname\":\"Bianchi\",\"working\":\"true\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .content(studentJson)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Marta"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Bianchi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.working").value(true))
                .andDo(print());
    }

    @Test
    public void testGetAllStudents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetStudentById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andDo(print());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        String updatedStudentJson = "{\"name\":\"Andrea\",\"surname\":\"Verdi\",\"working\":false}";

        mockMvc.perform(MockMvcRequestBuilders.put("/student/{id}", 1L)
                        .content(updatedStudentJson)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Andrea"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Verdi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.working").value(false))
                .andDo(print());
    }

    @Test
    public void testUpdateIsWorking() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/student/{id}", 1L)
                        .param("isWorking", "true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isWorking").value(true))
                .andDo(print());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}

