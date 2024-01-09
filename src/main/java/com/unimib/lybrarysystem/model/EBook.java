package com.unimib.lybrarysystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;
import java.util.Set;

/**
 * Represents an electronic book entity in the library system.
 */
@Entity
@DiscriminatorValue("EBook")
public class EBook extends Book {
    private String format;
    private int fileSizeMB;

    /**
     * Constructs an EBook object with specified parameters.
     *
     * @param ISBN              The unique identifier for the book.
     * @param title             The title of the book.
     * @param author            The author of the book.
     * @param publisher         The publisher of the book.
     * @param authors           The set of authors who wrote the book.
     * @param genreList         The genre object associated with the book.
     * @param libraryMember     The library members who borrowed the book.
     * @param historianMembers  The library members who historian borrowed the book.
     * @param format            The format of the ebook (e.g., PDF, EPUB).
     * @param fileSizeMB        The file size of the ebook in megabytes.
     */
    public EBook(Integer ISBN, String title, String author, String publisher,
                 Set<Author> authors, Genre genreList, List<LibraryMember> libraryMember,
                 List<LibraryMember> historianMembers, String format, int fileSizeMB) {

        // Call the constructor of the superclass (Book)
        super(ISBN, title, author, publisher, authors, genreList, libraryMember, historianMembers);

        // Initialize attributes specific to EBook
        this.format = format;
        this.fileSizeMB = fileSizeMB;
    }

    public EBook() {

    }

    /**
     * Retrieves the format of the ebook.
     *
     * @return The format of the ebook.
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the format for the ebook.
     *
     * @param format The format to set for the ebook.
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Retrieves the file size of the ebook.
     *
     * @return The file size of the ebook in megabytes.
     */
    public int getFileSizeMB() {
        return fileSizeMB;
    }

    /**
     * Sets the file size for the ebook.
     *
     * @param fileSizeMB The file size to set for the ebook.
     */
    public void setFileSizeMB(int fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }

    /**
     * Returns a string representation of the EBook object.
     *
     * @return A string representation of the EBook.
     */
    @Override
    public String toString() {
        // Utilize the superclass's toString() method and add specific information for EBook
        return super.toString() +
                "EBook{" +
                "format='" + format + '\'' +
                ", fileSizeMB=" + fileSizeMB +
                '}';
    }
}
