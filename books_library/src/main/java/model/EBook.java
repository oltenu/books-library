package model;

import helper.Id;

import java.time.LocalDate;
import java.util.Objects;

public class EBook {
    @Id
    private Long id;
    private String author;
    private String title;
    private LocalDate publishedDate;
    private String format;

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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EBook eBook = (EBook) o;
        return Objects.equals(id, eBook.id) && Objects.equals(author, eBook.author) && Objects.equals(title, eBook.title) && Objects.equals(publishedDate, eBook.publishedDate) && Objects.equals(format, eBook.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, publishedDate, format);
    }

    @Override
    public String toString() {
        return "EBook{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishedDate=" + publishedDate +
                ", format='" + format + '\'' +
                '}';
    }
}
