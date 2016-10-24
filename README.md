# introsde-2016-assignment-1
## Samuele Malavasi | mat.182551
The project is about handling .xml and .json files.
I used Ivy to manage dependencies and ant to automate the ivy installation, dependencies retrieving, java compiling and execution.

### About the code
I wrote 3 main classes
* `HealthProfileWriter` Implements **initializeDB** method to create 20 random istances of the class **Person** which are used to populate an XML file used as database
* `HealthProfileReader` Implements methods to query the database and the **main** one to run a demo
  * **getHeight(int)** Returns the height of the person whose id equals to the passed value
  * **getWeight(int)** Returns the weight of the person whose id equals to the passed value
  * **printHealthprofile(int)** Prints the **healthprofile** of the person entry of the given id
  * **printPeopleByWeight(double, String)** Prints the list of the people inside the database **people.xml** who satisfy a certain criterion expressed by a value and an operator
  * **printPeopleList()** Prints the list of the people inside the database **people.xml**
  * **main** Calls in order:
    * printPeopleList();
    * printHealthprofile(5);
    * printPeopleByWeight(90, ">");     
* `DataMapper`:
* `main`: contains the main classes that are targets in the `build.xml` file and that accomplish the assignment goals. The organization was thought in a way useful to separate similar tasks and technologies:
  * `ReadPeople.java`: used for the first 3 tasks (XML and XPATH). Depending on the executed target it is possible to:
    * **getAllPeople**: prints all the people with details;
