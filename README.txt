Additional assumptions:

1. It is not possible that one particular team plays in two different games at the same time
2. To remove game one must provide home team name and away team name. If they are swapped remove will not work.
3. Negative values are not valid scores
4. Score can be updated to any valid values regardless of current value of score
5. Finishing non existent game throws an exception
6. It is a ScoreBoard that verifies values for Score - as in different games different values may be accepted


A. ScoreBoard could be hidden behind interface which would give an actual object via factory method.
Then we could hide completely the one "utility" method getNumberOfGames which is not mentioned in spec
but is quite useful for testing. But this approach would add just another layer of complexity and solution
was to be as simple as it gets.

