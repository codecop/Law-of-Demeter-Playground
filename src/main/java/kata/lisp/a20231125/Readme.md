# Write a Scheme

Create a [Lisp](https://en.wikipedia.org/wiki/Lisp_(programming_language)), more precise a [Scheme](https://en.wikipedia.org/wiki/Scheme_(programming_language)), a dialect of Lisp.

Scheme (R5RS) is a minimalist dialect of the Lisp family of programming languages. Lisp (historically LISP) is a family of programming languages with a long history and a distinctive, fully parenthesised prefix notation. It was created in 1968.

This is a large assignment and an algorithmic challenge.

## Scheme Examples

Values: `1, #t, "Hello"`

Symbolic expressions (S-expressions): `(+ 1 2 3), (string-append "Hello" name), (sqrt 2), (list 1 2 3)`

## Requirements

Write a Scheme interpreter which 

1. accepts a file name as first argument and 
2. prints the result of evaluating the program to the console.

### Acceptance Criteria

* `(+ 1 2 3)` ... basic functionality
* `(string-append "Hello" " " "Name")` ... String functionality
* `(sqrt 2)` ... float function
* `(if #t (+ 1 2) (error "should not eval this")` ... lazy arguments
* `(define name "Peter") (string-append "Hello" name)` ... global variables, undefined/empty return value.
* `(let ((name "Peter")) (string-append "Hello" name))` ... local variables
* `(define (times-two x) (* x 2))` ... your own functions

## Constraint: Law of Demeter

[Law Of Demeter](https://www.khoury.northeastern.edu/home/lieber/LoD.html): Principle of Least Knowledge. Using PMD rule `LawOfDemeter` to check code for compliance.

### Background Law of Demeter

Original definition: A supplier object to a method `M` is an object to which a message is sent in `M`.
The preferred supplier objects in `M` are

* the immediate parts of self (i.e. fields or elements if it is a container) or
* the arguments of `M` or
* the objects which are either objects created directly by `M` or 
* objects in global variables.

It states that the Law of Demeter decreases the complexity of the methods, but increases the number of methods.

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

The `super` call in the constructor, which is mandatory, is calling a super class instance method. So it is weak. But as it is mandatory, I will ignore it. It is an instance method of `O` but of a different class.

### Sometimes objects are just containers

This is a real issue. The PMD rule flags usage of List and Map, but it allows usage of arrays. 

## Learnings

So this is a method centred rule and does not deal with classes at all. So it is possible (and done in the exercise) to pass the return value got from some method into a new method `M2` which is allowed to call methods on it. This feels like cheating but is aligned with the rules. (Strict: adding private methods in the same class to fix LoD violation is cheating. I would like to move the new method to a new owner who owns the data.)

* `If` cheat by using a private method, accessing methods of return value of parameter call.
* `Variables` cheat by using another public method (which happen to exist), accessing methods of return value of parameter call.
* `EvalVisitor` same like `If`.

Also it does not talk about fields. Am I allowed to access fields of a foreign instance? To be consistent I would say no. (Strict: accessing fields is like method calls on this, so LoD_O.1.)

Another situation was to replace calls with fields. Enums and null values work better than state variables because I can use `==` instead of `getSomething()`, which is allowed. This is cheating again, at least it is not helping a good design.

It is unclear what global instances are for static methods. Creation of new objects is allowed, so named constructors and factory functions are allowed.

Stream and helper functions from `java.util.Arrays` and `java.util.stream.Collectors` cause LoD violations when we are strict. Need to use for-loops again. Even extended for loops using an `Iterator` would be violations.

Containers are tricky. Even if I use first class collections and cover all usage inside, I might have to call something returned from the lost or map.

Law of Demeter is data centred. It concerns with getters. So it is similar to Tell Don't Ask, but less strong. But not for static methods, or is it? So it allows some coupling and allows Feature Envy. In Addition I need first class collections to separate out the data. LoD does not limit the number of fields.

### Times

* 3x Coding 4+11h
* Manual review 4h
* Final Analysis 0.5h
