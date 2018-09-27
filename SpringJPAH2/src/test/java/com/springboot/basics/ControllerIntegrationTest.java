package com.springboot.basics;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.basics.controller.PersonController;
import com.springboot.basics.entity.Person;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerIntegrationTest {
	
MockMvc mockMvc;
	
    @Autowired
    protected WebApplicationContext wac;
    
    @Autowired
    PersonController controller;
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.controller).build();// Standalone context
    }
    
    Person person1;
    @Before
    public void setUpProduct() throws Exception{
    	person1 = new Person();
    	person1.setId(1);
    	person1.setName("Roman Test");
    	person1.setLocation("Mexico");
    	person1.setBirth_date(new Date());
    	
    }

    @Test
    public void testFindAllSync() throws Exception {
        mockMvc.perform(get("/person/findall").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.*.name", hasItem(is("Juan"))));
    }
    
    @Test
    public void testFindById() throws Exception {
    	mockMvc.perform(get("/person/getbyid/10001").contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json;charset=UTF-8"))
    	.andExpect(content().string(Matchers.containsString("Roman")));
    }
    
    @Test
    public void testUpdate() throws Exception {
    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/person/update")
    					.content(asJsonString(person1))
    					.contentType(MediaType.APPLICATION_JSON)
    					.accept(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void testDelete() throws Exception {
    	
    	mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete/10004").contentType(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
    	    	
    }
    
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  

}
