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

You are sitting in the train on your daily commute. While dozing away a little, suddenly a great test idea pops up in your head: "If we can mock the HTTP calls, we can test every state in
the App without having to setup testdata on the server!". You want to note that idea down and directly share it with you colleagues, but how? Well, there is an app for that: PocketTester!

PocketTester, now you can write down your bugs, checks or test ideas; anytime, anywhere.

## Implementation

PocketTester is loosely based on the tutorial app "Course NoteKeeper" of [Jim Wilson](https://jwhh.com/). I implemented the adaptations myself for this assignment to have
a fun little toy application to fiddle around with. The app contains some basic functionality, but because it is nowhere near finished it will limit the scope for test 
automation and mimics an application in full development.  
Because the app is partially build on existing code, there are already some components that are not used (CourseModules), that are a bit clumsy (ItemTypeInfo) or that
apply a pattern I would never implement that way (DataManager). That is great too, because it creates challenges you would encounter as a test automation engineer in 
normal day to day work.

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
- From the top bar, Select "All Unit Tests"
- Click the green Play button

To run all instrumented tests (integration and UI):
- From the top bar, Select "All Instrumented Tests"
- From the top bar, select a valid emulation device like the Pixel 2 API 28
- Click the green Play button

# Showcases

## Development skills

In my opinion, a test automation engineer should not be afraid to get his hands dirty on code. Therefor I wanted to design and implement this application myself to
show that I am proficient in the following topics:
- Reading and understanding existing code
- Writing code myself
- Understand IDE's like Android Studio and IntelliJ
- Understand build tools and dependency managers like Gradle and Maven
- Understand how to run and debug production code
- Can find solutions for challenges I am facing
- Usage of source control and version management systems like Git, Github and SourceTree 
- Understand how commits, pushes, pulls and branching works

## Unit testing

While unit tests are the domain of the software developer, test automation engineers should really know how to write good unit tests so they can train and assist developers,
write additional tests if they are lacking and to understand which parts of the application is not covered. A test automation engineer should be proficient in the use
of code coverage tooling to get an idea of the parts in the application that are covered well and parts that are potentially vulnerable. 

For this assignment I wrote two unit test classes, one in Java and one in Kotlin that shows:
- Test setup and teardown
- How to test objects in isolation
- That unit tests should be narrowly scoped 
- How to apply proper test naming
- Proper use of the JUnit assertion library

Unit tests are the bottom of the test automation pyramid. There should be a lot of them and comply to the FIRST principles of Unit testing (Fast, Isolated, 
Repeatable, Self-Validating, Thorough)

## Integration testing

Integration tests are tests where individual units are combined and tested as a group. Components that are not under test should be mocked so that they are
not causing flakiness by interference with the components under test. In my opinion, integration tests are one of the most difficult test types. While you need a good
amount of them, writing good integration tests is very hard: 
- It is a team or even inter-team activity (who is responsible?) 
- When creating integration tests, you need to know the SUT very well
- The scope of an integration test is often too broad or too narrow
- For mocking multiple application layers, you need to have a good architecture that every developer understand and applies

For this assignment, I wrote an integration test on Parcel object. It shows:
- The mocking of an external dependency
- How to setup and use a mocked object
- The verification of results that comes back from a mocked object

 