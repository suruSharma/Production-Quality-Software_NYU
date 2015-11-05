package edu.nyu.cs.pqs.ps1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.utils.AddressBookUtils.SearchBy;

/**
 * @author Suruchi
 * Unit test class for AddressBook. 
 * Code coverage : 98.3%
 */

//Unit tests for the AddressBookTest class are not written as that class is not a part of the actual API.
public class AddressBookUnitTest {

  private AddressBook addressBook;
  private AddressEntry addressEntryTest;
  private AddressEntry test1;
  private AddressEntry test2;
  private AddressEntry test3;
  private AddressEntry test4;
  private AddressEntry test5;

  private String filePath = System.getProperty("user.dir") + "\\AddressBook.txt";

  @Before
  public void setUp() {
    addressBook = AddressBook.create("UnitTest");
    addressEntryTest = new AddressEntry.Builder("TestName").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress(
        "test@testing.com").note("TestNote").build();

    test1 = new AddressEntry.Builder("TestName").postalAddress(
        "Apt Test1, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress(
        "test@testing.com").note("TestNote").build();
    test2 = new AddressEntry.Builder("TestName").postalAddress(
        "Apt Test2, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress(
        "test@testing.com").note("TestNote").build();
    test3 = new AddressEntry.Builder("TestName").postalAddress(
        "Apt Test3, Testing Ave, Test City, 07306").phoneNumber("3333333333").emailAddress(
        "test@testing.com").note("TestNote3").build();
    test4 = new AddressEntry.Builder("TestName4").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("3333333333").emailAddress(
        "test@testing.com").note("TestNote4").build();
    test5 = new AddressEntry.Builder("TestName5").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("3333333333").emailAddress(
        "test5@testing.com").note("TestNote3").build();

    addressBook.addEntry(test1);
    addressBook.addEntry(test2);
    addressBook.addEntry(test3);
    addressBook.addEntry(test4);
    addressBook.addEntry(test5);
  
    File file = new File(filePath);
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  public void testCreateBook() {
    String testName = "TestAddressBook";
    AddressBook addressBookLocal = AddressBook.create("TestAddressBook");
    assertTrue("Incorrect address book is created", testName.equals(addressBookLocal
        .getAddressBookName()));
  }

  @Test
  public void testAddEntry() {
    addressBook.addEntry(addressEntryTest);
    assertTrue("AddressEntry object was not added successfully", addressBook.getEntry().contains(addressEntryTest));
  }
  
  @Test
  public void testAddDuplicateEntry(){
    assertFalse("Duplicate entry should not be added", addressBook.addEntry(test1));
  }
  
  //This unit test fails because the code allows a null entry to be added.
  @Test
  public void testAddNullEntry(){
    assertFalse("Null entry should not be added", addressBook.addEntry(null));
  }
  
  @Test
  public void testRemoveEntry() {
    assertTrue("The address enrty was not removed", addressBook.remove(test1));
    assertFalse("Null cannot be removed", addressBook.remove(null));
  }

  
  @Test
  public void testSearchByName() {
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.NAME, "TestName");
    assertTrue("Incorrect number of entries retreived", entries.size() == 3);
  }
  
  @Test
  public void testSearchByEmptyName(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.NAME, "");
    assertTrue("No entries should be returned", entries.size() == 0);
  }
  
  @Test
  public void testSearchByNullName(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.NAME, null);
    assertTrue("No entries should be returned", entries.size() == 0);
  }

  @Test
  public void testSearchByNote() {
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.NOTE, "TestNote");
    assertTrue("Incorrect number of entries retreived", entries.size() == 2);
  }
  
  @Test
  public void testSearchByEmptyNote(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.NOTE, "");
    assertTrue("No entries should be returned", entries.size() == 0);
  }
  
  @Test
  public void testSearchbyNullNote(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.NOTE, null);
    assertTrue("No entries should be returned", entries.size() == 0);
  }

  
  @Test
  public void testSearchByEmail() {
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.EMAIL, "test@testing.com");
    assertTrue("Incorrect number of entries retreived", entries.size() == 4);
  }

  @Test
  public void testSearchByEmptyEmail(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.EMAIL, "");
    assertTrue("No entries should be returned", entries.size() == 0);
  }
  
  @Test
  public void testSearchByNullEmail(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.EMAIL, null);
    assertTrue("No entries should be returned", entries.size() == 0);
  }
  
  @Test
  public void testSearchByPhoneNumber() {
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.PHONE_NUMBER, "3333333333");
    assertTrue("Incorrect number of entries retreived", entries.size() == 3);
  }

  @Test
  public void testSearchByEmptyPhoneNumber(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.PHONE_NUMBER, "");
    assertTrue("No entries should be returned", entries.size() == 0);
  }
  
  @Test
  public void testSearchByNullPhoneNumber(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.PHONE_NUMBER, null);
    assertTrue("No entries should be returned", entries.size() == 0);
  }
  
  @Test
  public void testSearchByPostalAddress() {
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.POSTAL_ADDRESS,
        "Apt Test, Testing Ave, Test City, 12345");
    assertTrue("Incorrect number of entries retreived", entries.size() == 2);
  }
  
  @Test
  public void testSearchByEmptyPostalAddress(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.POSTAL_ADDRESS,"");
    assertTrue("Incorrect number of entries retreived", entries.size() == 0);
  }
  
  @Test
  public void testSearchByNullPostalAddress(){
    List<AddressEntry> entries = addressBook.searchBy(SearchBy.POSTAL_ADDRESS,null);
    assertTrue("Incorrect number of entries retreived", entries.size() == 0);    
  }

  @Test
  public void testToString() {
    Set<AddressEntry> entries = new HashSet<AddressEntry>();
    AddressEntry localAddressEntry = new AddressEntry.Builder("TestName").postalAddress("Address").phoneNumber("0123456789")
        .emailAddress("test1@testing.com").note("TestNote").build();
    entries.add(localAddressEntry);
    addressBook.setEntry(entries);
    String result = "UnitTest[AddressEntry [name=TestName, postalAddress=Address, "
        + "phoneNumber=0123456789, emailAddress=test1@testing.com, note=TestNote]\n]";
    assertTrue("The strings do not match", result.equals(addressBook.toString()));
  }

  @Test
  public void testSetAddressBookName() {
    AddressBook testObj = AddressBook.create("");
    String testName = "Test Name";
    testObj.setAddressBookName(testName);
    assertTrue("Incorrect name is returned", testName.equals(testObj.getAddressBookName()));
  }

  @Test
  public void testSetEntry() {
    Set<AddressEntry> entries = new HashSet<AddressEntry>();
    AddressEntry localAddressEntry = new AddressEntry.Builder("TestName").postalAddress("Address").phoneNumber("0123456789")
        .emailAddress("test1@testing.com").note("TestNote").build();
    entries.add(localAddressEntry);
    addressBook.setEntry(entries);
    assertTrue("Set of entries is not set successfully", addressBook.getEntry().size() == 1);
  }

  @Test
  public void testSaveAndLoadFromFile() {
    try {
      addressBook.saveToFile(filePath, addressBook);
      AddressBook objectFromFile = addressBook.loadFromFile(filePath);
      assertTrue("Incorrect addressbook object is retrieved", addressBook.getAddressBookName()
          .equals(objectFromFile.getAddressBookName()));
      assertTrue("Incorrect number of address entries found",
          addressBook.getEntry().size() == objectFromFile.getEntry().size());
    } catch (Exception e) {
      fail("Exception found in loading/saving data from/to file");
    }
  }

  //This test case fails because of empty addressbook objects are not written to the file
  @Test
  public void testEmptyAddressBookOperations() {
    AddressBook emptyAddressBook = AddressBook.create("Empty book");
    assertFalse("Empty address book could not be created", emptyAddressBook.equals(null));
    emptyAddressBook.saveToFile(filePath, emptyAddressBook);

    File savedFile = new File(filePath);
    assertTrue("File was not created", savedFile.exists());
  }
}
