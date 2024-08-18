# JavaFX Project Setup

## Introduction
This project demonstrates how to set up a JavaFX application and run it using `Main.java`. It also includes instructions for adding the SQLite 3.7.2 database to your project.

## Prerequisites
- Java Development Kit (JDK) 11 or higher
- JavaFX SDK
- SQLite 3.7.2

## Download and Setup JavaFX

1. **Download JavaFX SDK**:
   - Go to the JavaFX Downloads page.
   - Download the appropriate JavaFX SDK for your operating system.

2. **Extract the JavaFX SDK**:
   - Extract the downloaded JavaFX SDK to a directory of your choice.

3. **Add JavaFX to Project Modules**:
   - Open your IDE (e.g., IntelliJ IDEA, Eclipse).
   - Go to your project settings and add the JavaFX SDK as a library/module.
   - For IntelliJ IDEA:
     - Go to `File > Project Structure > Libraries`.
     - Click `+` and select `Java`.
     - Navigate to the `lib` directory inside the extracted JavaFX SDK and add it.

## Download and Setup SQLite 3.7.2

1. **Download SQLite 3.7.2**:
   - Go to the SQLite Download Page.
   - Download the appropriate version for your operating system.

2. **Add SQLite to Project Modules**:
   - Extract the downloaded SQLite files.
   - Add the SQLite JDBC driver to your project.
   - For IntelliJ IDEA:
     - Go to `File > Project Structure > Libraries`.
     - Click `+` and select `Java`.
     - Navigate to the directory where you extracted the SQLite files and add the JDBC driver.

## Running the Program

1. **Ensure `Main.java` Exists**:
   - Make sure you have a `Main.java` file with your JavaFX application code.

2. **Add VM Options**:
   - When running the program, you need to add VM options to specify the JavaFX modules.
   - In your IDE, go to the run configuration settings.
   - Add the following VM options (adjust the path to your JavaFX SDK):

    ```sh
    --module-path /path/to/javafx-sdk-17/lib --add-modules javafx.controls,javafx.fxml
    ```

3. **Run `Main.java`**:
   - Run the `Main.java` file from your IDE.
   - Ensure the VM options are correctly set to include the JavaFX modules.

## Conclusion
You have successfully set up a JavaFX project and added SQLite 3.7.2 to your project modules. You can now run your JavaFX application using `Main.java`.

Feel free to reach out if you have any questions or need further assistance!
