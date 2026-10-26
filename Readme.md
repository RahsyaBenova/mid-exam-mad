# Student Management Mobile Application (Case 1)

A complete Android Studio Java application built for a Mobile Application Development mid-examination. This app allows users to manage student records using a local **SQLite database**, featuring **Create, Read, Update, and Delete (CRUD)** operations with a unique student identifier constraint.

---

## Features

- **Add Student Records:** Register student names, unique student IDs, and select their major via a dropdown spinner (**Informatics** or **Information Systems**).
- **Unique ID Constraint:** The application enforces that the `STUDENT_ID` acts as the primary key, preventing duplicate entries.
- **View Records on a Separate Page:** Clicking the **VIEW** button navigates to a dedicated page containing a scrollable `ListView` displaying all saved student entries.
- **Edit Records:** Update existing student details based on their unique Student ID.
- **Delete Records:** Remove student records securely from the SQLite database.

---

## Project Structure

The project consists of the following core components:

1. **`DatabaseHelper.java`**: Extends `SQLiteOpenHelper` to handle database creation, table setup (`student_table`), and CRUD operations (`insertData`, `getAllData`, `updateData`, `deleteData`).
2. **`MainActivity.java`**: The primary user interface managing input validation, database triggers, and navigation intents.
3. **`ViewStudentActivity.java`**: The secondary page handling data extraction via `Cursor` and populating the `ListView`.
4. **`activity_main.xml`**: Layout file for the input form (EditTexts, Spinner, and CRUD buttons).
5. **`activity_view_student.xml`**: Layout file for the second page containing the `ListView`.

---

## Getting Started & Installation

### Prerequisites
- **Android Studio** installed on your machine.
- An Emulator or a physical Android device configured for debugging.

### Setup Instructions
1. Open **Android Studio** and create a new project with an **Empty Activity** (Java language).
2. Create the packages and classes as provided in the implementation:
   - Create `DatabaseHelper.java`
   - Create `ViewStudentActivity.java`
3. Update your layout XML files (`activity_main.xml` and `activity_view_student.xml`).
4. Ensure your `AndroidManifest.xml` includes the declaration for the secondary activity:
   ```xml
   <activity android:name=".ViewStudentActivity" />
   ```
5. Build and run the project on your emulator or physical device.

---

## Usage Guide

1. **Adding Data:** Type the student's name, enter a unique Student ID, select their major from the spinner, and tap **ADD**.
2. **Viewing Data:** Tap **VIEW** to open the records page displaying all stored students in a list format.
3. **Editing Data:** Enter an existing Student ID along with updated details, then tap **EDIT**.
4. **Deleting Data:** Enter the target Student ID and tap **DELETE** to remove the entry from the database.