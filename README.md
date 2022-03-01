# OOAD Project 3 Part 2
 OOAD Project 3 Part 2

This is an assignment for our Object Oriented Analysis and Design course in which we will be applying object oriented design principles to create a simulation of a music shop. When ran, the user will be able to choose how many days the simulation will be ran for. As the simulation is running, announcements of all actions that occurs during each day will be printed to the terminal. The output.txt file in this repository contains the terminal output from running the simulation for 30 days.

**How to Run:**
Run in IntelliJ. Run the Main.java in src/abhi/ooad folder.

Creators: 
--------
Abhinav Gupta, Alexa Rinard, Varun Manjunath

Language: 
--------
We are using Java version 17.0.2 for this project

Class Heirarchy:
------------

<a href = "https://lucid.app/lucidchart/41af58ed-28cf-4924-bad2-b28631ca1c5f/edit?invitationId=inv_d94f10f2-e7f7-496b-8df3-ae2a3f32930c"> Link to UML Diagram </a>

Assumptions:
------------

<ul>
  <li>Every 7th day is considered to be Sunday</li>
  <li>Purchase price for all items is a random number between 1-50</li>
  <li>When placeAnOrder occurs, items purchased are always in New condition</li>
</ul>

Implementation Comments:
------------------------

<ul>
  <li>Condition is represented as a number from 1-5</li>
  <li>Customers buy and sell items in a random order each new day</li>
</ul>
Updated Uml diagram changes from the original diagram:
<ul>
  <li>Made most of the variables,arrays and methods as private in the Store, Item Class and all of the subclasses of Item Class</li>
  <li>Moved the functions in the Staff class to the Store Class.Added some new methods to the Staff Class</li>
  <li>New methods added to the Staff class are getStaffMembers() and getDamageFrequency(String  staffMember)</li>
  <li>Used HashMap for inventoryOnHold,soldItems,orderItems</li>
  <li>Added the decorator interface stringdecorator that is an interface for cables,gigs,strings and practiceamps.This is the decorator interface used to implemnt decorators</li>
 <li>Used a Tune interface for the strategy pattern</li>
</ul>
