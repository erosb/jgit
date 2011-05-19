/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cyclonephp.jgit.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author crystal
 */
public class GitObject {

    protected static Map<String, GitObject> objects = new HashMap<String, GitObject>();

    public static Map<String, GitObject> getObjects() {
        return objects;
    }

    private String hash;

    private boolean loaded;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean isLoaded) {
        this.loaded = isLoaded;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
        GitObject.getObjects().put(hash, this);
    }

}
