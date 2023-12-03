# Fantasy Battle Refactoring Kata

Code with LoD violations. 

## Constraint: Law of Demeter

Using PMD, can I fix all violations and NOT improve the design?

* Player:30 Potential violation of Law of Demeter (method chain calls).
* Player:30 Potential violation of Law of Demeter (object not created locally).
* Player:32 Potential violation of Law of Demeter (method chain calls).
* Player:32 Potential violation of Law of Demeter (method chain calls).
* Player:32 Potential violation of Law of Demeter (method chain calls).
* Player:32 Potential violation of Law of Demeter (object not created locally).
* Player:45 Potential violation of Law of Demeter (object not created locally).
* Player:46 Potential violation of Law of Demeter (object not created locally).
* Player:47 Potential violation of Law of Demeter (object not created locally).
* Player:48 Potential violation of Law of Demeter (object not created locally).
* Player:49 Potential violation of Law of Demeter (object not created locally).
* Player:52 Potential violation of Law of Demeter (object not created locally).
* Player:53 Potential violation of Law of Demeter (object not created locally).
* Player:54 Potential violation of Law of Demeter (object not created locally).
* Player:55 Potential violation of Law of Demeter (object not created locally).
* Player:56 Potential violation of Law of Demeter (object not created locally).
* Player:61 Potential violation of Law of Demeter (object not created locally).
* Player:62 Potential violation of Law of Demeter (object not created locally).
* Player:63 Potential violation of Law of Demeter (object not created locally).
* Player:64 Potential violation of Law of Demeter (object not created locally).
* Player:65 Potential violation of Law of Demeter (object not created locally).
* Player:66 Potential violation of Law of Demeter (object not created locally).
* Player:67 Potential violation of Law of Demeter (object not created locally).
* Player:68 Potential violation of Law of Demeter (object not created locally).
* Player:69 Potential violation of Law of Demeter (object not created locally).
* Player:70 Potential violation of Law of Demeter (object not created locally).

### Object Form of Law of Demeter "LoD_O"

A method `M` of an object `O` should invoke only the methods of the following kinds of objects: 

1. itself (method of `O` itself - not even methods on other instances of the same type) 
2. its parameter instances 
3. any objects it creates/instantiates inside `M` - maybe inside whole of `O` - these are parameters or fields then. 
4. its direct component objects (methods on any fields of `O`) 
5. global instances of `O`

## Learnings

No problem, in 6 minutes there are 26 LoD violations fixed. Code is the same. Got some static functions. The methods itself look good, many small methods indeed.

Resulting methods are static, so belong "nowhere".

