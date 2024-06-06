**Java Developer Assessment: Robot Simulator**

**Objective:**

Imagine you got the task to write a program for simulating and testing the movement of robots on a table.
The goal is to write a little program in Java that can be executed by someone without Java experience. Please
pay attention to object-oriented design and good code and development practices, and provide instructions
on how to execute the program.

**How to run this program:**

Prerequisites: 
 - install [Maven 3.6.3](https://maven.apache.org/install.html)
 - install [Java 8](https://www.java.com/de/download/manual.jsp)

1. Assuming you are in `\robot-simulator` , build and package your code by executing: 

       mvn clean package

2.  Run the program using the command:

        java -jar target\robot-simulator-1.0.jar <input_file>

    Example:

        java -jar target\robot-simulator-1.0.jar C:\dev\test.txt

**Task Description:**

1. Input Format:

The input for the program should be a single text or text file which specifies the table size, initial
position and orientation of the robot, and a sequence of movement commands.
Example Input:

    5 5     // Table size
    1 2 N   // Initial position and orientation
    MRMLRMM // Movement commands

2. Table Definition:
 
The table is a grid of N x M tiles.
The upper left corner of the table has the coordinate (0,0).
The table size is specified by two numbers separated by a space. The first element specifies the
number of rows (first dimension) and the second number specifies the number of columns
(second dimension).

    5 6 // Table of size 5 rows and 6 columns

3. Robot Initialization:


The robot's initial position and orientation will be provided by three values:

- Row number
- Column number
- Orientation (options are North, West, South, and East)

Example Input:

    1 2 N // Robot starts at row 1, column 2, facing North

4. Commands:

The program should accept a set of one-letter commands as a string sequence.

- **M**: Move forward one tile in the direction the robot is currently facing.
- **R**: Rotate 90 degrees to the right (clockwise).
- **L**: Rotate 90 degrees to the left (counterclockwise).

Examples:

- Facing **N** and rotating **R** would end in **E**.
- Facing **S** and rotating **R** would end in **W**.

5. Output:
The program should output the final position and orientation of the robot.

Example Output (row 3, col 2 facing east):

    3 2 E

**Example Test Sequences:**

**Example 1:**

Input:

    5 5
    1 2 S
    MRMLM

Expected Output:

    3 1 S

**Example 2:**

Input:

    5 4
    1 2 N
    LMLMMRMMLRM

Expected Output:

    Exception in thread "main" org.iosb.BusinessException: Robot has reached at the table's end while moving forward!
