In C#, Java, or Smalltalk create a class that when subclassed allows this sample assessment code to run using only the file system for storage, no pre-built database allowed; you must use files. If you don't save to disk, you fail.
Create any classes required to compile and pass the test case; if you're in Java, adapt the below C# test case to jUnit and modify as required as it should be very similar, just try and maintain the spirit of the test and turn in both your solution and your updated test case. We're just looking for a unique sample of your work to discuss in the interview.
The Id, Save, Delete, and Find methods must be in the super class; subclasses must not implement these methods themselves. Imagine this is a persistence library you're going to use for prototyping simple apps, so you don't want to continually rewrite this code in every new class.

```
public void ProgrammerAssessment() {
var address = new Address("5455 Apache Trail", "Queen Creek", "AZ", "85243");
var person = new Person("Jane", "Smith", address);
var biz = new Business("Alliance Reservations Network", address);

      Assert.IsNull(person.Id);
      person.Save();
      Assert.IsNotNull(person.Id);

      Assert.IsNull(biz.Id);
      biz.Save();
      Assert.IsNotNull(biz.Id);

      Person savedPerson = Person.Find(person.Id);
      Assert.IsNotNull(savedPerson);
      Assert.AreSame(person.Address, address);
      Assert.AreEqual(savedPerson.Address, address);
      Assert.AreEqual(person.Id, savedPerson.Id);
      Assert.AreEqual(person.FirstName, savedPerson.FirstName);
      Assert.AreEqual(person.LastName, savedPerson.LastName);
      Assert.AreNotEqual(person, savedPerson);
      Assert.AreNotSame(person, savedPerson);
      Assert.AreNotSame(person.Address, savedPerson.Address);
      Assert.AreEqual(person.Address, savedPerson.Address);

      Business savedBusiness = Business.Find(biz.Id);
      Assert.IsNotNull(savedBusiness);
      Assert.AreSame(biz.Address, address);
      Assert.AreEqual(savedBusiness.Address, address);
      Assert.AreEqual(biz.Id, savedBusiness.Id);
      Assert.AreEqual(biz.Name, savedBusiness.Name);
      Assert.AreNotEqual(biz, savedBusiness);
      Assert.AreNotSame(biz, savedBusiness);
      Assert.AreNotSame(biz.Address, savedBusiness.Address);
      Assert.AreEqual(biz.Address, savedBusiness.Address);

      var deletedPersonId = person.Id;
      person.Delete();
      Assert.IsNull(person.Id);
      Assert.IsNull(Person.Find(deletedPersonId));

      var deletedBizId = biz.Id;
      biz.Delete();
      Assert.IsNull(biz.Id);
      Assert.IsNull(Business.Find(deletedBizId));
}
```