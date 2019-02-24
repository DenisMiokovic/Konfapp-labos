package hr.vvg.Miokovic.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControllerPredavanjeTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	ControllerPredavanjeTest(MockMvc mockMvc) {
		
		this.mockMvc = mockMvc;
	}

	@Test
	public void prikazPredavanja() throws Exception {
		
		this.mockMvc.perform(get("/svaPredavanja").with(user("test").password("testpass").roles("USER", "ADMIN")))
		.andExpect(status().isOk())
		.andExpect(view().name("svaPredavanja"));
	}

	@Test
	public void unosPredavanjaGET() throws Exception {
		
		this.mockMvc.perform(get("/novoPredavanje").with(user("test").password("testpass").roles("USER", "ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("predavanje"))
		.andExpect(model().attributeExists("vrste"))
		.andExpect(model().attributeExists("pozicije"))
		.andExpect(view().name("novoPredavanje"));
	}
	
	@Test
	public void unosPredavanjaPOST() throws Exception {
		
		this.mockMvc.perform(post("/novoPredavanje").with(user("test").password("testpass").roles("USER", "ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("predavanje"))
		.andExpect(view().name("novoPredavanje"));
	}
	
	@Test
	public void pretragaPredavanjaGET() throws Exception {
		
		this.mockMvc.perform(get("/pretragaPredavanja").with(user("test").password("testpass").roles("USER", "ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("predavanje"))
		.andExpect(view().name("pretragaPredavanja"));
	}
	
	@Test
	public void pretragaPredavanjaPOST() throws Exception {
		
		this.mockMvc.perform(post("/pretragaPredavanja").with(user("test").password("testpass").roles("USER", "ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("nadenaPredavanja"))
		.andExpect(view().name("pretragaPredavanja"));
	}
}
