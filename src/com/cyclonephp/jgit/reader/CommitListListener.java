package com.cyclonephp.jgit.reader;

import com.cyclonephp.jgit.model.Commit;
import java.util.List;

/**
 *
 * @author crystal
 */
public interface CommitListListener {

    public void commitListUpdated(List<Commit> commits);

}
