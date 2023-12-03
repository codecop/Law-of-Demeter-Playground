# Fantasy Battle Refactoring Kata

Code with LoD violations. 

## Constraint: Law of Demeter

Using PMD, can I fix all violations and NOT improve the design?

### Object Form of Law of Demeter "LoD_O"

A method `M` of an object `O` should invoke only the methods of the following kinds of objects: 

1. itself (method of `O` itself - not even methods on other instances of the same type) 
2. its parameter instances 
3. any objects it creates/instantiates inside `M` - maybe inside whole of `O` - these are parameters or fields then. 
4. its direct component objects (methods on any fields of `O`) 
5. global instances of `O`

### (Strict) Class Form of Law of Demeter "LoD_Cs"

A method `M` of an object of class `A` should invoke only the methods of the following kinds: 

1. any method of class `A`, including on other instances 
2. any method on the classes of its parameters 
3. any method of any classes it creates/instantiates inside
4. any method of any classes of its fields 
5. any method of global objects

Till now it seems the LoD_C is only needed in equals, because then we access the (private) fields of the other instance.

Weak Form "LoD_Cw"

6. any method of parent class in a class hierarchy

## Learnings

tbd
