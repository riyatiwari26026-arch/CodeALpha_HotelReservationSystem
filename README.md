# Hotel Reservation System

The **Hotel Reservation System** is a Java-based console application developed to simplify the process of managing hotel room bookings. The project demonstrates the implementation of **Object-Oriented Programming (OOP)** concepts along with **File Handling**, **Collections**, and **Exception Handling** in Java.

The system allows users to view available rooms, search rooms by category (Standard, Deluxe, and Suite), book rooms, cancel existing bookings, and view booking details using a unique Booking ID. During the booking process, the application automatically calculates the total room cost based on the number of days and verifies the payment before confirming the reservation.

To ensure data persistence, the project uses **text files (`rooms.txt` and `bookings.txt`)** to store room and booking information. Whenever the application starts, it loads the existing data from these files, and any new bookings or cancellations are automatically saved, allowing the data to remain available even after the program is closed.

### Features

* Display all available hotel rooms
* Search rooms by room type
* Book a room with payment validation
* Generate unique Booking IDs automatically
* Cancel room bookings
* View booking details using Booking ID
* Store and retrieve room and booking data using text files
* User-friendly menu-driven console interface

### Technologies Used

* Java
* Object-Oriented Programming (Classes & Objects)
* Java Collections Framework (`ArrayList`)
* File Handling (`FileReader`, `FileWriter`, `BufferedReader`, `PrintWriter`)
* Exception Handling (`try-catch`)
* Scanner Class for user input

# Learning Outcomes

This project helped in understanding real-world application development using Java by applying OOP principles, managing collections of objects, handling file operations for permanent data storage, implementing exception handling for error management, and designing a structured, menu-driven console application.
