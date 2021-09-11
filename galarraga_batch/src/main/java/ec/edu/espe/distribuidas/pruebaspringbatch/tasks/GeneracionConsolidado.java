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
package ec.edu.espe.distribuidas.pruebaspringbatch.tasks;

import ec.edu.espe.distribuidas.pruebaspringbatch.config.ApplicationValues;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.data.mongodb.core.MongoTemplate;

public class GeneracionConsolidado implements Tasklet, StepExecutionListener {

    private final ApplicationValues applicationValues;
    private final MongoTemplate mongoTemplate;

    public GeneracionConsolidado(ApplicationValues applicationValues, MongoTemplate mongoTemplate) {
        this.applicationValues = applicationValues;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void beforeStep(StepExecution arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        return null;
        
    }

}
