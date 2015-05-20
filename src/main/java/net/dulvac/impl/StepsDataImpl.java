package net.dulvac.impl;

import net.dulvac.StepsData;
import java.util.HashMap;
import java.util.Map;

public class StepsDataImpl implements StepsData {
    private Map<Long, Object> mapLevel;
    private Map<String, Object> mapName;

    public StepsDataImpl() {
        this.mapLevel = new HashMap<Long, Object>();
        this.mapName = new HashMap<String, Object>();
    }

    @Override
    public Object getData(Long stepLevel) {
        return mapLevel.get(stepLevel);
    }

    @Override
    public Object getData(String stepName) {
        return mapLevel.get(stepName);
    }
    
    @Override
    public synchronized void putData(Long level, String name, Object value) {
        this.mapLevel.put(level, value);
        this.mapName.put(name, value);
    }
}
