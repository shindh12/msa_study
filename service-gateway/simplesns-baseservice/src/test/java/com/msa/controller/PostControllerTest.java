package com.msa.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.controller.dto.PostDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	final String TOKEN = "f6405595568cebaec0037a980b4fafda2e257b7e5ce458829d64641d629b91d6";
	final String TITLE = "test title";
	final String CONTENT = "test content";
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
    public void addPost() throws Exception {
		PostDto dto = new PostDto(TITLE, CONTENT);
		this.mockMvc.perform(post("/post")
				.header("accesstoken",TOKEN)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("data")))
				.andExpect(jsonPath("$.data").exists())
				.andExpect(jsonPath("$.data.title").value(TITLE))
				.andExpect(jsonPath("$.data.content").value(CONTENT))
				.andDo(print());
	}
	
	@Test
	public void getSimplePostTest() throws Exception {
		this.mockMvc.perform(
				 get("/post/{postId}/simple", "1")
				 .header("accesstoken",TOKEN))
	            .andExpect(status().isOk())
	            .andExpect(content().string(containsString("data")))
	            .andExpect(jsonPath("$.data").exists())
	            .andExpect(jsonPath("$.data.id").exists())
	            .andExpect(jsonPath("$.data.id").value(1))
	            .andDo(print());
	}
	
	@Test
	public void getPostTest() throws Exception {
		this.mockMvc.perform(
				 get("/post/{postId}", "1")
				 .header("accesstoken",TOKEN))
	            .andExpect(status().isOk())
	            .andExpect(content().string(containsString("data")))
	            .andExpect(jsonPath("$.data").exists())
	            .andExpect(jsonPath("$.data.id").exists())
	            .andExpect(jsonPath("$.data.id").value(1))
	            .andExpect(jsonPath("$.data.user.id").value(1))
	            .andDo(print());
	}
}
