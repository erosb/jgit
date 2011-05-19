/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cyclonephp.jgit.reader;

import com.cyclonephp.jgit.GitCommand;
import com.cyclonephp.jgit.JGitException;
import com.cyclonephp.jgit.model.Branch;
import com.cyclonephp.jgit.model.Commit;
import com.cyclonephp.jgit.model.GitObject;
import com.cyclonephp.jgit.model.Tree;
import com.cyclonephp.jgit.ui.MainWindow;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crystal
 */
public class RepositoryReader {

    private File rootDir;

    private List<BranchListListener> branchListListeners = new ArrayList<BranchListListener>();

    private List<CommitListListener> commitListListeners = new LinkedList<CommitListListener>();

    public RepositoryReader(File rootDir) {
        for (File sub: rootDir.listFiles()) {
            if (sub.getName().equals(".git")) {
                this.rootDir = sub;
                break;
            }
        }
        if(this.rootDir == null) {
            try {
                throw new JGitException(rootDir.getCanonicalPath() + " is not a git repository");
            } catch (IOException ex) {
                throw new JGitException("Failed to throw JGitException", ex);
            }
        }
    }

    public void readRepository() {
        readBranches();
    }

    public void readCommits(Branch branch) {
        try {
            if (!branch.isLoaded()) {
                File branchFile = new File(rootDir.getAbsolutePath() + "/refs/heads/" + branch.getName());
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(branchFile)));
                Commit branchHead =
                        (Commit) GitObjectReader.COMMIT.load(in.readLine());
                branch.setHead(branchHead);
                branch.setLoaded(true);
            }
            List<Commit> commitList = new LinkedList<Commit>();
            for (Commit commit = branch.getHead(); commit != null; commit = commit.getParent()) {
                commitList.add(commit);
            }
            for (CommitListListener listener : commitListListeners) {
                listener.commitListUpdated(commitList);
            }
        } catch (FileNotFoundException ex) {
            throw new JGitException("Error: branch not found in repository: " + branch.getName(), ex);
        } catch (IOException ex) {
            throw new JGitException("Failed to read branch: " + branch.getName(), ex);
        }
    }

    public void readBranches() {
        String path = rootDir.getAbsolutePath() + "/refs/heads";
        for (File sub : new File(path).listFiles()) {
            Branch br = new Branch();
            br.setName(sub.getName());
            /*Commit branchHead =
            (Commit) GitObjectReader.COMMIT.load(in.readLine());
            br.setHead(branchHead);*/
        }
        System.out.println("ott: " + branchListListeners.size());
        for (BranchListListener listener : branchListListeners) {
            listener.branchListUpdated(new ArrayList<Branch>(Branch.getBranches().values()));
        }

    }

    public void addBranchListListener(BranchListListener listener) {
        branchListListeners.add(listener);
    }

    public void removeBranchListListener(BranchListListener listener) {
        branchListListeners.remove(listener);
    }

    public void addCommitListListener(CommitListListener listener) {
        commitListListeners.add(listener);
    }

    public void removeCommitListListener(CommitListListener listener) {
        commitListListeners.remove(listener);
    }

}
