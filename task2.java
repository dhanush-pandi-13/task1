import java.util.*;

class Room {
    String id;
    String type;
    double price;
    boolean available;

    public Room(String id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.available = true;
    }
}

class Booking {
    String bookingId;
    String roomId;
    String guestName;
    String checkIn;
    String checkOut;

    public Booking(String bookingId, String roomId, String guestName, String checkIn, String checkOut) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}

public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        initRooms();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Search Rooms\n2. Reserve Room\n3. View Booking\n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> searchRooms(scanner);
                case 2 -> reserveRoom(scanner);
                case 3 -> viewBooking(scanner);
                case 4 -> System.exit(0);
            }
        }
    }

    private static void initRooms() {
        rooms.add(new Room("101", "Single", 100));
        rooms.add(new Room("102", "Double", 150));
        rooms.add(new Room("103", "Suite", 250));
    }

    private static void searchRooms(Scanner scanner) {
        System.out.print("Enter room type (or press Enter to view all): ");
        String type = scanner.nextLine();
        for (Room room : rooms) {
            if (room.available && (type.isEmpty() || room.type.equalsIgnoreCase(type))) {
                System.out.println("Room ID: " + room.id + ", Type: " + room.type + ", Price: $" + room.price);
            }
        }
    }

    private static void reserveRoom(Scanner scanner) {
        System.out.print("Enter Room ID to reserve: ");
        String roomId = scanner.nextLine();
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.id.equals(roomId) && room.available) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available.");
            return;
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        String checkIn = scanner.nextLine();
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        String checkOut = scanner.nextLine();

        // Mock payment
        System.out.print("Enter card number: ");
        String card = scanner.nextLine();
        if (card.length() < 12) {
            System.out.println("Invalid card number.");
            return;
        }

        String bookingId = UUID.randomUUID().toString();
        bookings.add(new Booking(bookingId, roomId, name, checkIn, checkOut));
        selectedRoom.available = false;
        System.out.println("Booking confirmed. Your Booking ID is: " + bookingId);
    }

    private static void viewBooking(Scanner scanner) {
        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();
        for (Booking booking : bookings) {
            if (booking.bookingId.equals(bookingId)) {
                System.out.println("Booking Details:\nName: " + booking.guestName +
                        "\nRoom ID: " + booking.roomId +
                        "\nCheck-in: " + booking.checkIn +
                        "\nCheck-out: " + booking.checkOut);
                return;
            }
        }
        System.out.println("Booking not found.");
    }
}
