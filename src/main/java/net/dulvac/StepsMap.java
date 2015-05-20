package net.dulvac;

import java.util.Set;

public interface StepsMap {
    Step getStep(Integer stepLevel);
    Step getStep(String stepName);
    void putStep(Step step);
    Set<Integer> levelKeySet();
    Set<String> nameKeySet();
}
