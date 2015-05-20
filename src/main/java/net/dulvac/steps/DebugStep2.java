/*************************************************************************
 *
 * ADOBE CONFIDENTIAL
 * __________________
 *
 *  Copyright 2014 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 **************************************************************************/
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
