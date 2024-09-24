# DawnPic

<div align="center">
  <img src="./dawnpic.png" alt="logo" width="150">
</div>

## Overview

**DawnPic** is an open-source picture bed system built with Spring Boot. It offers seamless image management, database support, and automatic image analysis. Whether you're a developer looking to integrate image handling into your application or someone who needs a reliable image storage solution, DawnPic has you covered. 📸

## Features

- **Database Support**: Integration with MySQL database using MyBatis.
- **Image Management**: Effortlessly upload and retrieve images.
- **Automatic Image Analysis**: Extracts and analyzes image metadata, including name, extension, width, and height.
- **Image Upload Validation**: Ensures only valid image files are accepted by verifying file extensions.
- **UUID Image Identification**: Uses UUIDs for better image identification.
- **User Management**: Supports user registration, login, and retrieval of user-specific information and images.

## Getting Started

### Prerequisites

- Java 17 or higher
- MySQL database

### Run

Download the jar file from the [GitHub releases page](https://github.com/hanyujie2002/DawnPic/releases), run it via `java -jar <jar-name>`.

## Usage Instructions

### User Registration

To create a new account, use the `/signup` endpoint. Provide the necessary user details in the request body.

### User Login

Log in to your account using the `/login` endpoint. Upon successful authentication, a JWT token will be stored in a Http-Only cookie.

### Current User Information

Retrieve information about the currently logged-in user using the `/current-user` endpoint.

### Uploading Images

To upload an image, use the `/api/upload` endpoint. Ensure the file extension is valid (e.g., .jpg, .png). Upon successful upload, you will receive a UUID as a response. 🎉

### Retrieving Images

Retrieve images using the `/api/images/{imageUuid}` endpoint, where `{imageUuid}` is the UUID of the image.

### User Images Information

Get information about images uploaded by a logged-in user using the `/api/userImages` endpoint.

### Change Data Sources

To change a data source, use the `/api/config/database` endpoint.

## Contributing

We welcome contributions! Please fork the repository and submit a pull request. Let's make DawnPic even better together! 🚀

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For any inquiries or support, please contact [yujiehan2002@outlook.com](mailto:yujiehan2002@outlook.com). We promise to respond faster than a speeding bullet! 📨
