![gitnotion](https://github.com/user-attachments/assets/079fdd2e-ba20-4a5b-9801-58448e81d8b9)

# GitHub Resume Builder

The GitHub Resume Builder is a Spring Boot application that allows users to generate an ATS-friendly resume using their GitHub profile information. Users can enter their GitHub username, select relevant repositories, and provide additional personal details such as phone number and location. The application then generates a professional-looking resume in a standard format, with headings in Helvetica Bold and body text in Arial. The resume is displayed on the website and can be downloaded as a PDF.

## Team Members

1. [Goureesh Chandra](https://github.com/Herogo3241)

## Link to Product Walkthrough

![[Watch the Demo Video]](https://youtu.be/HGsjjNDPex8)

## How It Works

1. **User Input**:
   - Users enter their GitHub username on the main page.
   - Additional details such as phone number and location are also provided.

2. **GitHub Data Retrieval**:
   - The application fetches user profile information and repositories from GitHub using the GitHub API.
   - Users can select which repositories they want to include in their resume.

3. **Resume Generation**:
   - The application compiles the selected repositories, user profile details, and additional information into a structured resume format.
   - The resume is formatted with Helvetica Bold for headings and Arial for body text.

4. **Resume Display and Download**:
   - The generated resume is displayed on the website.
   - Users can download the resume as a PDF by clicking the "Download Resume as PDF" button.

## Libraries Used

- **Spring Boot** - Version 3.x
- **Thymeleaf** - Version 4.x
- **iText** - Version 5.x
- **Spring Web** - Version 3.x

## How to Configure

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/yourusername/github-resume-builder.git
   cd github-resume-builder
   ```


2. **Configure Application Properties**:

   - Update any other required properties in `src/main/resources/application.properties`.

## How to Run

1. **Build and Run the Application**:

   ```bash
   ./gradlew bootRun
   ```

2. **Access the Application**:

   Open your browser and navigate to [http://localhost:8080](http://localhost:8080).

3. **Test the Application**:

   - Enter a GitHub username and additional details.
   - Select repositories and generate the resume.
   - Download the resume as a PDF to ensure functionality.

