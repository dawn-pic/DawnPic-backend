# Change Log of DawnPic

## v0.01

**Initial Release**

I am excited to announce the first release of DawnPic, an open-source picture bed system based on Spring Boot. This version includes the following features:

- **Database Support**: Integration with MySQL database using MyBatis.
- **Image Management**: Upload and retrieve images seamlessly.
- **Automatic Image Analysis**: Automatically analyze and extract image metadata, including name, extension, width, and height.

## v0.02

- **Image Upload Validation**: Implemented a check to verify the file extensions of uploaded images, ensuring only valid image files are accepted.

## v0.03

- **Refactor image identification**: replace auto-incremented integer IDs with uuid
