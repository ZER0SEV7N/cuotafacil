package com.nttdata.cuotafacil.domain.model;

public record SimulacionCuota(
				double monto,
				int plazoMeses,
				double cuotaMensual,
				double totalPagar
) {}