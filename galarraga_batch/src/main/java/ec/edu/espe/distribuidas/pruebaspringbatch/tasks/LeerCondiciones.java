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
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class LeerCondiciones implements Tasklet, StepExecutionListener {

    private final ApplicationValues applicationValues;

    public LeerCondiciones(ApplicationValues applicationValues) {
        this.applicationValues = applicationValues;
    }
    
    @Override
    public void beforeStep(StepExecution se) {
        log.info("Preparando motores");
    }

    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        log.info("Va a ejecutar la tarea leer condiciones");
        log.info("El archivo con condiciones es: {}", this.applicationValues.getConfigFile());
        Properties props = new Properties();
        try {
           
            props.load(new FileInputStream(this.applicationValues.getConfigFile()));

            Integer transacciones;
            
            try {
                transacciones = Integer.parseInt(props.getProperty("transacciones"));
                log.info("Va a generar {} transacciones", transacciones);
                ExecutionContext jobContext = cc.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
                jobContext.put("transacciones", transacciones);
            } catch (NumberFormatException e) {
                log.error("Invalid values");
            }

        } catch (IOException e) {
            log.error("Propertie file does not exists");
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        log.info("Finalizo la ejecucion");
        return ExitStatus.COMPLETED;
    }
}
