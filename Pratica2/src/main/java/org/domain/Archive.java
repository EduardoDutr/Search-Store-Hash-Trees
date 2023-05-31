package org.domain;

public class Archive implements Comparable<Archive> {
    private String name;
    private String path;
    private String type;
    private long size;
    private final long creationTime;
    private long modificationTime;

    public Archive(String name, String path, String type, long size, long creationTime, long modificationTime) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(long modificationTime) {
        this.modificationTime = modificationTime;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", creationTime=" + creationTime +
                ", modificationTime=" + modificationTime +
                '}';
    }

    @Override
    public int compareTo(Archive o) {
        return name.compareTo(o.getName());
    }
}
