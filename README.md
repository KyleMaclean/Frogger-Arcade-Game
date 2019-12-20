Tested on: A32 machine and my Linux laptop, both with Java 13.0.1 (note the JDK has a hard-coded External Library path and needs to be redirected on the testing machine) + JavaFX 13 + JUnit 5.4.2; Build script: Gradle 6.0.1

## Explanation of the MVC architecture
- controllers: provide operations on and access to single static instances of their corresponding views
- models: contain data and program logic modelled in a natural hierarchy of game objects and score data
- views: define GUI components which may be unchanging or modifiable by their corresponding controllers

## Class hierarchy of the game's interactive objects
- Objects which extend Intersectable are recognisable by the "pulled up" getIntersectingObjects() method
- Intersectable objects which can change co-ordinates and "act" extend the Actor class
- Actors which cycle through a series of sprites (of arbitrary length) at a parameterised speed extend the Animated class

## Refactorings
- Animation Timer (which was called every pulse) is replaced by Timeline
- Capping the number of calls to act() per second through the use of the above Timeline
- Animated.animate() is an overhaul of the original techniques used to achieve animation
- The game positional grid is reworked to be in multiples of 50
- Background graphics had to be remade to accommodate the now evenly distributed swamps
- Glitches related to the frog's movement magnitude being non-deterministic are resolved
- WorldView and WorldController are greatly simplified compared to the initial solution for initialising/managing game objects

## Extensions
- Crocodiles randomly appear in swamps which have not yet been activated
- A snake randomly slithers across the median
- Four game levels are present; the layout of each being modelled by an array of Intersectable objects
- The boss level (fourth) has six rows of snakes slithering across the road and median
- At most two bonus bugs can appear during a level
- Randomisation in whether some turtles dive and the colours of cars
- A time bar at the bottom of the screen shrinks over the course of twenty seconds per life/level
- Upon losing all lives, the player's score is written to the high-scores.csv file
- After writing the scores, the HomeController returns the user to the HomeView

## Points system
- 250 points are initially allocated per game
- 5 lives are allocated and the loss of each life subtracts 50 points
- Each successful swamp landing gains 100 points
- The remaining time as a percentage is multiplied by 100 and added as a bonus upon landing in a swamp
- Grabbing a bug adds 100 points to the score for the game

# Score modeling
- Score entries are stored in CSV rows and read into an Object with fields corresponding to columns
- By implementing Comparable, ScoreBoard overrides the compareTo method to sort according to score
- Reading, writing, formatting, etc. are all performed by the model and exposed to ScoreController
- Dummy score files (valid & invalid) are in "src/test/resources" to allow unit testing of the model

## Performance improvements
- keyReleased events are no longer required to track intersections
- Frog jumping animation is accomplished with a PauseTransition object instead of a separate keyEvent
- Refreshing is now handled by Timeline object; framerate is explicitly capped to 100FPS