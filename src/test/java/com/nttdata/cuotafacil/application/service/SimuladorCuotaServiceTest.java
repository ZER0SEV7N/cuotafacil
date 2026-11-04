package com.nttdata.cuotafacil.application.service;

import com.nttdata.cuotafacil.domain.model.SimulacionCuota;
import com.nttdata.cuotafacil.domain.model.SolicitudPrestamo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorCuotaServiceTest {

	private final SimuladorCuotaService service = new SimuladorCuotaService();

	@Test
	void debeCalcularCuotaConInteresCorrectamente() {
		SolicitudPrestamo solicitud = new SolicitudPrestamo(10000.0, 12.0, 12);
		SimulacionCuota resultado = service.calcularSimulacion(solicitud);

		assertEquals(10000.0, resultado.monto());
		assertEquals(12, resultado.plazoMeses());
		assertEquals(888.49, resultado.cuotaMensual());
		assertEquals(10661.88, resultado.totalPagar());
	}

	@Test
	void debeCalcularCuotaSinInteresCorrectamente() {
		SolicitudPrestamo solicitud = new SolicitudPrestamo(1200.0, 0.0, 12);
		SimulacionCuota resultado = service.calcularSimulacion(solicitud);

		assertEquals(100.0, resultado.cuotaMensual());
		assertEquals(1200.0, resultado.totalPagar());
	}

	@Test
	void debeLanzarExcepcionConParametrosInvalidos() {
		SolicitudPrestamo solicitudInvalida = new SolicitudPrestamo(-500.0, 10.0, 12);
		assertThrows(IllegalArgumentException.class, () -> service.calcularSimulacion(solicitudInvalida));
	}
}