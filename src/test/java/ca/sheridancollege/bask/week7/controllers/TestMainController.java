package ca.sheridancollege.bask.week7.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridancollege.bask.week7.beans.Inventory;


@RunWith(SpringRunner.class)//defines the test runner
@SpringBootTest      //tells spring to look for a main config class to create testing environment
@AutoConfigureMockMvc //allow us to test mock requests and fake responses
public class TestMainController {
	
	@Autowired
	private MockMvc mockMvc;//MockMvc object to perform pretend http requests
	
	
	/**
	 * Test the add item method
	 * @throws Exception
	 */
	@Test
	public void testAddItem() throws Exception {
		//construct an inventory object
		Inventory testInventory = new Inventory(32165, "Manos del Uruguay Fino Storm Glass",
				10, 19.99);
		//perform method allows to perfom fake post request to url "/additem".
		//flassAttribute allow us to set a pretend model attribute
		mockMvc.perform(post("/addItem").flashAttr("inv", testInventory))
			.andExpect(status().isOk())//status is OK expected
			.andExpect(view().name("inventory.html"));//inventory.html is expected
		
	}
	
	
	/**
	 * Test the loading index pass
	 * @throws Exception
	 */
	
	@Test
	public void testIndexPass() throws Exception {
		//perform method allows you to perfom fake request to the root
		mockMvc.perform(get("/"))//if get request to root
		.andExpect(model().attributeExists("inv"))//check if the model attribute exists
		.andExpect(status().isOk())//status is OK expected
		.andExpect(view().name("index.html"))//loads the index.html expected
		.andDo(print()); //prints the output of the method
	}
	
	/**
	 * Test the loading index fail
	 * @throws Exception
	 */
	@Test
	public void testIndexFail() throws Exception {
		//test a mock url to load index page
		mockMvc.perform(get("/foo")).andExpect(status().isNotFound());
	}

}
