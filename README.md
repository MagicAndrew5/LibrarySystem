# 2023_assignment03_librarysystem



## Require of Application 
The application must implement ************CRUD************ (Create, Read, Update, Delete) and at 
least a ************SEARCH************ operation on a set of ****************Entities**************** 
(the search operation must work with at least two entities in a relationship, searching for a single entity 
by ids or attributes is not enough)

There must be at least 4 entities in the system (with **********************Inheritance********************** a 
between at least 2 of entities) and at least 3 ************************Relationship************************ 
(including at least a **many-to-many** and **************a self-loop**************  relationship) 
between those entities. Arbitrarily define entities and attributes.

## Architecture of project
This project structure should provide a comprehensive implementation of CRUD operations and search functionalities, covering various types of relationships and inheritance in the context of a Library Management System.

To initiate the development of a Spring MVC application, begin by establishing the project structure. This involves setting up a Maven project, necessitating the creation of a `pom.xml` file to manage dependencies and project configuration.

The configuration of the Spring MVC framework involves the creation of essential components:

1. **Model:** Craft a straightforward Java class representing the model, typically serving as an entity for the application.
2. **Controller:** Develop a controller class annotated with `@Controller`. This class orchestrates the interaction between the model and the view.
3. **View:** Generate an HTML or JSP file within the designated `src/main/resources/templates` directory. This file serves as the user interface, presenting information to the end user.

This structured approach forms the foundation of a Spring MVC application, fostering clarity and modularity in the development process.

## Project: Library Management System
## Entities

- ********Book********
    - ************************************Attributes:************************************
        - ************************************ISBN************************************
        - ************************************Title************************************
        - ************************************Author************************************
        - ************************************Genre************************************
        - ************************************Published Year************************************
    - ******************************Relationships:******************************
        - Many-To-Many relationship with **Author**
        - Many-To-One relationship with **********Genre**********
- ************Author************
    - **Attributes**:
        - ******************Author ID******************
        - **Name**
        - **Birthdate**
    - **Relationships:**
        - Many-To-Many relationship with ********Book********
        - Self-Loop relationship for collaborations with other ************Author************
- **********Genre**********
    - **********************Attributes:**********************
        - ****************Genre ID****************
        - ********Name********
    - ****************************Relationships:****************************
        - One-To-Many relationship with ********Book********
- **************************LibraryMember**************************
    - ********************Attributes:********************
        - ********************Member ID********************
        - **********Name**********
        - **************Address**************
        - **Membership Start Date**
    - ****************************Relationships:****************************
        - Many-To-Many relationship with **********Book**********

## Operations

- **CREATE**:
    - Ability to add new books, authors, genre, and library members
- **READ**:
    - Display information about books, authors, genre and library members
    - View the books borrowed by a library member
- **UPDATE**:
    - Modify information about existing books, authors, genres and library members
- **DELETE**:
    - Remove books, authors, genres, and library members from the system
- **SEARCH**:
    - Implement a search operation that involves a Many-to-Many relationship (e.g., search for books by a specific author)
    - Implement a search operation that involves a Self-loop relationship (e.g., find co-authors for a given author)

## INHERITANCE

- **Book and EBook:**
    - Introduce an **EBook** entity that inherits from ********Book******** entity.
    - Additional attributes for ****EBook:****
        - ************Format************
        - **Download Link**

## RELATIONSHIPS

- **************************MANY-TO-MANY:**************************
    - ************Books************ and ****Authors**** have a Many-To-Many relationship, as an author can write multiple **********Books********** and a ********Book******** can have multiple **************Authors**************
- ********************SELF-LOOP:********************
    - **************Authors************** have a Self-Loop relationship, representing collaborations between authors
- ****************************************OTHER RELATIONSHIPS:****************************************
    - Many-To-One relationship between **********Book********** and **Genre**
    - Many-To-Many relationship between ****************************LibraryMember**************************** and ********Book******** (Books borrowed by a member)