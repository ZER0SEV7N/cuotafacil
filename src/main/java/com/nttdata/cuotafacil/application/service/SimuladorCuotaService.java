package com.nttdata.cuotafacil.application.service;

import com.nttdata.cuotafacil.domain.model.SimulacionCuota;
import com.nttdata.cuotafacil.domain.model.SolicitudPrestamo;
import com.nttdata.cuotafacil.domain.port.in.SimularCuotaUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SimuladorCuotaService implements SimularCuotaUseCase {

	@Override
	public SimulacionCuota calcularSimulacion(SolicitudPrestamo solicitud) {
		if (solicitud.monto() <= 0 || solicitud.plazoMeses() <= 0 || solicitud.tasaAnualPorcentaje() < 0) {
			throw new IllegalArgumentException("Parámetros de préstamo inválidos");
		}

		double cuota;
		if (solicitud.tasaAnualPorcentaje() == 0) {
			cuota = solicitud.monto() / solicitud.plazoMeses();
		} else {
			double tasaMensual = (solicitud.tasaAnualPorcentaje() / 100.0) / 12.0;
			double factor = Math.pow(1 + tasaMensual, solicitud.plazoMeses());
			cuota = solicitud.monto() * (tasaMensual * factor) / (factor - 1);
		}

		double cuotaRedondeada = redondear(cuota);
		double totalRedondeado = redondear(cuotaRedondeada * solicitud.plazoMeses());

		return new SimulacionCuota(
						solicitud.monto(),
						solicitud.plazoMeses(),
						cuotaRedondeada,
						totalRedondeado
		);
	}

	private double redondear(double valor) {
		return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}