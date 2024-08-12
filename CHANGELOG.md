# Change Log of DawnPic

## v0.0.1

**Initial Release**

I am excited to announce the first release of DawnPic, an open-source picture bed system based on Spring Boot. This version includes the following features:

- **Database Support**: Integration with MySQL database using MyBatis.
- **Image Management**: Upload and retrieve images seamlessly.
- **Automatic Image Analysis**: Automatically analyze and extract image metadata, including name, extension, width, and height.

## v0.0.2

- **Image Upload Validation**: Implemented a check to verify the file extensions of uploaded images, ensuring only valid image files are accepted.

## v0.1.0

- **Refactor image identification**: replace auto-incremented integer IDs with uuid.

## v0.1.1

- **Database Update via RESTful API**: Added support for updating the database using RESTful API endpoints, enabling seamless integration and data management.
- **Web-Based Database Update**: Added support for updating the database via a web interface at the URL: `http(s):<domainName>:<port>/config/data-source/index.html`.

## v0.1.2

- **Refactor to MyBatis Plus**: Replaced `mybatis-spring-boot-starter` with `mybatis-plus` to tidy up the codebase, enhancing maintainability.

## v0.1.3

- **User Registration**: Implemented user registration functionality using Spring Security and JJWT, allowing new users to create accounts securely.
- **User Login**: Added user login feature with Spring Security and JJWT, enabling authenticated access to the application.