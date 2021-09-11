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

import ec.edu.espe.distribuidas.pruebamongo.model.Consolidado;
import ec.edu.espe.distribuidas.pruebamongo.service.ConsolidadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/consolidado")
public class ConsolidadoController {
    
    private final ConsolidadoService service;

    public ConsolidadoController(ConsolidadoService service) {
        this.service = service;
    }
    
    @ApiOperation(value = "Lista por monto menor", notes = "Lista completa de los montos menores")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. Se encontro montos menores al ingresado"),
        @ApiResponse(code = 500, message = "No se encontro")})
    @GetMapping(value = "monto/{monto}")
    public ResponseEntity listarTodos(@PathVariable("monto") BigDecimal monto) {
        List<Consolidado> consolidados = service.listarMontoMenor(monto);
        return ResponseEntity.ok(consolidados);
    }
    
    @ApiOperation(value = "Lista por monto y cantidad denominacion")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. Se encontro correctamente"),
        @ApiResponse(code = 500, message = "No se encontro")})
    @GetMapping(value = "id/{codigo}")
    public ResponseEntity listarTodos(@PathVariable("codigo") String codigo) {
        Consolidado consolidado = service.listarPorCajero(codigo);
        return ResponseEntity.ok(consolidado);
    }
}
