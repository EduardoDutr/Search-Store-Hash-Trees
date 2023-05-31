package org.services;

import org.domain.Archive;
import org.structures.hashing.HashTable;
import org.structures.trees.Tree;

import java.io.File;

public class FileSearchSystem {
    private final HashTable<String, Archive> fileIndex;

    public FileSearchSystem() {

        fileIndex = new HashTable<>(0.70);
    }

    public void indexFiles(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            indexFilesInDirectory(directory);
        } else {
            System.out.println("Diretório inválido.");
        }
    }

    private void indexFilesInDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    Archive metadata = createFileMetadata(file);
                    fileIndex.put(metadata.getName(), metadata);
                } else if (file.isDirectory()) {
                    indexFilesInDirectory(file);
                }
            }
        }
    }

    private Archive createFileMetadata(File file) {
        String name = file.getName();
        String path = file.getAbsolutePath();
        String type = getFileType(name);
        long size = file.length();
        long creationTime = file.lastModified();
        long modificationTime = file.lastModified();

        return new Archive(name, path, type, size, creationTime, modificationTime);
    }

    private String getFileType(String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex > 0 && extensionIndex < fileName.length() - 1) {
            return fileName.substring(extensionIndex + 1).toLowerCase();
        }
        return "Unknown";
    }

    public Tree<Archive> putArchiveIntoTree(String keyword, Tree<Archive> tree) {
        for (Archive metadata : fileIndex.values()) {
            if (metadata.getName().contains(keyword)) {
                tree.insert(metadata);
            }
        }
        return tree;
    }
}
