# COMP6901 - Project 
Team Members:
* Lurlynn Maharaj
* Nikita Louison
* Richard Samuel
* Amar Andrew Singh

## Component One - ViewIndividualStudentAnalytics class
### Issues
#### Functionality
Given the word “analytics”, this java class is based on the computational analysis of data and statistics related to students such as attendance and punctuality. To be more precise, the class retrieves the stored data in the Firebase Database including as student name, student ID, lecture name, starting time of lecture, recorded attendance date of student and the time of student arrival. 
This class displays the data in the form of pie-charts for a selected student.
The class uses the following to calculate punctuality and attendance of each student.
* The student ID
* Course code
* The retrieved number of minutes late to the lecture
* Marking of attendance
To plot the charts, a count is kept for students who arrive early and late sessions as well as whether or not sessions were attended. Once the values are allocated, the two charts are updated with the respective data. In the ViewIndividualStudentAnalytics class both methods (drawAttendancePieChart and drawPunctualityPieChart) are used to analyze data.
#### Strengths
1. All code pertaining to Individual Student Analytics are in one class and the chart drawing are in their relevant methods for analysis. 
2. The relevant calculation methods for student analysis such as attendance and punctuality are also in this one class.
#### Weaknesses
1. The class has too many methods and as such readability and understandability is challenging and there is low cohesion.
2. The class generates a great variety of actions, it is broad and unfocused. 
   1. For example: gathering data, calculating, drawing charts and updating charts.
3. The draw pie chart methods are not “open” for use in the other classes. If new charts types have to be added or re-used they have to be re-written or “duplicated”. 
   1. For instance: if piecharts were needed for another analytics class, it would have to be redrawn for that specific class.
4. If any other chart type (e.g. Line Chart) became an additional requirement, then it would increase the amount of methods in this one class.

#### Why SRP & OCP 
##### SRP is being broken.
1. Single Responsibility Principle: Every software module/ class, should have only one reason to change or responsibility.
2. If a class has more than one responsibility, then the responsibilities become coupled. In this case the calculations are tightly coupled to creating/updating charts.
3. Changes to one responsibility may impair or inhibit the class’ ability to meet the others.
4. This kind of coupling leads to fragile designs that break in unexpected ways when changed.  It may also lead to “code-rot”.

Please note the UML depicting the original Class UML layout of this class as well as the sequence UML diagram depicting the calculation methods. The UML class diagram depicts the abundance of methods in the class and the sequence diagrams depicts the coupling of the methods.
##### OCP is being broken.
1. Open Close Principle: Software entities should be open for extension but closed for modifications.
2. Developers should design modules that never change. When requirements change or in this case any addition charts are required, you extend the behavior of such modules by adding new code, not by changing old code that already works. 
3. This means that the behavior of the module can be extended. We can then make the module behave in new and different ways as the requirements of the application change, or to meet the needs of new applications.

Please note the UML depicting the sequence UML diagram depicting the drawing piechart methods located in this class.

#### UML Diagrams
##### Class Diagram
![Class Diagram](Images/Original/Component1/Class.jpg)

##### Sequence Diagram
![Sequence Diagram](Images/Original/Component1/Sequence.PNG)

### Proposed Solution
#### Design Patterns
##### Creational Pattern: Factory Subsystem: & Structural Pattern: Facade Subsystem
ViewIndividualStudentAnalytics had tight coupling because there is the responsibility of drawing the pie charts, calculating the attendance and punctuality of students and populating the charts with the data. The previous design violated the SRP and OCP. 

The ViewIndividualStudentAnalytics class had three responsibilities. 
1. The first responsibility is to provide drawing of the pie chart. 
2. The second responsibility is to calculate attendance and punctuality 
3. The third is to update the pie charts. 

If we add a new type of graph or new calculation then we will have to retest every method in the class to ensure that the other charts are working i.e. rebuild, retest, and redeploy ViewIndividualStudentAnalytics. 

A better design is to separate the responsibilities. As mentioned before, a façade and factory design are used. 

##### Factory Pattern
A creational pattern that creates objects without exposing the instantiation logic to the client.
Its intent is to define an interface for creating an object, and lets the subclasses decide which class to instantiate. Factory method lets a class defer instantiation to subclasses.

Implemented Factory Subsystem:
* Factory class called:- Statistics
  - This class is the Factory to generate object of concrete class based on given information. The concrete/subclasses classes here are attendance and punctuality.
* Interface class:- Calculation
  - This class is the interface between Statistics and the concrete/subclasses Attendance and Punctuality.
* Utility Classes:- Attendance & Punctuality
  - These classes are implementing the necessary calculations pertaining to its corresponding names. They implement the Calculations interface.

##### Facade Pattern
Is a structural pattern that has single class to represent an entire subsystem.
Its intent is to provide a unified interface to a set of interfaces in a subsystem. The facade is the higher level interface that makes the subsystem easier to use and reference. In essence it wraps a complicated subsystem with a simpler interface.
The facade object is to be a facilitator or advocate or facilitator. It promotes decoupling the subsystem from its potential many clients. (NB: ViewCourseAnalystics’ s Line chart)

Implemented Facade Subsystem:
* Facade class called:- ChartRendering
  - This class calls the subclass Pie to draw a Pie Chart. Statistics are then calculated and fed into the charts. Thereafter, labels and descriptions are added pertaining to the type of data the chart is illustrating. 
* Interface class:- Chart
  - This class is the interface between the ChartRendering and subclass Pie.
* Utility Classes:- Pie
  - This class implements the Chart interface. It draws a Pie chart and returns and Pie Chart object. 

#### The advantages of the new design
* More cohesion
* Easier to read code
* Removes tight coupling
* Charts open for extension for instance to the line charts in the View Course Analytics class
* Able to add new chart types required by clients for instance bar charts
* New calculation subsystem of “utility” classes can be added for further statistical analysis of courses, lectures and students

#### How it facilitates any of SOLID principles discussed
1. Follows these principles:
   * SRP & OCP
2. Creational Pattern: Factory:
   * To solve the SRP issue, the factory includes an Interface Calculations and an Attendance and Punctuality class which implements the interface. The factory solution now utilizes a class called Statistics which uses the Attendance and Punctuality class to execute calculations for early and late students as well as total attendances. With this solution, the responsibility of the calculations belong to the Statistics class and if another calculation was to be added it can now be created as a new class which implements the Calculations interface. 
![Factory Class Diagram](Images/Proposed/Component1/Class2.jpg)
3. Structural  Pattern: Facade
   * To solve the OCP issue, a façade solution was used. An interface called Chart is created along with a Pie and Line class that implement the interface. The façade solution uses a class called ChartRendering which uses the Pie class to create pie charts in methods. These methods create pie charts for attendance and punctuality. If another chart was to be used to show another type of data, for example a line chart for the ViewCourseAnalytics class, it could be added to the this class. 
![Facade Class Diagram](Images/Proposed/Component1/Class1.jpg)

#### UML Diagram
##### Class Diagram
![Factory Class Diagram](Images/Proposed/Component1/Class3.jpg)
##### Sequence Diagram
![Factory Class Diagram](Images/Proposed/Component1/Sequence1.png)

## Component Two - ViewCourseActivity Class
### Issues
#### Functionality
This activity display a list of the courses using the FirebaseHelper class which interacts with the database. Firebase is a NoSQL Database and the courses are stored in a tree format under each lecturer’s ID. 
#### Strengths
1. ViewcourseActivity has the functions needed to provide the required course ID and course name, which is retreives from the Firebase Database.
3. Firebase provides a visible “identifiable” reference for each method that needs it in the mentioned class.  
#### Weaknesses
1. Firebase and a lot of its dependencies are required for the class to run..
2. A new ValueEventListener object is required to get the Firebase Data.
3. FirebaseHelper deal with Firebase commands making it difficult to untangle.
4. Firebase is tightly coupled with this class.
#### SRP
1. Since this class is so tied together it makes it difficult to make any major changes such as a new Database.
2. FirebaseHelper should only deal with firebase commands, however ViewCourseActivity has firebase depencies
#### UML Diagrams
##### Class Diagram
![Class Diagram](Images/Original/Component2/Class.png)
##### Sequence  Diagram
![Sequence Diagram](Images/Original/Component2/Sequence.png)

### Proposed Solution
The primary issue of the original implementation is that Firebase is in all the classes that are linked to ViewCourseActivity. The proposed method will take firebase completely out of ViewCourseActivity and house it in a separate entity. While FirebaseHelper tried to do that it still needed to use a ValueEventListener which is part of Firebase. To separate that out ViewCourseActivity will now observe a ViewModel Class that will actually called the Firebase commands. This way ViewCourseActivity does not end up using any Firebase references and will only receive a list of Courses.

#### Design Patterns
##### Observer
Observer pattern is used due to how Firebase retrieves Data. Firebase requires a ValueEventListener to get data in real time and update any necessary components. The ViewCourseActivity will observe the ViewCourse Class for any changes and update the course list with any changes.
##### MVC Pattern 
A loose implementation of the Model View Controller Pattern was used:
1. FirebaseLiveData (Model) - This will contain the database interaction with Firebase and parse the data in a way that other classes can use it without having any references to Firebase.
2. ViewCourseActivity (View) - This will just be displaying the information from the database by observing the ViewClass (Controller).
3. ViewCourse (Controller) - This will handle the interaction between the FirebaseLiveData and ViewCourseActivity. It instantiates a FirebaseLiveData and returns the information in the form of a list of Courses. 

#### The advantages of the new design
1. MVC naturally tends to end to Abstract code into reusable modules and with the design principles chosen it leads to each component being independent and have a unique function.
2. Firebase is not referenced all over the classes
3. The Database calls and commands only exist in one class and the other classes that interact do not require it.
4. If a change to the database type is required a new database class can be written and only the controller will need updating.
5. Utilises the same underlying classes that were developed by the original developers such as Courses to return the data in a format the application understands.

#### How it facilitates any of SOLID principles discussed
1. Solid Responsibility Principle - Each component has a unique function:
2. FirebaseLiveData - Returns firebase information in useable format.
3. ViewCourse - Gets the information from FirebaseLiveData and sends it to ViewCourseActivitiy.
4. ViewCourseActivity - Only responsible for displaying of Data and observing ViewCourse.

#### UML Diagrams
##### Class Diagram
![Class Diagram](Images/Proposed/Component2/Class.png)
##### Sequence  Diagram
![Sequence Diagram](Images/Proposed/Component2/Sequence.png

## Component Three - ViewCourseAnalytics

