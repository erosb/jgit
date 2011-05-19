package com.cyclonephp.jgit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author crystal
 */
public class Branch {

    protected static Map<String, Branch> branches = new HashMap<String, Branch>();

    public static Map<String, Branch> getBranches() {
        return branches;
    }

    private String name;

    private boolean current;

    private Commit head;

    private boolean loaded;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public Commit getHead() {
        return head;
    }

    public void setHead(Commit head) {
        this.head = head;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        branches.put(name, this);
    }

    @Override
    public String toString() {
        if (current) {
            return "* " + name;
        }
        return name;
    }

}
