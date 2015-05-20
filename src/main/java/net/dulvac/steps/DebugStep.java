package net.dulvac.steps;

import net.dulvac.StepsData;
import net.dulvac.impl.AbstractStep;
import net.dulvac.impl.StepConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugStep extends AbstractStep<DebugStepConfig> {

    private static final Logger LOG = LoggerFactory.getLogger(DebugStep.class);

    public DebugStep(Integer level, String name, StepsData config) throws Throwable {
        super(level, name, config);
    }

    @Override
    public void apply() {
        LOG.info(config.getMessage());
    }
}
