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
    * printPeopleList()
    * printHealthprofile(5)
    * printPeopleByWeight(90, ">")
* `DataMapper`:
  * **generatePeople()** Generates 3 random people inside a new istance of a **People** object and returns it
  * **marshall(People, String)** Marshall in XML format the People istance passed inside the indicated file
  * **marshallJSON(People, String)** Marshall in JSON format the People istance passed inside the indicated file
  * **unmarshall(String)** Unmarshall from the XML file indicated a People istance and returns it
  * **unmarshallJSON(String)** Unmarshall from the JSON file indicated a People istance and returns it
  * **main(String[])** Marshalls and Unmarshalls both from XML and from JSON, before each marshalling it generates 3 new **Person** istances inside a **People** object, at each step it cleans the variable in which data are stored:
    * Generates 3 random people and marshall them to people_new.xml
    * Cleans local variable
    * Unmarshalls the previous created people from people_new.xml
    * Generates other 3 random people and marshall them to people_new.json
    * Cleans local variable
    * Unmarshalls the previous created people from people_new.json
