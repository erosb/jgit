/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cyclonephp.jgit;

/**
 *
 * @author crystal
 */
public class JGitException extends RuntimeException{

    public JGitException(Throwable cause) {
        super(cause);
    }

    public JGitException(String message, Throwable cause) {
        super(message, cause);
    }

    public JGitException(String message) {
        super(message);
    }

    public JGitException() {
        super();
    }

}
