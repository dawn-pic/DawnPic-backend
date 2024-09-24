# Change Log of DawnPic

## v1.0.0

- **Image Deletion**: Implemented endpoint to delete images.
- **User Images Info Endpoint**: Implemented an endpoint to retrieve information about user-uploaded images.

## v0.3.0

- **(BREAKING CHANGE) Deprecating Returning JWT Token as Response**: JWT tokens are now stored in Http-Only cookies to prevent XSS attacks.
- **Standard Token Expiration Time**: Standardized the JWT duration time to exactly one month (2,592,000 seconds). The duration time of the cookie is set to the same length.

## v0.2.0

- **(BREAKING CHANGE) Image User Info**: The `Image` entity class has been refactored to include a new field, `userId`, which corresponds to the ID of the user. For anonymous users, this ID is set to `0`.
- **User Image Uploading**: The image uploading functionality has been refactored to record the `userId` in the image table. If an image is uploaded without a logged-in user, the `userId` will be set to `0`.
- **User Image Information Retrieval**: Added support for retrieving information about images uploaded by a user through the `/api/userImages` endpoint. This API is accessible to logged-in users only.

## v0.1.3

- **User Registration**: Implemented user registration functionality using Spring Security and JJWT, allowing new users to create accounts securely.
- **User Login**: Added user login feature with Spring Security and JJWT, enabling authenticated access to the application.

## v0.1.2

- **Refactor to MyBatis Plus**: Replaced `mybatis-spring-boot-starter` with `mybatis-plus` to tidy up the codebase, enhancing maintainability.

## v0.1.1

- **Database Update via RESTful API**: Added support for updating the database using RESTful API endpoints, enabling seamless integration and data management.
- **Web-Based Database Update**: Added support for updating the database via a web interface at the URL: `http(s)://<domainName>:<port>/config/data-source/index.html`.

## v0.1.0

- **(BREAKING CHANGE) Refactor Image Identification**: Replaced auto-incremented integer IDs with UUID.

## v0.0.2

- **Image Upload Validation**: Implemented a check to verify the file extensions of uploaded images, ensuring only valid image files are accepted.

## v0.0.1

**Initial Release**

I am excited to announce the first release of DawnPic, an open-source picture bed system based on Spring Boot. This version includes the following features:

- **Database Support**: Integration with MySQL database using MyBatis.
- **Image Management**: Upload and retrieve images seamlessly.
- **Automatic Image Analysis**: Automatically analyze and extract image metadata, including name, extension, width, and height.
