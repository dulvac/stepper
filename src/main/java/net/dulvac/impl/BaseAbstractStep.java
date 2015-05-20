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
