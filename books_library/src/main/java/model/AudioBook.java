package model;

import helper.Id;

import java.time.LocalDate;
import java.util.Objects;

public class AudioBook {
    @Id
    private Long id;
    private String author;
    private String title;
    private LocalDate publishedDate;
    private int runTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioBook audioBook = (AudioBook) o;
        return runTime == audioBook.runTime && Objects.equals(id, audioBook.id) && Objects.equals(author, audioBook.author) && Objects.equals(title, audioBook.title) && Objects.equals(publishedDate, audioBook.publishedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, publishedDate, runTime);
    }

    @Override
    public String toString() {
        return "AudioBook{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishedDate=" + publishedDate +
                ", runTime=" + runTime +
                '}';
    }
}
