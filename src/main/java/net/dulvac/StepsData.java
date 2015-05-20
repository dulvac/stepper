package net.dulvac;

/**
 *  
 */
public interface StepsData {
    public Object getData(Long stepLevel);
    public Object getData(String stepName);
    public void putData(Long level, String name, Object value);
}
