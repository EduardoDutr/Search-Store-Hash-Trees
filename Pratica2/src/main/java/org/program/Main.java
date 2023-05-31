package org.program;

import org.domain.Archive;
import org.services.FileSearchSystem;
import org.structures.trees.RBTree;
import org.structures.trees.Tree;

public class Main {
    public static void main(String[] args) {
        FileSearchSystem fsi = new FileSearchSystem();

        fsi.indexFiles("/");

        Tree<Archive> archiveTree = new RBTree<>();

        fsi.putArchiveIntoTree("", archiveTree);

        archiveTree.printAll();
        System.out.println(archiveTree.size());
    }
}