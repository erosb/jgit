package com.cyclonephp.jgit.reader;

import com.cyclonephp.jgit.GitCommand;
import com.cyclonephp.jgit.JGitException;
import com.cyclonephp.jgit.model.Blob;
import com.cyclonephp.jgit.model.Commit;
import com.cyclonephp.jgit.model.GitObject;
import com.cyclonephp.jgit.model.Tag;
import com.cyclonephp.jgit.model.Tree;
import java.util.Date;

/**
 *
 * @author crystal
 */
public enum GitObjectReader {

    COMMIT {

        public Commit load(String hash) {
            if (Commit.getObjects().containsKey(hash) && ((Commit) Commit.getObjects().get(hash)).isLoaded()) {
                return (Commit) Commit.getObjects().get(hash);
            }
            System.out.println("reading commit " + hash);
            String str = read(hash);
            Commit rval = new Commit();
            rval.setLoaded(true);
            rval.setHash(hash);
            String[] lines = str.split("\n");
            int ctr;
            for (ctr = 0; ctr < lines.length; ++ctr) {
                if (lines[ctr].trim().isEmpty())
                    break;
                
                String[] line = lines[ctr].split(" ");
                if (line[0].equals("tree")) {
                    rval.setTree((Tree) TREE.load(line[1]));
                } else if (line[0].equals("parent")) {
                    rval.setParent((Commit) COMMIT.load(line[1]));
                } else if (line[0].equals("author")) {
                    StringBuilder strAuthor = new StringBuilder();
                    for (int i = 1; i < line.length - 2; ++i) {
                        strAuthor.append(line[i]).append(" ");
                    }
                    rval.setAuthor(strAuthor.toString());
                    Date commitTime = new Date(Integer.parseInt(line[line.length - 2]));
                    rval.setDate(commitTime);
                } else if (line[0].equals("committer")) {
                    StringBuilder strCommitter = new StringBuilder();
                    for (int i = 1; i < line.length - 2; ++i) {
                        strCommitter.append(line[i]).append(" ");
                    }
                    rval.setCommitter(strCommitter.toString());
                }
            }

            StringBuilder msg = new StringBuilder();
            // reading commit message
            for (; ctr < lines.length; ++ctr) {
                msg.append(lines[ctr]).append("\n");
            }

            rval.setMessage(msg.toString());
            return rval;
        }
    },
    TREE {

        public Tree load(String hash) {
            Tree rval = new Tree();
            rval.setHash(hash);

            //String str = read(hash);
            return rval;
        }
    },
    BLOB {

        public Blob load(String hash) {
            Blob rval = new Blob();
            rval.setHash(hash);
            //String str = read(hash);
            return rval;
        }
    },
    TAG {

        public Tag load(String hash) {
            Tag rval = new Tag();
            rval.setHash(hash);
            //String str = read(hash);
            return rval;
        }
    };

    protected String read(String hash) {
        return new GitCommand().arg("cat-file").arg("-p").arg(hash).exec();
    }

    public abstract GitObject load(String hash);
}
