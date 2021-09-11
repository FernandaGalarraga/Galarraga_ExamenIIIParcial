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

import ec.edu.espe.distribuidas.pruebamongo.dao.CajeroRepository;
import ec.edu.espe.distribuidas.pruebamongo.dao.ConsolidadoRepository;
import ec.edu.espe.distribuidas.pruebamongo.dao.TransaccionRepository;
import ec.edu.espe.distribuidas.pruebamongo.exception.CreateException;
import ec.edu.espe.distribuidas.pruebamongo.model.Cajero;
import ec.edu.espe.distribuidas.pruebamongo.model.Consolidado;
import ec.edu.espe.distribuidas.pruebamongo.model.Transaccion;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransaccionService {
    private final TransaccionRepository transaccionRepo;
    private final CajeroRepository cajeroRepo;

    public TransaccionService(TransaccionRepository transaccionRepo, CajeroRepository cajeroRepo) {
        this.transaccionRepo = transaccionRepo;
        this.cajeroRepo = cajeroRepo;
    }

    public void crearTransaccion(Transaccion transaccion){
        Optional<Cajero> cajeroOpt = this.cajeroRepo.findById(transaccion.getCodigoCajero());
        if(cajeroOpt.isPresent()){
            LocalDateTime fechaTransaccion = LocalDateTime.now(ZoneId.of("America/Chicago")).withNano(0);
            transaccion.setFecha(fechaTransaccion);
            transaccion.setEstado("PEN");
            this.transaccionRepo.save(transaccion);
        }else{
            throw new CreateException("No existe el cajero con codigo: "
                    + transaccion.getCodigoCajero());
        }
    }
}
