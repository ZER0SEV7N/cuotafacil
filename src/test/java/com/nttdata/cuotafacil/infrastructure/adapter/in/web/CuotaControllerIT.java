package com.nttdata.cuotafacil.infrastructure.adapter.in.web;

import com.nttdata.cuotafacil.domain.model.SolicitudPrestamo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CuotaControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void endpointSimularDebeRetornar200YCalculoCorrecto() throws Exception {
		SolicitudPrestamo request = new SolicitudPrestamo(5000.0, 15.0, 6);

		mockMvc.perform(post("/api/cuotas/simular")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(request)))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.monto").value(5000.0))
						.andExpect(jsonPath("$.plazoMeses").value(6))
						.andExpect(jsonPath("$.cuotaMensual").value(870.17));
	}
}