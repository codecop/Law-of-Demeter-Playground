# Requirements

Write a Scheme interpreter which 

1. accepts a file name as first argument and 
2. prints the result of evaluating the program to the console.

## Scheme Examples

Values: `1, #t, "Hello"`

Symbolic expressions (S-expressions): `(+ 1 2 3), (sqrt 2), (list 1 2 3), (string-append "Hello" name)`

Acceptance Criteria

* `(+ 1 2 3)` ... basic function
* `(sqrt 2)` ... float function
* `(let ((name "Peter")) (string-append "Hello" name))` ... local variables
