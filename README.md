# DawnPic

![banner image](./dawnpic.png)

## Overview

**DawnPic** is an open-source picture bed system built with Spring Boot. It offers seamless image management, database support, and automatic image analysis. Whether you're a developer looking to integrate image handling into your application or someone who needs a reliable image storage solution, DawnPic has you covered.

## Features

- **Database Support**: Integration with MySQL database using MyBatis.
- **Image Management**: Effortlessly upload and retrieve images.
- **Automatic Image Analysis**: Extracts and analyzes image metadata, including name, extension, width, and height.
- **Image Upload Validation**: Ensures only valid image files are accepted by verifying file extensions.
- **UUID Image Identification**: Uses UUIDs for better image identification.

## Getting Started

### Prerequisites

- Java 17 or higher
- MySQL database
- Maven

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/hanyujie2002/DawnPic.git
    cd DawnPic
    ```

2. **Configure the database:**
   Update the `application.yaml` file with your MySQL database credentials.

3. **Build the project:**
    ```bash
    mvn clean install
    ```

4. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

Alternatively, you can download the jar file directly from the [GitHub releases page](https://github.com/hanyujie2002/DawnPic/releases).

## Usage Instructions

### Uploading Images

To upload an image, use the `/api/upload` endpoint. Ensure the file extension is valid (e.g., .jpg, .png). Upon successful upload, you will receive a UUID as a response.

### Retrieving Images

Retrieve images using the `/api/images/{imageUuid}` endpoint, where `{imageUuid}` is the UUID of the image.

### Change Data Sources

To change a data source, use the `/api/config/database` endpoint.

## Contributing

We welcome contributions! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For any inquiries or support, please contact [yujiehan2002@outlook.com](mailto:yujiehan2002@outlook.com).
