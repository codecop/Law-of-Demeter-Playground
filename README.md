# Law Of Demeter Playground with PMD Check

This is an [Apache Maven](https://maven.apache.org/) project. Run `mvn test` to
run your tests. [JUnit](https://junit.org/junit5/) is provided as dependency.

## Constraint

[Law Of Demeter](https://www.khoury.northeastern.edu/home/lieber/LoD.html): Principle of Least Knowledge.

### Checking Code for Compliance

PMD contains a rule `LawOfDemeter` to check code for compliance with Law Of Demeter.
This project is set up to check the code using the Maven PMD Plugin on each `mvn test`.
You can also check the rules on their own with `mvn pmd:check`.
By using the [Maven Shell](https://github.com/jdillon/mvnsh) the time to run the check can be reduced by 50%.

In Eclipse, use `Run As/Maven build...` and see the violations in the Console:
![Eclipse Run Configuration](run_pmd.eclipse.png)

In IntelliJ IDEA, use `Maven Projects/Plugins/pmd/pmd:check` and see the violations in the Console:
![IDEA Run Configuration](run_pmd.intellij.png)

### Limitations of Checking Code

Obviously code analysis cannot find everything.

You can use `// NOPMD` line comments and `@SuppressWarnings("PMD.LawOfDemeter")` annotations to suppress false positives.
Use your good judgement. The goal is to follow the law, not to suppress it.

### License

This work is licensed under a [3-Clause BSD License](https://opensource.org/license/bsd-3-clause/), see `LICENSE` in repository.
