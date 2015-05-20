package net.dulvac.impl;

import net.dulvac.Runner;
import net.dulvac.Step;
import net.dulvac.StepsData;
import net.dulvac.StepsMap;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class RunnerImpl implements Runner {
    public static final Logger LOG = LoggerFactory.getLogger(RunnerImpl.class);
    public static final String RUNNER_CONFIG_FILE_NAME = "runner.yaml";

    protected final StepsData stepsData;
    protected StepsMap steps;

    public RunnerImpl(String... configFiles) throws Throwable {
        if (null == configFiles || configFiles.length == 0) {
            configFiles = new String[] {RUNNER_CONFIG_FILE_NAME};
        }
        this.stepsData = new StepsDataImpl();
        this.steps = new StepsMapImpl();
        for (String configFile : configFiles) {
            readConfig(configFile);
        }
    }

    public RunnerImpl() throws Throwable {
        this(RUNNER_CONFIG_FILE_NAME);
    }

    @Override
    public StepsMap getSteps() {
        return steps;
    }
    
    @Override
    public void run() {
        for (Integer level : steps.levelKeySet()) {
            Step step = steps.getStep(level);
            LOG.info("Applying level " + level + " step " + step.getName());
            // apply step
            step.apply();
        }
    }

    private void readConfig(String configFile) throws Throwable {
        InputStream in;
        try {
            in = new FileInputStream(configFile);
        } catch (FileNotFoundException e) {
            // try the classpath
            in = RunnerImpl.class.getClassLoader().getResourceAsStream(configFile);
            if (null == in) {
                LOG.error("Could not find " + configFile + " in classpath or in path");
            }
        }
        
        TypeDescription desc = new TypeDescription(Map.class);
        Constructor constructor = new Constructor(desc);
        Yaml yaml = new Yaml(constructor);
        // Read the config
        Map<Integer, Map> c = (HashMap<Integer, Map>) yaml.load(in);
        
        // Read steps
        for (Object key : c.keySet()) {
            Integer level;
            String stepName;
            RunnerConfig conf;
            Step step;
            
            // key is either level or the step name
            if (key instanceof Integer) {
                level = (Integer) key;
                conf = getRunnerConfig(c.get(level), yaml);
                stepName = conf.getStepName();
                Class stepClass = Class.forName(conf.getClassName());
                step = (BaseAbstractStep) stepClass.getConstructor(Integer.class, String.class, StepsData.class)
                        .newInstance(level, stepName, stepsData);
            } else if (key instanceof String) {
                //continue;
                stepName = (String) key;
                step = steps.getStep(stepName);
                if (null == step) {
                    LOG.warn("Did not find existing step " + stepName + " in previous configuration");
                    continue;
                }
                level = step.getLevel();
                String className = null;
                String config = null;
                Map<String, String> confMap = c.get(stepName);
                if (null != confMap) {
                    className = confMap.get("className");
                    if (null != confMap.get("config")) {
                        config = yaml.dump(confMap.get("config"));
                    }
                }
                conf = new RunnerConfig();
                conf.setStepName(stepName);
                conf.setClassName((null != className) ? className : step.getClass().getCanonicalName());
                conf.setConfig(config);
                
                StepConfig sc = step.getConfig();
                Class stepClass = Class.forName(conf.getClassName());
                
                step = (BaseAbstractStep) stepClass.getConstructor(Integer.class, String.class, StepsData.class)
                        .newInstance(level, stepName, stepsData);
                step.setConfig(sc);
            } else {
                LOG.warn("Cannot process config with key " + key);
                continue;
            }
            
            // Read config from file if provided
            readConfig(step);
            
            // Read config 
            if (null != conf.getConfig()) {
                LOG.debug("Applying config to step {} ({}) from runner config", step.getName(), step.getClass());
                step.readConfigString(conf.getConfig());
            }
            
            // Add step
            synchronized (this) {
                steps.putStep(step);
            }
        }
        
    }

    private static RunnerConfig getRunnerConfig(Map<String, String> m, Yaml yaml) {
        String className = m.get("className");
        String stepName = m.get("stepName");
        String config = null;
        if (null != m.get("config")) {
            config = yaml.dump(m.get("config"));
        } 
        RunnerConfig c = new RunnerConfig();
        c.setClassName(className);
        c.setStepName(stepName);
        c.setConfig(config);
        return c;
    }

    // Static
    
    public static void readConfig(Step step, String configFile) {
        InputStream in;
        try {
            in = new FileInputStream(configFile);
        } catch (FileNotFoundException e) {
            // try the classpath
            in = step.getConfig().getClass().getClassLoader().getResourceAsStream(configFile);
            if (null == in) {
                LOG.info("Did not find " + configFile + " in classpath or in path. Ignoring config");
                return;
            }
        }

        // load config
        String configString = null;
        try {
            configString = IOUtils.toString(in, "UTF-8");
        } catch (IOException e) {
            LOG.warn("Cannot read config string", e);
            return;
        }
        step.readConfigString(configString);
    }

    public static void readConfig(Step step) {
        String fileName = step.getName() + ".yaml";
        readConfig(step, fileName);
    }
}
