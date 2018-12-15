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
![Sequence Diagram](Images/Original/Component1/Sequence.png)
