package com.nttdata.cuotafacil.infrastructure.adapter.in.web;

import com.nttdata.cuotafacil.domain.model.SimulacionCuota;
import com.nttdata.cuotafacil.domain.model.SolicitudPrestamo;
import com.nttdata.cuotafacil.domain.port.in.SimularCuotaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuotas")
public class CuotaController {

	private final SimularCuotaUseCase useCase;

	public CuotaController(SimularCuotaUseCase useCase) {
		this.useCase = useCase;
	}

	@PostMapping("/simular")
	public ResponseEntity<SimulacionCuota> simular(@RequestBody SolicitudPrestamo solicitud) {
		return ResponseEntity.ok(useCase.calcularSimulacion(solicitud));
	}
}