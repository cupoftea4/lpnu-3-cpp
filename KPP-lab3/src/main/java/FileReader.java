import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Employee.Employee;
import Employee.EmployeePosition;

public class FileReader {
    public static List<Employee> readEmployeeData(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String name = data[0].trim();
                    String positionName = data[1].trim();
                    String positionDescription = data[2].trim();
                    float salary = Float.parseFloat(data[3].trim());
                    int birthYear = Integer.parseInt(data[4].trim());

                    EmployeePosition position = new EmployeePosition(positionName, positionDescription);
                    Employee employee = new Employee(name, position, salary, birthYear);

                    employees.add(employee);
                }
            }
        }

        return employees;
    }
}