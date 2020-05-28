package com.generator.number.test;

import com.generator.number.controller.ListController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class ListControllerTest {
  private MockMvc mockMvc;
  
  @InjectMocks
  private ListController listControllerTest;
  
  @Before
  public void Setup() throws Exception {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[] { this.listControllerTest }).build();
  }
  
  @Test
  public void testGetAllList() {
    try {
      MvcResult result = this.mockMvc
        .perform((RequestBuilder)MockMvcRequestBuilders.get("/api/tasks/getAllTasks", new Object[0]))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
      String response = " \"Goal: 12, Step: 3\": \"045ca881-c478-4230-8056-46fff6111eb1\"";
      Assert.assertEquals("Response does not match", result.getResponse().getContentAsString(), response);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  @Test
  public void testGetNumList() {
    try {
      MvcResult result = this.mockMvc
        .perform((RequestBuilder)MockMvcRequestBuilders.get("/api/tasks/045ca881-c478-4230-8056-46fff6111eb1/getNumList", new Object[0]))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
      String response = "12, 9, 6, 3, 0";
      Assert.assertEquals("Response does not match", result.getResponse().getContentAsString(), response);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
