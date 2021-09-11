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
package ec.edu.espe.distribuidas.pruebamongo.controller;

import ec.edu.espe.distribuidas.pruebamongo.dto.TransaccionRQ;
import ec.edu.espe.distribuidas.pruebamongo.model.Transaccion;
import ec.edu.espe.distribuidas.pruebamongo.service.TransaccionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/transaccion")
public class TransaccionController {
    
    private final TransaccionService service;

    public TransaccionController(TransaccionService service) {
        this.service = service;
    }
    
    @ApiOperation(value = "Crear una transaccion",
            notes = "Inserción de una transaccion")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. La transaccion fue creada correctamente"),
        @ApiResponse(code = 400, message = "Bad Request. No se pudo crear la transaccion"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity crearConsulta(@RequestBody TransaccionRQ request) {
        try {
            log.info("Se va a crear una transaccion con la siguiente informacion: {}", request);
            Transaccion transaccion = new Transaccion();
            transaccion.setCodigoCajero(request.getCodigoCajero());
            transaccion.setTipo(request.getTipo());
            transaccion.setMonto(request.getMonto());
            transaccion.setBilletes10(request.getBilletes10());
            transaccion.setBilletes20(request.getBilletes20());
            this.service.crearTransaccion(transaccion);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Ocurrio un error al crear la transaccion. {} - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
}
