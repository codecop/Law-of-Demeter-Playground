# Write a Scheme

Create a Lisp, more precise a [Scheme](https://en.wikipedia.org/wiki/Scheme_(programming_language)), a dialect of Lisp.

This is a large assignment and an algorithmic challenge.

## Scheme Examples

Values: `1, #t, "Hello"`

Symbolic expressions (S-expressions): `(+ 1 2 3), (string-append "Hello" name), (sqrt 2), (list 1 2 3)`

## Requirements

Write a (R5RS) Scheme interpreter which 

1. accepts a file name as first argument and 
2. prints the result of evaluating the program to the console.

### Acceptance Criteria

* `(+ 1 2 3)` ... basic functionality
* `(string-append "Hello" " " "Name")` ... String functionality
* `(sqrt 2)` ... float function
* `(if #t (+ 1 2) (error "should not eval this")` ... lazy arguments
* `(define name "Peter") (string-append "Hello" name)` ... global variables, undefined/empty return value.
* `(let ((name "Peter")) (string-append "Hello" name))` ... local variables
