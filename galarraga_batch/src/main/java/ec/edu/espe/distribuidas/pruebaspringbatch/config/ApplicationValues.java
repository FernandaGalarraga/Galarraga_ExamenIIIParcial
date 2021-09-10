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
package ec.edu.espe.distribuidas.pruebaspringbatch.config;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ToString
public class ApplicationValues {
    private final String mongoHost;
    private final String mongoDB;
    private final String configFile;
    private final String dataPath;
    
    @Autowired

    public ApplicationValues(@Value("${pruebabatch.mongo.host}") String mongoHost,
            @Value("${pruebabatch.mongo.db}") String mongoDB,
            @Value("${pruebabatch.config.file}") String configFile,
            @Value("${pruebabatch.config.dataPath}") String dataPath
    ) {
        this.mongoHost = mongoHost;
        this.mongoDB = mongoDB;
        this.configFile = configFile;
        this.dataPath = dataPath;
        log.info("Propiedades Cargadas: "+this.toString());
    }

    public static Logger getLog() {
        return log;
    }
    
    public String getConfigFile() {
        return configFile;
    }

    public String getMongoHost() {
        return mongoHost;
    }

    public String getMongoDB() {
        return mongoDB;
    }

    public String getDataPath() {
        return dataPath;
    }
}
