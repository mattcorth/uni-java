# Labs
Labs were a series of smaller programming tasks relating to certain parts of previous lectures. 

A lab folder involves two files:
- one contains my code solutions.
  - This will have the filename `COMP108W0X`, where `X` is the lab number.
- the other can be compiled and run to check whether or not my solutions worked.
  - This file has the same filename but with `...App` appended to it
  - This file was made by my lecturer Dr Prudence Wong

Here is a brief summary of what each lab covers:
- *week-2* - `COMP108W02.java`
  - This lab has no overarching theme and is a simple introduction with no clear link to the lectures from that week
  - `sumFromOne` prints the sum of the integers between two numbers (inclusive)
  - `sumFromTo` prints the sum of the integers between two numbers (inclusive)
  - `isFactor` prints whether or not the second argument is a factor of the first argument
  - `multipleFactor` prints the multiples of the first argument that are factors of the second argument
- *week-3* - `COMP108W03.java`
  - This lab is partly about implementing sequential and binary searches and partly about finding the largest and smallest values in an array
  - `seqSearch` is a sequential search that aims to find a given value in an array and output the number of comparisons it took to do so
  - `binarySearch` is a (non-recursive) binary search that aims to find a given value in an array and output the number of comparisons it took to do so
  - `findMin` is a sequential search that finds and prints the smallest number in an array
  - `findMax` is a sequential search that finds and prints the largest number in an array
  - `findSecondMin` is a sequential search that finds and prints the second smallest number in an array
  - `findSecondMax` is a sequential search that finds and prints the second largest number in an array
- *week-4* - `COMP108W04.java`
  -  Lab was based on a scenario with a database of movies (stored as an array) and some requests from customers who want to watch movies (also stored as an array).
  -  We want to know (i) which requested movies do not exist in the database and (ii) how many times each movie in the database has been requested.
  - `notExists` takes two arrays and prints a list of all elements in the first one that aren't in the second one
  - `count` takes two arrays, then counts and prints the number of times each element from the second array appears in the first array 
- *week-6* - `COMP108W06.java`
  - This lab was on linked lists and performing basic search operations on it
  - `seqSearchList` is a sequential search that returns `true` if a given number is in the list and `false` otherwise
  - `countList` is a sequential search that returns the number of times a given number appears in the list
  - `searchMin` is a sequential search that returns the smallest number in the list
  - `searchMax` is a sequential search that returns the largest number in the list
  - Any other methods in this file were written by my lecturer
- *week-7* - `COMP108W07.java`
  - This lab was on performing the more advanced node insertion and deletion operations on a linked list
  - `searchMoveToHead` is a sequential search that will move a node to the head of the linked list if found
  - `searchMoveToTail` is a sequential search that will move a node to the tail of the linked list if found
- *week-8* - `COMP108W08.java`
  - This lab is a direct parallel to the week-4 lab but this time the movie database is stored using a linked list
  - `notExists` takes an array and prints a list of all elements that aren't in the linked list
  - `count` takes an array, then counts and prints the number of times each element from the linked list appears in the array
