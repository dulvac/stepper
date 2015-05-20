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
package net.dulvac.impl;

import net.dulvac.Step;
import net.dulvac.StepsData;

public abstract class BaseAbstractStep implements Step {
    protected Integer level;
    protected String name;
    protected StepsData data;

    public BaseAbstractStep(Integer level, String name, StepsData data) throws Throwable {
        this.level = level;
        this.name = name;
        this.data = data;
    }
}
