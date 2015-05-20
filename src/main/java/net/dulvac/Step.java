package net.dulvac;

import net.dulvac.impl.StepConfig;

/**
 * *
 */
public interface Step <T extends StepConfig> {
    public Integer getLevel();
    public String getName();
    public StepsData getData();
    public T getConfig();
    public void setLevel(Integer level);
    public void setName(String name);
    public void setData(StepsData data);
    public void setConfig(T config);
    public Class getConfigType();
    public void readConfigString(String configString);
    public void apply();
}
