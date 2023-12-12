# SnakeAI
## The Game
The project was a game of battle royale snake, where there would be 4 snakes in a 50x50 grid.
A snake would grow if it eats an apple. However the apple was degrading, thus it loses 0.1 value each tick.
If the apple's value is negative, then the snake decreases in size, and if the apple has a value <-5 then it
kills any snake that eats it.
If there is a head on head collision between snakes, then both snakes die.

Every tick, the game would send information to each "agent"/snakeAI. The information included info about every snake(their head
position, tail position, length, body parts etc),obstacle positions, and also the position of the apple.

## Outline of my solution
My solution to creating an efficient snakeAI to play the game was the following:
* Check if the apple is worth going for
  * If worth going for:
    * Have a path-finding algorithm to navigate the maze from my snake's head to the apple in the least amount of moves
  * If not worth going for:
    * Have a strategy to prioritize staying alive

Additionally the snakeAI would keep track of:
* Other snakes data. i.e) their head position
* Apple information
* Obstacle information

The solution was done using java

## Design of my solution
Using OOP, I split each component of the solution into its own file, and further more related files were put into packages.
This increased the readability and maintainability of the project, and made making changes easier as i was working on the project.

## Implementation of my solution
* Path finding algorithm

I used A* search with the manhattan heuristic to calculate the shortest path to the apple from a snake's head. The other options were breadth-first search or best first search, but
I found A* search to be the best out of the lot.
Manhattan was used since the snake could only make 4 moves(up,down,left,right)

* Checking if the apple is worth going for
  
Since A* is effective and quick at finding the shortest path in most instances, my snakeAI did the A* search for each snake and returned the number of moves the snake would take to
reach the apple. If my snake was going to reach the apple first, then it would go for it.If my snake wasnt going to reach the apple first, or there would be a head collision(2 snakes
will reach the apple in the same number of moves), then my snake would not go for the apple and prioritize staying alive

* Prioritize staying alive

Using a best-first search with a queue-based floodfill algorithm. The snakeAI would look at the moves the snake could make, and calculate the total available space in that area with
the floodfill algorithm in a 5x5 area. Then the algorithm will select the move that maximizes the total available space.
A 5x5 area had to be used to reduce computation cost, as if you dont specify the area size, it would calculate the total available space for the whole grid.

The only issue with this approach is that if 2+ moves have the same total available space, it would select the first move, which sometimes lead to its death.
Due to time constraints, I was unable to implementate a function to check which best move was the "best" and would reduce the chance of dying.

## Result
I was in the top 15 of my class. There was over 120+ submissions.

