 <h1 align="center">Employee Database</h1>

 <br>

## About The Project

<img src = "screenshots/loaded_page.PNG">

This is a mobile application that functions as a basic employee database. It lists the names and emails of each employee in the table. It was created using Android Studio, Kotlin, and SQLite.

The primary files used in this application are in the following path within the repository: <i>app/src/main</i>

The Kotlin class files in the <i>java/com/example/employeedatabase</i> path in the <i>main</i> folder are as follows: EmployeeModel.kt, DatabaseHandler.kt, ItemAdapter.kt, MainActivity.kt
* The EmployeeModel class contains the Data Model class which provides the template for each employee in the database.
* The DatabaseHandler class is used for handling the database logic and extending the SQliteOpenHelper base class, which is used for connecting and interacting with the SQlite database used in this program.
* The ItemAdapter class creates the view holder for view items, connects the data source of the RecyclerView and handles the view logic by creating a RecyclerView Adapter.
* The MainActivity class acts as the controller for the UI. It contains all of the functions associated to each part of the UI and bridges the UI and database together.

The XML files in the <i>res/layout</i> of the <i>main</i> folder are as follows: content_main.xml, item_row.xml, dialog_update.xml
* The content_main file is used for main content of the page, so where the user can input text and add employees, as well as initializing the RecyclerView which will display all of the employees in the database.
* The item_row file is used for providing the template for each employee that will be displayed from the database. In other words, it provides the template for each item in the RecyclerView.
* The dialog_update file is used for providing the window that is shows when the user attempts to edit an employee's information. You can edit their name and email.

## Getting Started

### Prerequisites
The JavaFX SDK needed to be imported as a library in the build path.

The following run configurations needed to be set as VM arguments:
```sh
--module-path "C:\Program Files (x86)\Java\jre1.8.0_91\lib\javafx-sdk-15.0.1\lib" 
--add-modules javafx.controls,javafx.fxml
```

A MySQL JDBC driver had to be imported to provide the program with a connection to the MySQL database.

## Usage
The basic functionality of the website is as follows:

* The program is launched and the School Management System windows appears.

<img src = "screenshots/login_page.PNG">

<hr>

* The user must enter the right credentials as per the login table of the school schema and click the login button.

<img src = "screenshots/wrong_login.PNG">
<img src = "screenshots/correct_login.PNG">

<hr>

* This brings up the Admin Dashboard of the application.

<img src = "screenshots/admin_page.PNG">

<hr>

* From here the user can click the Load/Refresh Data button to bring up all of the information in the students table of the school schema.

<img src = "screenshots/loaded_page.PNG">

<hr>

* The user can add a student by entering information into the text boxes in the Add Student section of the page, then clicking the Add Student button. Load/Refresh Student will need to be clicked to refresh the table.

<img src = "screenshots/add_student1.PNG">
<img src = "screenshots/add_student2.PNG">

<hr>

* The user can delete a student by clicking on the row of the student they want to delete, then clicking the Deleted Student button. Load/Refresh Student will need to be clicked to refresh the table.

<img src = "screenshots/delete_student1.PNG">
<img src = "screenshots/delete_student2.PNG">

<hr>

* The user can clear the text boxes in the Add Student section by clicking the Clear Form button in the Add Student section.

<img src = "screenshots/clear_student_form1.PNG">
<img src = "screenshots/clear_student_form2.PNG">

<hr>

* The user can add an Admin by entering information into the text boxes in the Add Admin section of the page, then clicking the Add Admin button.

<img src = "screenshots/add_admin.PNG">

<hr>

* The user can clear the text boxes in the Add Admin section by clicking the Clear Form button in the Add Admin section.

<img src = "screenshots/clear_admin_form.PNG">

## Resources Used

This project was based off of the following YouTube tutorial: https://www.youtube.com/watch?v=h1rYlMrvNyE
* MySQL was used instead of SQLite.
* The button for deleting entries was not done in the video.
* The Add Admin button was not done in the video.