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

import ec.edu.espe.distribuidas.pruebaspringbatch.tasks.GeneracionConsolidado;
import ec.edu.espe.distribuidas.pruebaspringbatch.tasks.LeerCondiciones;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class JobConfig {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ApplicationValues applicationValues;
    
    @Bean
    protected Step leerCondiciones() {
        return steps
                .get("leerCondiciones")
                .tasklet(new LeerCondiciones(this.applicationValues))
                .build();
    }
    
    @Bean
    protected Step generacionConsolidado() {
        return steps
                .get("generacionConsolidado")
                .tasklet(new GeneracionConsolidado(this.applicationValues, this.mongoTemplate))
                .build();
    }
    
    @Bean
    public Job generarConsolidado() {
        return jobs
                .get("generarPersonas")
                .start(leerCondiciones())
                .next(generacionConsolidado())
                .build();
    }
}
