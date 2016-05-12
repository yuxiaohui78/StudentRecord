# Student Record
This is a project for COM212 Fall 2012 by Kirby, Danya, Amanda, Zach. The goal was to create a record which stores student information (name, student number, SSN, email, etc.). Each student can enter ideas to their record, and an admin who can access these records, modify them and assign ideas a rating.
The project uses two bianry search trees to implement O(log n) lookup, where students were sorted by SSN and by student number, an ordered linked list which stores the ideas sorted by rating, and a queue for each student which stores the student's last 10 inserted ideas. 

