import java.util.ArrayList;
import java.util.Scanner;

public class StudentGrades {
    public static void main(String[] args) {
        ArrayList<Integer> grades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;

        // Reading student grades
        System.out.println("Enter student grades (type 'done' to finish): ");
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            try {
                int grade = Integer.parseInt(input);
                grades.add(grade);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid grade or 'done' to finish.");
            }
        }

        // Compute average, highest, and lowest scores
        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            int sum = 0;
            int highest = grades.get(0);
            int lowest = grades.get(0);

            for (int grade : grades) {
                sum += grade;
                if (grade > highest) {
                    highest = grade;
                }
                if (grade < lowest) {
                    lowest = grade;
                }
            }

            double average = (double) sum / grades.size();

            // Display results
            System.out.printf("Average score: %.2f%n", average);
            System.out.println("Highest score: " + highest);
            System.out.println("Lowest score: " + lowest);
        }

        scanner.close();
    }
}
