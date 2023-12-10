# SMQA
MOBILE ASSET MANAGEMENT SYSTEM
- Eclipse IDE installed
- Java Development Kit (JDK) installed
- JUnit 5 Library for test coverage

Setup and Run
Unzip the Project:
Unzip the file `mobileclientassetmanagement.zip`

Open Eclipse:
Launch Eclipse.
Open File -> Open Projects from File System
In the import source, click on the directory where the unzipped folder is present it should be ending like this (`../mobileclientassetmanagement`).
Click Finish.

To Run the Application:
In the project structure, locate `src` folder, and within it, find `defaultpackage` and `AppExecute.java`.
Right-click on `AppExecute.java` and select "Run as Java Application".

It will asks for Name 
These are the credentials.
Credentials:

    - John
       - Email: john@email.com
       - Role: Admin

    - Jane Smith
       - Email: jane.smith@email.com
       - Role: Asset User

     - Bob Johnson
       - Email: bob.johnson@email.com
       - Role: Asset Manager

      - Alice Williams
       - Email: alice.williams@email.com
       - Role: Technician

Note: Based on the user role, specific modules will be shown.


To Run Test coverage in Eclipse
Initial Configuration:
Right-click on the project (`mobileclientassetmanagement`)
Go to Build Path -> Configure Build Path
Locate libraries, click on the class path, and select "Add Library".
Choose JUnit, click Next, set JUnit Library Version as 5, and click Finish.
Click Apply and Close.

Run:
Right-click on the `test` folder.
Choose Coverage As -> JUnit Test.


To Run Test in Terminal
Open the terminal.
Navigate to the path where `mobileclientassetmanagement` is present.
Execute `ls` to see the available scripts.

To run Whitebox Test, execute:
chmod +x run_whitebox_test.sh
and after that execute ./run_whitebox_test.sh

To run Blackbox Test, execute:
chmod +x run_blackbox_test.sh
and after that execute ./run_blackbox_test.sh

PS: Ensure that Eclipse is configured with the necessary build path and libraries for JUnit 5.
