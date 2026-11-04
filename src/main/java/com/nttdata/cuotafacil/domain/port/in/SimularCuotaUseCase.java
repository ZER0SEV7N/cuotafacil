package com.nttdata.cuotafacil.domain.port.in;

import com.nttdata.cuotafacil.domain.model.SimulacionCuota;
import com.nttdata.cuotafacil.domain.model.SolicitudPrestamo;

public interface SimularCuotaUseCase {
	SimulacionCuota calcularSimulacion(SolicitudPrestamo solicitud);
}