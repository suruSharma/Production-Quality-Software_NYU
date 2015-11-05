package edu.nyu.cs.pqs.ps1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Suruchi
 * Unit test class for AddressEntry.
 * Code coverage : 100%
 */
public class AddressEntryUnitTest { 
  private AddressEntry addressEntry;

  @Before
  public void setUp() {
    addressEntry = new AddressEntry.Builder("TestName").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress(
        "test@testing.com").note("TestNote").build();
  }

  @Test
  public void testEqualsPositive() {
    AddressEntry entryObj = new AddressEntry.Builder("TestName").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress(
        "test@testing.com").note("TestNote").build();

    assertTrue("Address entry objects are unequal", addressEntry.equals(entryObj));
  }

  @Test
  public void testEqualsNegative() {
    AddressEntry entryObj = new AddressEntry.Builder("SomeName").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress(
        "test@testing.com").note("TestNote").build();
    assertFalse("Address entry objects should not be equal", addressEntry.equals(entryObj));
  }

  @Test
  public void testEqualsWithNull() {
    assertFalse("False should be returned", addressEntry.equals(null));
  }

  @Test
  public void testEqualsWithNotAddressEntryObject() {
    assertFalse("False should be returned", addressEntry.equals(new Integer(1)));
  }

  @Test
  public void testHashCode() {
    int hashcode = addressEntry.hashCode();
    AddressEntry entryObj = new AddressEntry.Builder("TestName").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress(
        "test@testing.com").note("TestNote").build();
    assertTrue("Hashcodes should be the same", entryObj.hashCode() == hashcode);
  }

  @Test
  public void testSetEmailAddress() {
    String testEmail = "something@bc.xom";
    AddressEntry entryObj = new AddressEntry.Builder("TestName").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress("").note(
        "TestNote").build();
    entryObj.setEmailAddress(testEmail);
    assertTrue("Incorrect email address", entryObj.getEmailAddress().equals(testEmail));
  }

  @Test
  public void testSetName() {
    String tetsName = "SomeName";
    AddressEntry entryObj = new AddressEntry.Builder("").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress("").note(
        "TestNote").build();
    entryObj.setName(tetsName);
    assertTrue("Incorrect name", entryObj.getName().equals(tetsName));
  }

  @Test
  public void testSetNote() {
    String testNote = "SomeNote";
    AddressEntry entryObj = new AddressEntry.Builder("").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress("").note(
        "").build();
    entryObj.setNote(testNote);
    assertTrue("Incorrect note", entryObj.getNote().equals(testNote));
  }

  @Test
  public void testSetPhoneNumber() {
    String testNumber = "12345678";
    AddressEntry entryObj = new AddressEntry.Builder("").postalAddress(
        "Apt Test, Testing Ave, Test City, 12345").phoneNumber("0123456789").emailAddress("").note(
        "").build();
    entryObj.setPhoneNumber(testNumber);
    assertTrue("Incorrect phone number", entryObj.getPhoneNumber().equals(testNumber));
  }

  @Test
  public void testsetPostalAddress() {
    String testAddress = "Apt Test, Testing Ave, Test City, 12345";
    AddressEntry entryObj = new AddressEntry.Builder("").postalAddress("")
        .phoneNumber("0123456789").emailAddress("").note("").build();
    entryObj.setPostalAddress(testAddress);
    assertTrue("Incorrect note", entryObj.getPostalAddress().equals(testAddress));
  }

  @Test
  public void testToString() {
    String expectedResult = "AddressEntry [name=TestName, postalAddress=Apt Test, Testing Ave, "
        + "Test City, 12345, phoneNumber=0123456789, emailAddress=test@testing.com, note=TestNote]";
    assertTrue("Incorrect string representation", expectedResult.equals(addressEntry.toString()
        .trim()));
  }

}
