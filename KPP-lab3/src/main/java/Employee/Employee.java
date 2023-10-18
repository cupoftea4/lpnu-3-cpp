package Employee;

import java.util.Objects;

public record Employee(String name, EmployeePosition position, float salary, int birthYear) {
    @Override
    public String toString() {
        return String.format("%-20s|%-30s|%-40s|%10.2f|%4d|",
                name, position.name(), position.description(), salary, birthYear);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee that = (Employee) obj;
        return Objects.equals(name, that.name) && Objects.equals(this.position,that.position) &&
                Objects.equals(this.birthYear,that.birthYear) && Objects.equals(this.salary,that.salary);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
