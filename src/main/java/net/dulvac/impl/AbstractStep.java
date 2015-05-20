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

import net.dulvac.StepsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;


import java.lang.reflect.ParameterizedType;

public abstract class AbstractStep <T extends StepConfig> extends BaseAbstractStep {
    public static final Logger LOG = LoggerFactory.getLogger(AbstractStep.class);
    protected T config;

    public AbstractStep(Integer level, String name, StepsData data, String configFile) throws Throwable {
        super(level, name, data);
        // Instantiate an empty config object of type T
        config = (T) getConfigType().newInstance();
    }

    public AbstractStep(Integer level, String name, StepsData data) throws Throwable {
        this(level, name, data, name + ".yaml");
    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public StepsData getData() {
        return data;
    }

    @Override
    public Class getConfigType() {
        return (Class) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

    @Override
    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setData(StepsData data) {
        this.data = data;
    }

    @Override
    public void setConfig(StepConfig config) {
        this.config = (T) config;
    }

    @Override
    public void readConfigString(String configString) {
        Constructor constructor = new Constructor(this.config.getClass());
        Yaml yaml = new Yaml(constructor);
        // load config
        try {
            this.config = (T) (yaml.load(configString));
        } catch (Exception e) {
            LOG.warn("Could not load config from string " + configString);
        }
    }

    @Override
    public T getConfig() {
        return config;
    }

}
