/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cyclonephp.jgit;

import com.cyclonephp.jgit.reader.RepositoryReader;
import com.cyclonephp.jgit.ui.MainWindow;
import java.io.File;
import javax.swing.SwingUtilities;

/**
 *
 * @author crystal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppRuntime.setDir(new File("/media/private/tmp/gitorial"));
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
                RepositoryReader repoReader = AppRuntime.getCurrentRepoReader();
                if (repoReader != null) {
                    repoReader.addBranchListListener(mainWindow);
                }
            }
        });
    }

}
