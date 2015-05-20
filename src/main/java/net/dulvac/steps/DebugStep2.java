package net.dulvac.steps;

import net.dulvac.StepsData;
import net.dulvac.impl.AbstractStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugStep2 extends AbstractStep<DebugStepConfig> {

    private static final Logger LOG = LoggerFactory.getLogger(DebugStep2.class);

    public DebugStep2(Integer level, String name, StepsData config) throws Throwable {
        super(level, name, config);
    }

    @Override
    public void apply() {
        LOG.info("DEBUG2");
        LOG.info(config.getMessage());
    }
}
