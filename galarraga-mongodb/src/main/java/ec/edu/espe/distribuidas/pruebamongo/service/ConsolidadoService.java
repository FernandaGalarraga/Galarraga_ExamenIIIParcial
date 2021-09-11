/*
 * Copyright (c) 2021 mafer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mafer - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.pruebamongo.service;

import ec.edu.espe.distribuidas.pruebamongo.dao.ConsolidadoRepository;
import ec.edu.espe.distribuidas.pruebamongo.exception.CreateException;
import ec.edu.espe.distribuidas.pruebamongo.model.Consolidado;
import java.math.BigDecimal;
import java.util.List;

public class ConsolidadoService {
    private final ConsolidadoRepository consolidadoRepo;

    public ConsolidadoService(ConsolidadoRepository consolidadoRepo) {
        this.consolidadoRepo = consolidadoRepo;
    }
    
    public List<Consolidado> listarMontoMenor(BigDecimal monto){
        Consolidado consolidado = new Consolidado();
        if(consolidado.getMontoDisponible().compareTo(monto)==-1){
            return this.consolidadoRepo.findByMontoDisponibleLessThan(monto);
        }else{
            throw new CreateException("No se ha encontrado montos menores al valor ingresado");
        }
    }
    
    public Consolidado listarPorCajero(String cajero){
        return this.consolidadoRepo.findByCodigoCajero(cajero);
    }
}
