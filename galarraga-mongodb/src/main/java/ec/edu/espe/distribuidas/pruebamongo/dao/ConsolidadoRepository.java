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
package ec.edu.espe.distribuidas.pruebamongo.dao;

import ec.edu.espe.distribuidas.pruebamongo.model.Consolidado;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsolidadoRepository extends MongoRepository<Consolidado, String> {
    
    List<Consolidado> findByMontoDisponibleLessThan(BigDecimal monto);
    Consolidado findByCodigoCajero(String codigo);
}
