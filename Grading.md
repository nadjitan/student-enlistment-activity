# Grades

## Meets Requirements: 10/20%

* Occupancy counter should not be in ```Room```. It should be in ```Section```.
* Some requirements missing.

## Test-Driven Development: 10/15%

* New tests were attempted, but some were broken or didn't test what they were supposed to be testing.

## Readability: 19/20%

* Most new code is quite readable, except for ```Schedule.checkPeriodConstraints()```.


## Object-Oriented Design Principles: 20/20%

* Conforms to discussed OO principles.

## Coding Practices: 10/15%

Positive

* ```Room``` class conforms to discussed practices
* ```Room``` field in ```Section``` conforms to discussed practcies

Negative

* Time should not have been represented with String since parsing is error prone.


## Version Control & Continuous Integration Practices: 7/10%

Negative 

* IDE files (.vscode, .classpath, .settings, .idea, .project) should not be committed
* Generated and compiled files should not be committed (```target``` directory). You should have created a ```.gitignore``` file to avoid committing this directory.

Positive

* Some commit messages are good, informative: "Edit title and requirements", "Added room and edit schedule", "Added room param to section"


## OVERALL SCORE ##

**From submitted work:** 77/100

**Bonuses:**

* Submitted on time: 10 pts
* Code actually compiles and runs: 10 pts

### TOTAL SCORE ###
**97/100**