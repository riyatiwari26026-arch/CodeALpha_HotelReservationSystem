import java.io.*;
import java.util.*;

class Room {
    int roomNumber;
    String roomType;
    double pricePerDay;
    boolean isAvailable;

    Room(int roomNumber, String roomType, double pricePerDay, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
    }

    String toFileFormat() {
        return roomNumber + "," + roomType + "," + pricePerDay + "," + isAvailable;
    }
}

class Booking {
    int bookingId;
    String customerName;
    int roomNumber;
    String roomType;
    int numberOfDays;
    double totalAmount;

    Booking(int bookingId, String customerName, int roomNumber, String roomType,
            int numberOfDays, double totalAmount) {

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.numberOfDays = numberOfDays;
        this.totalAmount = totalAmount;
    }

    String toFileFormat() {
        return bookingId + "," + customerName + "," + roomNumber + "," +
               roomType + "," + numberOfDays + "," + totalAmount;
    }
}

public class HotelReservationSystem {

    static Scanner input = new Scanner(System.in);

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    static final String ROOM_FILE = "rooms.txt";
    static final String BOOKING_FILE = "bookings.txt";

    public static void main(String[] args) {

        loadRooms();
        loadBookings();

        int choice;

        do {
            System.out.println("\n========== HOTEL RESERVATION SYSTEM ==========");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Search Room By Type");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View Booking Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    showAvailableRooms();
                    break;

                case 2:
                    searchRoomByType();
                    break;

                case 3:
                    bookRoom();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    viewBookingDetails();
                    break;

                case 6:
                    saveRooms();
                    saveBookings();
                    System.out.println("Thank you for using the hotel reservation system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    static void loadRooms() {
        File file = new File(ROOM_FILE);

        if (!file.exists()) {
            rooms.add(new Room(101, "Standard", 1500, true));
            rooms.add(new Room(102, "Standard", 1500, true));
            rooms.add(new Room(201, "Deluxe", 2500, true));
            rooms.add(new Room(202, "Deluxe", 2500, true));
            rooms.add(new Room(301, "Suite", 4000, true));
            rooms.add(new Room(302, "Suite", 4000, true));

            saveRooms();
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(ROOM_FILE));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int roomNumber = Integer.parseInt(data[0]);
                String roomType = data[1];
                double price = Double.parseDouble(data[2]);
                boolean available = Boolean.parseBoolean(data[3]);

                rooms.add(new Room(roomNumber, roomType, price, available));
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error while loading room data.");
        }
    }

    static void saveRooms() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(ROOM_FILE));

            for (Room room : rooms) {
                writer.println(room.toFileFormat());
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error while saving room data.");
        }
    }

    static void loadBookings() {
        File file = new File(BOOKING_FILE);

        if (!file.exists()) {
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(BOOKING_FILE));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int bookingId = Integer.parseInt(data[0]);
                String customerName = data[1];
                int roomNumber = Integer.parseInt(data[2]);
                String roomType = data[3];
                int days = Integer.parseInt(data[4]);
                double amount = Double.parseDouble(data[5]);

                bookings.add(new Booking(bookingId, customerName, roomNumber,
                        roomType, days, amount));
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error while loading booking data.");
        }
    }

    static void saveBookings() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(BOOKING_FILE));

            for (Booking booking : bookings) {
                writer.println(booking.toFileFormat());
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error while saving booking data.");
        }
    }

    static void showAvailableRooms() {
        System.out.println("\n------ Available Rooms ------");

        boolean roomFound = false;

        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room Number: " + room.roomNumber);
                System.out.println("Room Type: " + room.roomType);
                System.out.println("Price Per Day: Rs. " + room.pricePerDay);
                System.out.println("-----------------------------");
                roomFound = true;
            }
        }

        if (!roomFound) {
            System.out.println("No rooms are available right now.");
        }
    }

    static void searchRoomByType() {
        input.nextLine();

        System.out.print("Enter room type Standard / Deluxe / Suite: ");
        String type = input.nextLine();

        boolean found = false;

        System.out.println("\n------ Search Result ------");

        for (Room room : rooms) {
            if (room.roomType.equalsIgnoreCase(type) && room.isAvailable) {
                System.out.println("Room Number: " + room.roomNumber);
                System.out.println("Room Type: " + room.roomType);
                System.out.println("Price Per Day: Rs. " + room.pricePerDay);
                System.out.println("-----------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available room found for this type.");
        }
    }

    static void bookRoom() {
        input.nextLine();

        System.out.print("Enter customer name: ");
        String name = input.nextLine();

        System.out.print("Enter room type Standard / Deluxe / Suite: ");
        String type = input.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.roomType.equalsIgnoreCase(type) && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Sorry, selected room type is not available.");
            return;
        }

        System.out.print("Enter number of days: ");
        int days = input.nextInt();

        double totalAmount = selectedRoom.pricePerDay * days;

        System.out.println("\n------ Payment Details ------");
        System.out.println("Room Number: " + selectedRoom.roomNumber);
        System.out.println("Room Type: " + selectedRoom.roomType);
        System.out.println("Price Per Day: Rs. " + selectedRoom.pricePerDay);
        System.out.println("Total Amount: Rs. " + totalAmount);

        System.out.print("Enter payment amount: Rs. ");
        double paidAmount = input.nextDouble();

        if (paidAmount < totalAmount) {
            System.out.println("Payment failed. Paid amount is less than total bill.");
            return;
        }

        int bookingId = generateBookingId();

        selectedRoom.isAvailable = false;

        Booking newBooking = new Booking(
                bookingId,
                name,
                selectedRoom.roomNumber,
                selectedRoom.roomType,
                days,
                totalAmount
        );

        bookings.add(newBooking);

        saveRooms();
        saveBookings();

        System.out.println("\nRoom booked successfully.");
        System.out.println("Your Booking ID is: " + bookingId);
    }

    static int generateBookingId() {
        int id = 1001;

        for (Booking booking : bookings) {
            if (booking.bookingId >= id) {
                id = booking.bookingId + 1;
            }
        }

        return id;
    }

    static void cancelBooking() {
        System.out.print("Enter booking ID to cancel: ");
        int id = input.nextInt();

        Booking bookingToCancel = null;

        for (Booking booking : bookings) {
            if (booking.bookingId == id) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel == null) {
            System.out.println("Booking not found.");
            return;
        }

        for (Room room : rooms) {
            if (room.roomNumber == bookingToCancel.roomNumber) {
                room.isAvailable = true;
                break;
            }
        }

        bookings.remove(bookingToCancel);

        saveRooms();
        saveBookings();

        System.out.println("Booking cancelled successfully.");
    }

    static void viewBookingDetails() {
        System.out.print("Enter booking ID: ");
        int id = input.nextInt();

        boolean found = false;

        for (Booking booking : bookings) {
            if (booking.bookingId == id) {
                System.out.println("\n------ Booking Details ------");
                System.out.println("Booking ID: " + booking.bookingId);
                System.out.println("Customer Name: " + booking.customerName);
                System.out.println("Room Number: " + booking.roomNumber);
                System.out.println("Room Type: " + booking.roomType);
                System.out.println("Number of Days: " + booking.numberOfDays);
                System.out.println("Total Amount Paid: Rs. " + booking.totalAmount);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Booking details not found.");
        }
    }
}
```
