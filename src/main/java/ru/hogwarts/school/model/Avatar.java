package ru.hogwarts.school.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String filePath;
    long fileSize;
    String mediaType;
    byte[] data;
    @OneToOne
    @JoinColumn(name = "student_id")
    Student student;

    public Long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avatar avatar)) return false;
        return getFileSize() == avatar.getFileSize() && Objects.equals(getId(), avatar.getId()) && Objects.equals(getFilePath(), avatar.getFilePath()) && Objects.equals(getMediaType(), avatar.getMediaType()) && Arrays.equals(getData(), avatar.getData());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getFilePath(), getFileSize(), getMediaType());
        result = 31 * result + Arrays.hashCode(getData());
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
