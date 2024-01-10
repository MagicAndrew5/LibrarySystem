# Library Management System

This project is a Library Management System implemented using Java and Spring Boot. The system allows for the management of books, authors, genres, and library members. It supports various operations such as creating, reading, updating, and deleting (CRUD) these entities, as well as searching for books based on certain attributes.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Entities](#entities)
- [Operations](#operations)
- [Inheritance](#inheritance)
- [Relationships](#relationships)
- [Installation](#installation)
- [Additional Notes](#additional-notes)
- [Usage](#usage)
- [Contributors](#contributors)
- [License](#license)

## Prerequisites

Make sure you have installed:

- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL](https://www.mysql.com/)

## Entities

The system includes the following entities:

- `Book`: Represents a book in the library. Attributes include ISBN, title, author, genre, and published year.
- `Author`: Represents an author of books. Attributes include author ID, name, surname and birthdate.
- `Genre`: Represents a genre of books. Attributes include genre ID and name.
- `LibraryMember`: Represents a member of the library. Attributes include member ID, name, surname, and membership start date.
- `EBook`: Represents an electronic book. This entity inherits from `Book` and includes additional attributes such as format and size Mb.
- `User` : Represents a user of the system. Attributes include user ID, name, surname, username, password, email, phone number and city.

## Operations

The system supports the following operations:

- **Create**: Add new books, authors, genres, library members, users.
- **Read**: Display information about books, authors, genres, library members and users. View books borrowed by a library member, historical books, and all books that are in the library.
- **Update**: Modify information about existing books, authors, genres, and library members.
- **Delete**: Remove books, authors, genres, and library members from the system.
- **Search**: Search for books with a specific title and author. Ability to search for EBooks. Search for books by doing a more advanced search by passing as a parameter the nationality of the author and the publisher of the book.

## Inheritance

The `EBook` entity inherits from the `Book` entity, representing an electronic version of a book with additional attributes such as format and size Mb.

## Relationships

The system includes the following relationships between entities:

- **Many-to-Many**: A book can have multiple authors and an author can write multiple books.
- **Self-Loop**: An author can collaborate with other authors, represented as a self-loop relationship.
- **Many-to-One**: A book has one genre, but a genre can be associated with many books.
- **Many-to-Many**: A library member can borrow multiple books and a book can be borrowed by multiple library members.

## Installation

This project uses [GitLab](https://gitlab.com/) for version control and manages the database configuration through an ignored `application.properties` file for security reasons. To successfully run the project, you need to create a local copy of the `application.properties` file with the correct database information.

1. **Clone the repository:**
   ```bash
   git clone https://gitlab.com/andrew1234567/2023_assignment03_librarysystem.git
   cd 2023_assignment03_librarysystem
   ```
   
2. **Copy `application.properties`:**
    - Create a copy of the `application.properties` file with your database configurations. The file should be located in the `src/main/resources/` directory. Ensure not to share this file with the repository, as it may contain sensitive information such as database usernames and passwords.

3. **Database configuration:**
    - Ensure you have a MySQL database created with the name "library" or update the datasource URL in the `application.properties` file with your database information.

4. **Run the application:**
    - You can use your preferred development environment to run the application. For example, with Maven:
      ```bash
      ./mvnw spring-boot:run
      ```

5. **Check the execution:**
    - Open your browser and go to [http://localhost:8080](http://localhost:8080) to verify that the application is running correctly.

## Others Folder

The `others` folder contains scripts that generate various data for the project. These scripts are written in Python and are organized in the `generatevalue` subfolder. Here's a brief overview of each script:

- `GenerateAuthors.ipynb`: This script generates a list of authors with random names, surnames, birth dates, and nationalities. The output is saved in a CSV file named `author.csv`.

- `GenerateBooks.ipynb`: This script generates a list of books with random attributes such as file size, genre, ISBN, book type, author, publisher, title, and format. The output is saved in a CSV file named `booksR.csv`.

- `GenerateGenre.ipynb`: This script generates a list of book genres. The output is saved in a CSV file named `genre.csv`.

Please note that these scripts are used for generating dummy data for testing purposes.

Also encompasses various elements contributing to a comprehensive understanding of the project:

- `diagrams:` This folder holds UML diagrams, providing visual representations of the system's structure and behavior. These diagrams are invaluable for grasping the application's overall design and architecture.

- `mysqldatabase:` Containing a MySQL database dump, this folder facilitates database setup by offering pre-populated data for testing and development.

- `screenshot:` For a visual representation of the application's user interface, the `screenshot` folder stores images capturing the application in action when running on `localhost:8080`.

These components collectively contribute to the efficient development, testing, and documentation of the project. For further details on each script or additional elements, refer to the specific subfolders within `others`.

## Additional Notes

- Never share the `application.properties` file with your database credentials in the repository. Add the file to your `.gitignore` to avoid tracking.
- For further details on Spring Boot configuration, refer to [the official documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/).

## Usage

To run the Library Management System, navigate to the target directory and run the command `java -jar librarysystem-0.0.1-SNAPSHOT.jar`.

## Contributors

This project is maintained by a team of dedicated developers. Contributions are welcome. Please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License.

