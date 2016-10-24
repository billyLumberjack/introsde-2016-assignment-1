# introsde-2016-assignment-1
## Samuele Malavasi | mat.182551
The project is about handling .xml and .json files.
I used Ivy to manage dependencies and ant to automate the ivy installation, dependencies retrieving, java compiling and execution.

### About the code
I wrote 3 main classes
* `HealthProfileWriter` Implements **initializeDB** method to create 20 random istances of the class **Person** which are used to populate an XML file used as database
* `HealthProfileReader`Implements methods to query the database and the **main** one to run a demo
  * getHeight(int)
  * getWeight(int)
  * main(String[])
  * printHealthprofile(int)
  * printPeopleByWeight(double, String)
  * printPeopleList()
* `DataMapper`:
