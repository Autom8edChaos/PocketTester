# Test assignment

*We are looking for Automation Engineers that have the mindset "only the sky is the limit" and "automation doesn't stop at testing, it's just a beginning!" ;)*

*The purpose of this test assignment is to assess the applicant's automation skills, allowing him/her to show the best they can do and how fast they can learn.
It is an open assignment. There is no the right answer and there is no end goal other than proving yourself. Surprise us!*

*Make sure that you give detailed comments or descriptions of your tests.
When the assignment is complete, please push your solution to Github(Gitlab) and send us the link to hr.grid.adi@nl.abnamro.com.
If you have any questions, please contact us at hr.grid.adi@nl.abnamro.com.*

*Good luck.*

*PS. We don't expect you to spend weeks (and sleepless nights) on doing it. Lets see how far you can get in 6-10 hours. We want to see how you approach and solve problems.*

*PSPS. Please use mobile native tools. (Tests written on Java are accepted too)*

# Introduction

Because I am doing my main development on a Windows machine (my MBP is from 2010 and starts to be a bit old for development), I choose to do the Android assignment.  
After opening the App that comes with this assignment, I realized that it looked quite empty and lacking testable functionality for a proper showcase. And of course, there is not much fun in testing an application that does almost nothing, so we need to change that a little bit. 

![Notes Overview Screen](/images/reference_android.png)  
*The somewhat empty App that came with the assignment*

After some thinking, I decided that I wanted to make a Note Taking app that could be used during testing. And of course that App, PocketTester I'd like to call it, needs to be tested.  
In this assignment, I will take you through the different layers of test automation: Unit tests, integration tests and -everyones favorite- automated UI tests. However, I strongly believe that an adept test automation engineer is able to do more then only test automation, so there will also be passing some development stuff, the appliance of design patterns and software principles. I will provide links to the applicable sections in the code, but do not hesitate to clone the project yourself and run it on Android Studio. The installation instructions can be found below. 

Due to the time constraints I will only focus on automated (functional) tests. There will be things that I really like but have to skip: static analysis tools, build pipelines, multi device testing and test approaches that encourage collaboration like Behaviour Driven Development.

# PocketTester

You are sitting in the train on your daily commute. While dozing off a little, you suddenly open your eyes. A great test idea just popped up in your head: "If we can mock the HTTP calls, we can test every state in the App without having to setup testdata on the server!". You want to note that idea down and directly share it with you colleagues, but how? Well, there is an app for that: PocketTester!

![Notes Overview Screen](/images/PockerTester_overview.png)
![Notes Entry Screen](/images/PocketTester_new_note.png)

**_PocketTester, now you can write down your bugs, checks or test ideas; anytime, anywhere._**

## Implementation

PocketTester is loosely based on the tutorial app "Course NoteKeeper" of [Jim Wilson](https://jwhh.com/). I implemented the adaptations myself for this assignment to have a fun little toy application to tinker around with. The app contains basic functionality, but because it is nowhere near finished it will limit the scope for test automation and mimics an application in early development.  

Because the app is partially build on existing code, there are already some components that are not used ([ModulesInfo](/app/src/main/java/com/abnamro/apps/pockettester/ModuleInfo.java)), that are a bit clumsy ([ItemTypeInfo](/app/src/main/java/com/abnamro/apps/pockettester/ItemTypeInfo.java)) or that apply a pattern I would never implement that way ([DataManager](/app/src/main/java/com/abnamro/apps/pockettester/DataManager.java)). That is great too, because it creates challenges you would encounter as a test automation engineer in a normal days work.

# Installation

## Prerequisites, tools and SDKs

- Android Studio
- Android SDK API level 28
- Emulated device, tested on Pixel 2 API 28

## Quick Start

1. Clone the project or download the zip.
2. Open Android Studio and open the project

### To startup the application:
- Wait until Gradle is ready with retrieving dependencies and building the app
- From the top bar, select App
- From the top bar, select a valid emulation device like the Pixel 2 API 28
- Click the green Play button

![Run Unit Tests](/images/run_App.png)

### To run the unit tests:
- View the application in "Android View"
- Open App > Java and try to see the package `com.abnamro.apps.pockettester (test)`
- Right click on it and click the green Play button next to "Run tests in 'pockettester'"

![Run Unit Tests](/images/run_unitTests.png)

### To run all instrumented tests (integration and UI):
- From the top bar, select a valid emulation device like the Pixel 2 API 28
- View the application in "Android View"
- Open App > Java and try to see the package `com.abnamro.apps.pockettester (androidTest)`
- Right click on it and click the green Play button next to "Run 'All Instrumented tests'"

![Run Instrumented Tests](/images/run_androidTests.png)

# Showcases

With this assignment, there is a lot to show. I ordered it in five showcases which you can find below. They will show my skills in Software Development, Unit Testing, Integration Testing, UI Testing and the use of proper design techniques to keep software maintainable, testable and extendable in order have an overall high quality state. 

## 1. Software Development

In my opinion, a test automation engineer should not be afraid to get his hands dirty on code. Therefore I wanted to design and implement this application myself to show that I am proficient in the following topics:
- Reading and understanding existing code
- Writing code
- Understand IDEs like Android Studio and IntelliJ
- Understand build tools and dependency managers like Gradle and Maven
- Understand how to run and debug production code
- Usage of source control and version management systems like Git, Github and SourceTree 
- Understand how commits, push/pull, branching and merging works

![Git Flow](/images/git_flow.png)  
*The Git Flow of this Assignment at the moment of writing*

## 2. Unit testing

While unit tests are the domain of the software developer, test automation engineers should really know how to write good unit tests so they can train and assist developers, write additional tests if they are lacking and to understand which parts of the application are sufficiently covered or not. A test automation engineer should be proficient in the use of code coverage tooling to get an idea of the parts in the application that are covered well and parts that are potentially vulnerable to bugs. 

For this assignment I wrote two unit test classes, one in Java for [`NoteInfoTest`](/app/src/test/java/com/abnamro/apps/pockettester/NoteInfoTest.java) and one in Kotlin for [`ItemTypeInfoTest`](/app/src/test/java/com/abnamro/apps/pockettester/ItemTypeInfoTest.kt). They show:
- [Test setup](/app/src/test/java/com/abnamro/apps/pockettester/NoteInfoTest.java#L22) and [teardown](/app/src/androidTest/java/com/abnamro/apps/pockettester/UI/NoteUITest.java#L44)
- How to test objects in isolation
- That unit tests should be narrowly scoped 
- How to apply proper test naming
- Proper use of the JUnit assertion library

Unit tests are the bottom of the [test automation pyramid](https://martinfowler.com/articles/practical-test-pyramid.html#TheTestPyramid). There should be a lot of them and comply to the F.I.R.S.T. principles of Unit testing (Fast, Isolated, Repeatable, Self-Validating, Thorough)

## 3. Integration testing

Integration tests are tests where individual units are combined and tested as a group. Components that are not under test should be mocked so that they are not causing flakiness by interference with the components under test. In my opinion, integration tests are one of the most difficult test types. While you need a good amount of them, writing good integration tests is very hard because: 
- It is a team or even inter-team activity (who is responsible?) 
- When creating integration tests, you need to know the SUT very well to test the correct things
- The scope of an integration test is often too broad or too narrow making them brittle and less valuable
- For testing and mocking multiple application layers, you need to have a good architecture that every developer understand and applies

For this assignment, I wrote an [integration test](/app/src/androidTest/java/com/abnamro/apps/pockettester/Integration/ParcelIntegrationTest.java) on the external Parcel object. It shows the use of the Parcel object as an external dependency, but works still in isolation in our test. You can still find a mistake in the comments: at first I thought that the JUnit testrunner did the mocking of the object, later I learned that it uses the Parcel object itself and not a mocked version. (You see, integration tests are complex material).

Integration tests are the middle layer of the test automation pyramid. There should be sufficient of them and be run as often as possible because they tend to break fast with architectural changes.

## 4. Applying SOLID Principles and Testing with Mocks

The [Subscription Policy](/app/src/main/java/com/abnamro/apps/pockettester/SubscriptionPolicy.kt) showcases why we want to use [SOLID principles](https://en.wikipedia.org/wiki/SOLID). It is a class that uses [dependency injection](https://en.wikipedia.org/wiki/Dependency_injection) to acquire [inversion of control](https://en.wikipedia.org/wiki/Inversion_of_control). This is very valuable for testing, because now the dependencies can be mocked.

Why do you want that?
For example: We have no control over the [DataManager](/app/src/main/java/com/abnamro/apps/pockettester/DataManager.java) and the amount of notes that are in there. The DataManager can be an interface to a database, a cloud storage or maybe a file. But for your testcases, you always want to get the same amount of notes to validate the policy. Therefore we are creating a mock that will always return the same amount of notes.
  
Mocking works great too for the following case: the GeneralSettings object is pending functionality. There exists no concrete implementation. Because the dependencies are injected by their interface, they can be mocked and are therefore testable.

For this showcase, I wrote a new [SubscriptionPolicy](/app/src/main/java/com/abnamro/apps/pockettester/SubscriptionPolicy.kt) class in Kotlin, two interfaces and the unit tests in [SubscriptionPolicyTest](/app/src/test/java/com/abnamro/apps/pockettester/SubscriptionPolicyTest.kt) in Kotlin too. The SubscriptionPolicy counts the notes [somewhat more complex](/app/src/main/java/com/abnamro/apps/pockettester/SubscriptionPolicy.kt#L24) than necessary because the DataManager object is perfectly capable of returning all the notes at once with a single `DataManager.getNotes()` call, but I kept it that way because now we need to do more complex things with our mocks. Just as in real life.

This showcase shows:
- Understanding of dependency injection and inversion of control 
- The application of [interfaces](/app/src/main/java/com/abnamro/apps/pockettester/IDataManager.java)
- The use of [Mocks](/app/src/test/java/com/abnamro/apps/pockettester/SubscriptionPolicyTest.kt#L34) in unit/integration tests
- How to create independent, fast tests with reliable results
- The application of the [Mockito](https://site.mockito.org/) library

## 5. The Icing on the Cake: Creating UI Tests

This showcase shows the application of [UI tests](/app/src/androidTest/java/com/abnamro/apps/pockettester/UI/NoteUITest.java) on PocketTester. It is the last showcase for a reason: It is recommended to implement the UI tests after the unit tests and integration tests are build for a feature. Now the team does have a better understanding of the application and all issues that could be found by the lower level tests are ironed out. The scope of the UI test is more clear and we can re-use code that was used for the other tests.

You'll see that the tests in the UI tests are not completely UI driven. This is purposely done, because we want to really only focus on the UI parts we want to test:  
The first test, [creating a new note](/app/src/androidTest/java/com/abnamro/apps/pockettester/UI/NoteUITest.java#L55), will enter a new note through the UI, but the test will check the existence of the note in the DataManager.  
The second test, [selecting a test from the overview](/app/src/androidTest/java/com/abnamro/apps/pockettester/UI/NoteUITest.java#L86), will first fetch the data from the DataManager, then click on the same test in the overview and compare the details to see if the detail screen is correctly implemented.
By keeping the UI actions minimized, we get maintainable, stable and relative fast UI tests.

For these test, I used the native Espresso library because it is the de facto UI test library for Android. They are written in the language of the developers, which makes them easier to share within the team. A quick pilot with the build-in TestingBluePrint template project showed me that the Espresso library is many time faster then competing libraries like UiAutomator and Selenium type of libraries like Appium or Selendroid.   

The NoteUITest will show:
- The application of [the AAA](http://wiki.c2.com/?ArrangeActAssert) (Arrange, Act, Assert) pattern
- The usage of [Espresso patterns](/app/src/androidTest/java/com/abnamro/apps/pockettester/UI/NoteUITest.java#L62) to interact with objects
- The usage of [Matchers](/app/src/androidTest/java/com/abnamro/apps/pockettester/UI/NoteUITest.java#L96) to find the right type of objects and data
- Tests that [cleanup](/app/src/androidTest/java/com/abnamro/apps/pockettester/UI/NoteUITest.java#L44) after themselves no matter if they pass or fail
- The combined use of UI actions and non-UI actions for stable tests

# Afterthoughts

This was a fun assignment to do. I timeboxed the project at 8 hours of coding and used another 2 hours for documenting it properly. I am happy with the results and how the showcases have come into being. I learned a lot with this assignment: How to use Gradle, the Kotlin programming language, Android Studio, Espresso, Hamcrest and other Android-own libraries. Having experience in the field of test automation and software development has it perks: you have learned to pick up a programming language, a new IDE, the test implementation and new libraries very fast. 

There is a lot more to do and we didn't even touch mobile specific test cases: screen orientation, gestures, slow/no data connections, app switching, etc. And still, there is already a lot of material here. I couldn't cover each decision or philosophy behind certain approaches. It would be too much for both the reader as the writer to get into that amount of details. However, if there are any questions or if there are any ideas you want to share, I am happy to talk and elaborate.

Bas M. Dam  
Test Automation Specialist  
bas.dam@performancearchitecten.nl

# Materials Used

This is an incomplete list of reference materials I used during this assignment:  
- [Android Studio Reference Guide](https://developer.android.com/studio/intro)
- [Android Documentation](https://developer.android.com/docs)  
- [Kotlin Reference Guide](https://kotlinlang.org/docs/reference/)
- [Kotlin Cheat Sheet](https://blog.kotlin-academy.com/kotlin-cheat-sheet-1137588c75a)
- [JUnit Wiki](https://github.com/junit-team/junit4/wiki)
- [Working with Android Tools and Testing](https://app.pluralsight.com/library/courses/android-tools-testing/table-of-contents)
- [Android JUnit Parcelable model test](https://medium.com/@dbottillo/android-junit-parcelable-model-test-37a2f2ae18b1)
- [Using Android Parcel](https://dzone.com/articles/using-android-parcel)

This is the software and tooling I used:
- Android Studio for the main development
- Chrome as my searchable, extended knowledge source
- SourceTree for my version management and source control
- Notepad++ for editting some of the text files
- Greenshot for the screenshots
