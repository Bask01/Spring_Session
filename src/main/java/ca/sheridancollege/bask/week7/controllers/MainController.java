package ca.sheridancollege.bask.week7.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.bask.week7.beans.Inventory;
import ca.sheridancollege.bask.week7.beans.Name;

@Controller
public class MainController {

//	@GetMapping("/")
//	public String index(HttpSession session) {
//		
//		if(session.isNew()) { // if seesion is new
//			session.setAttribute("message", "Welcome!"); //display a welcome message
//		}else { // if session is not new
//			session.setAttribute("message", "Welcome Back!"); // display a welcome back message
//		}
//		return "index.html";				
//	}

	
	/**
	 * Handler method to load the index page
	 * inventory object will be stored in the model
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/")
	public String index(Model model) {

		// create an empty inventory object and add to the model to binding the form
		model.addAttribute("inv", new Inventory());
		return "index.html";
	}
	
	/**
	 * handler method to add item
	 * @param model
	 * @param inv
	 * @param session
	 * @return
	 */
	@PostMapping("/addItem")
	public String addItem(Model model, @ModelAttribute Inventory inv, HttpSession session) {

		// everything is stored as a session attribute in a Object type variable
		Object o = session.getAttribute("inventory");
		List<Inventory> list = null;// create an empty list variable

		if (o == null) { // if there is no session attribute called as inventory,
			list = new CopyOnWriteArrayList<Inventory>(); // instantiate a new inventory list

		} else {// if there is an existing list cast it to CopyOnWriteArrayList
			list = (CopyOnWriteArrayList<Inventory>)o;
		}
		list.add(inv);// add the inventory object into the list
		session.setAttribute("inventory", list);//re-add the list to the session

		return "inventory.html";
	}
	
	
	/**
	 * Method to Load the inventory page
	 * @return
	 */
	@GetMapping("/invList")
	public String invList() {
		return "inventory.html";
	}
	
	
	
/**
 * Method to load name form
 * @param model
 * @param session
 * @return
 */
	@GetMapping("/name")
	public String addName(Model model, HttpSession session) {
		//create a name object, keep it in the model
		model.addAttribute("userName", new Name());
		
		return "name.html";
	}
	
	/**
	 * method to load name output page
	 * sidplays the username
	 * @param model
	 * @param userName
	 * @param session
	 * @return
	 */
	@PostMapping("/testName")
	public String testName(Model model, @ModelAttribute Name userName,
			HttpSession session) {
		
		model.addAttribute("u", userName);
		//add the username into session attribute
		session.setAttribute("uName", userName); 
		//load the output page
		return "output.html";
	}
	
	/**
	 * Method for back button
	 * loads the name page
	 * @param model
	 * @param userName
	 * @param session
	 * @return
	 */
	@GetMapping("/back")
	public String goBack(Model model, @ModelAttribute Name userName,
			HttpSession session) {
				
		return "name.html";
	}
	
	/**
	 * logout method to invalidate the session
	 * loads again name page
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/logOut")
	public String logOut(Model model,HttpSession session) {
		
		session.invalidate();
		
		return "name.html";
		
	}
	


}
