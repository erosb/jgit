package com.cyclonephp.jgit.model;

import java.util.Date;

/**
 *
 * @author crystal
 */
public class Commit extends GitObject {

    private Tree tree;

    private Commit parent;

    private String author;

    private String committer;

    private String message;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Commit getParent() {
        return parent;
    }

    public void setParent(Commit parent) {
        this.parent = parent;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
