/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cyclonephp.jgit.reader;

import com.cyclonephp.jgit.model.Branch;
import java.util.List;

/**
 *
 * @author crystal
 */
public interface BranchListListener {

    public void branchListUpdated(List<Branch> branches);

}
