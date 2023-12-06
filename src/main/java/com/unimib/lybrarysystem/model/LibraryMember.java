package com.unimib.lybrarysystem.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "library_member")
public class LibraryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String address;

    @Column(nullable = false, length = 45)
    private String membershipDate;


    @ManyToMany
    @JoinTable(
            name = "library_member_book",
            joinColumns = @JoinColumn(name = "library_member_id"),
            inverseJoinColumns = @JoinColumn(name = "book_ISBN")
    )
    private Set<Book> borrowedBooks = new HashSet<>();


    public LibraryMember(Integer id, String name, String address, String membershipDate, Set<Book> borrowedBooks) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.membershipDate = membershipDate;
        this.borrowedBooks = borrowedBooks;
    }

    public LibraryMember() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }


    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", membershipDate='" + membershipDate + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryMember that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) &&
                Objects.equals(membershipDate, that.membershipDate) && Objects.equals(borrowedBooks, that.borrowedBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, membershipDate, borrowedBooks);
    }
}
