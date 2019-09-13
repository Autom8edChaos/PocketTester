# Test assignment

We are looking for Automation Engineers that have the mindset "only the sky is the limit" and "automation doesn't stop at testing, it's just a beginning!" ;)

The purpose of this test assignment is to assess the applicant's automation skills, allowing him/her to show the best they can do and how fast they can learn.
It is an open assignment. There is no the right answer and there is no end goal other than proving yourself. Surprise us!

Make sure that you give detailed comments or descriptions of your tests.
When the assignment is complete, please push your solution to Github(Gitlab) and send us the link to hr.grid.adi@nl.abnamro.com.
If you have any questions, please contact us at hr.grid.adi@nl.abnamro.com.

Good luck.

PS. We don't expect you to spend weeks (and sleepless nights) on doing it. Lets see how far you can get in 6-10 hours. We want to see how you approach and solve problems.

PSPS. Please use mobile native tools. (Tests written on Java are accepted too)

# Start

After opening the App that comes with this assignment, I realized that it looked quite empty and it was lacking testable functionality for a proper showcase. 
And of course, there is not much fun in testing an application that does almost nothing, so we need a proper application to test on. After some thinking (doing a run outside 
in the nature can sometimes be more of value than a day in a meeting room), I decided that I wanted to make a Note Taking app that could be used during testing. 

# PocketTester

You are sitting in the train on your daily commute. While dozing off a little, you suddenly open your eyes. A great test idea just popped up in your head: "If we can mock the HTTP calls, we can test every state in
the App without having to setup testdata on the server!". You want to note that idea down and directly share it with you colleagues, but how? Well, there is an app for that: PocketTester!

PocketTester, now you can write down your bugs, checks or test ideas; anytime, anywhere.

## Implementation

PocketTester is loosely based on the tutorial app "Course NoteKeeper" of [Jim Wilson](https://jwhh.com/). I implemented the adaptations myself for this assignment to have a fun little toy application to fiddle around with. The app contains some basic functionality, but because it is nowhere near finished it will limit the scope for test automation and mimics an application in early development.  

Because the app is partially build on existing code, there are already some components that are not used (CourseModules), that are a bit clumsy (ItemTypeInfo) or that apply a pattern I would never implement that way (DataManager). That is great too, because it creates challenges you would encounter as a test automation engineer in a normal days work.

# Installation

## Prerequisites, tools and SDKs

- Android Studio
- Android SDK API level 28
- Emulated device, tested on Pixel 2 API 28

## Quick Start

To startup the application:
- Wait until Gradle is ready with retrieving dependencies and building the app
- From the top bar, select App
- From the top bar, select a valid emulation device like the Pixel 2 API 28
- Click the green Play button

To run the unit tests:
- View the application in "Android View"
- Open App > Java and try to see the package `com.abnamro.apps.pockettester (test)`
- Right click on it and click the green Play button next to "Run tests in 'pockettester'"

To run all instrumented tests (integration and UI):
- From the top bar, select a valid emulation device like the Pixel 2 API 28
- View the application in "Android View"
- Open App > Java and try to see the package `com.abnamro.apps.pockettester (androidTest)`
- Right click on it and click the green Play button next to "Run 'All Instrumented tests'"
 
# Showcases

## 1. Development skills

In my opinion, a test automation engineer should not be afraid to get his hands dirty on code. Therefor I wanted to design and implement this application myself to show that I am proficient in the following topics:
- Reading and understanding existing code
- Writing code
- Understand IDE's like Android Studio and IntelliJ
- Understand build tools and dependency managers like Gradle and Maven
- Understand how to run and debug production code
- Can find solutions for challenges I am facing
- Usage of source control and version management systems like Git, Github and SourceTree 
- Understand how commits, pushes, pulls and branching works

## 2. Unit testing

While unit tests are the domain of the software developer, test automation engineers should really know how to write good unit tests so they can train and assist developers,
write additional tests if they are lacking and to understand which parts of the application is not covered. A test automation engineer should be proficient in the use
of code coverage tooling to get an idea of the parts in the application that are covered well and parts that are potentially vulnerable. 

For this assignment I wrote two unit test classes, one in Java and one in Kotlin that shows:
- Test setup and teardown
- How to test objects in isolation
- That unit tests should be narrowly scoped 
- How to apply proper test naming
- Proper use of the JUnit assertion library

Unit tests are the bottom of the test automation pyramid. There should be a lot of them and comply to the FIRST principles of Unit testing (Fast, Isolated, Repeatable, Self-Validating, Thorough)

## 3. Integration testing

Integration tests are tests where individual units are combined and tested as a group. Components that are not under test should be mocked so that they are
not causing flakiness by interference with the components under test. In my opinion, integration tests are one of the most difficult test types. While you need a good
amount of them, writing good integration tests is very hard: 
- It is a team or even inter-team activity (who is responsible?) 
- When creating integration tests, you need to know the SUT very well
- The scope of an integration test is often too broad or too narrow
- For mocking multiple application layers, you need to have a good architecture that every developer understand and applies

For this assignment, I wrote an integration test on the Parcel object. It shows:
- The mocking of an external dependency
- How to setup and use a mocked object
- The verification of results that comes back from a mocked object

## 4. Applying SOLID Principles and Testing with Mocks

The Subscription Policy showcases why we want to use SOLID principles. It is a class that uses dependency injection to acquire inversion of control. 
This is very valuable for testing, because now, the dependencies can be mocked.

Why do you want that?
For example: We have no control over the DataManager and the amount of notes that are in there. The DataManager can be an interface to a database, 
a cloud storage or maybe a file. But for your testcases, you always want to get the same amount of notes to validate the policy. Therefore we are
creating a mock that will always return the same amount of notes.
  
Mocking works also greate for the following case: the GeneralSettings object is pending functionality. There exists no concrete implementation.
Because the dependencies are injected by their interface, they can be mocked and are therefore testable.

For this showcase, I wrote a new SubscriptionPolicy class, two interfaces and the unit tests in SubscriptionPolicyTest. It shows:
- Understanding of dependency injection and inversion of control 
- The application of interfaces
- The use of Mocks in your tests
- Creating independent, fast tests with always the same result
- The application of the Mockito library

## 5. The Icing on the Cake: Creating UI Tests
This showcase shows the application of UI tests on PocketTester. It is the last showcase for a reason: It is recommended to implement the UI tests after the unit tests and integration tests are build for a feature. Now the team does have a better understanding of the application and all issues that could be found by the lower level tests are ironed out. The scope of the UI test is more clear and we can re-use code that was used for the other tests.
However, you'll see that the tests in the UI tests are not completely UI driven. The first test, creating a new note, will enter a new note through the UI, but the test will check the existence of the note in the DataManager.
The second test will first fetch the data from the DataManager, then click on the same test in the overview and compare the details to see if the detail screen is correctly implemented.
By keeping the UI actions minimized, we get maintainable, stable and relative fast tests.

In the NoteUITest shows:
- The application of the AAA (Arrange, Act, Assert) pattern
- The usage of Espresso patterns to interact with objects
- The usage of Matchers to find the right type of objects and data
- Tests that cleanup after themselves
- The combined use of UI actions and non-UI actions for stable tests


