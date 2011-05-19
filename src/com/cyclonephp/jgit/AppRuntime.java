/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cyclonephp.jgit;

import com.cyclonephp.jgit.reader.RepositoryReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.print.DocFlavor.BYTE_ARRAY;

/**
 *
 * @author crystal
 */
public class AppRuntime {

    private final static String PREF_KEY = "LAST_REPO";

    private static Preferences prefs = Preferences.userRoot().node(AppRuntime.class.getName());

    private static File dir;

    private static RepositoryReader currentRepoReader;

    public static RepositoryReader getCurrentRepoReader() {
        return currentRepoReader;
    }

    public static void setCurrentRepoReader(RepositoryReader currentRepoReader) {
        AppRuntime.currentRepoReader = currentRepoReader;
    }

    public static File getDir() {
        return dir;
    }

    public static void setDir(File dir) {
        AppRuntime.dir = dir;
        currentRepoReader = new RepositoryReader(dir);
        prefs.put(PREF_KEY, dir.getAbsolutePath());
    }

    static {
        String latest = prefs.get(PREF_KEY, null);
        if (latest != null) {
            dir = new File(latest);
            try {
                currentRepoReader = new RepositoryReader(dir);
            } catch (JGitException ex) {
                
            }
        }
    }

    public static String execGitCommand(GitCommand command) {
        String rootCommand = "git ";
        rootCommand += command.getArgs();
        try {
            Process proc = Runtime.getRuntime().exec(rootCommand, null, dir);
            InputStream stdout = proc.getInputStream();
            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return result.toString();
        } catch (IOException ex) {
            Logger.getLogger(AppRuntime.class.getName()).log(Level.SEVERE, "Failed to execute git command: " + rootCommand, ex);
        }
        return "";
    }

    private AppRuntime() {
        
    }

}
