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
package ec.edu.espe.distribuidas.pruebamongo.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "consolidado")
public class Consolidado {
    @Id
    private String id;
    private String codigoCajero;
    private Integer billetes10;
    private Integer billetes20;
    private BigDecimal montoDisponible;
    private String estado;
    
    List<Cajero> cajeros;
    
}
