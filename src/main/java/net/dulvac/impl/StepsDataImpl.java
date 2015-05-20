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
