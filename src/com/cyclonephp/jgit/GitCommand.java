/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cyclonephp.jgit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crystal
 */
public class GitCommand {

    private List<String> args = new ArrayList<String>();

    public GitCommand arg(String arg) {
        args.add(arg);
        return this;
    }

    public String getArgs() {
        StringBuilder sb = new StringBuilder();
        for (String arg: args) {
            sb.append(" ").append(arg);
        }
        return sb.toString();
    }

    public String exec() {
        return AppRuntime.execGitCommand(this);
    }

}
