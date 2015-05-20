package net.dulvac.impl;

import net.dulvac.Step;
import net.dulvac.StepsMap;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class StepsMapImpl implements StepsMap {
    private SortedMap<Integer, Step> mapLevel;
    private SortedMap<String, Step> mapName;

    public StepsMapImpl() {
        this.mapLevel = new TreeMap<Integer, Step>();
        this.mapName = new TreeMap<String, Step>();
    }

    @Override
    public Step getStep(Integer stepLevel) {
        return mapLevel.get(stepLevel);
    }

    @Override
    public Step getStep(String stepName) {
        return mapName.get(stepName);
    }

    @Override
    public synchronized void putStep(Step step) {
        this.mapLevel.put(step.getLevel(), step);
        this.mapName.put(step.getName(), step);
    }
    
    @Override
    public Set<Integer> levelKeySet() {
        return this.mapLevel.keySet();        
    }

    @Override
    public Set<String> nameKeySet() {
        return this.mapName.keySet();
    }
}
