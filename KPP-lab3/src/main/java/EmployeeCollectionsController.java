import Employee.Employee;
import Employee.EmployeePosition;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeCollectionsController {
    public void printYoungestAndOldestEmployeeByEveryPosition(List<Employee> employees){
        var employeePositionListMap = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::position,
                        Collectors.toList()
                ));
        for (EmployeePosition employeePosition : employeePositionListMap.keySet()
             ) {
            var employeeList = employeePositionListMap.get(employeePosition);
            Optional<Employee> maxAge = employeeList.stream().min(Comparator.comparingInt((Employee o) -> o.birthYear()));
            Optional<Employee> minAge = employeeList.stream().max(Comparator.comparingInt((Employee o) -> o.birthYear()));
            System.out.println(
                    "Minimal and maximal age for position:"+employeePosition.name()+
                            "\nMin: " + minAge.orElseThrow().name() + "\nMax: " + maxAge.orElseThrow().name());
        }
    }

    public void printSalarySumByPosition(List<Employee> employees) {
        var map = new HashMap<EmployeePosition, List<Employee>>();
        for (var employee : employees) {
            EmployeePosition position = employee.position();
            if (!map.containsKey(position)) {
                map.put(position, new ArrayList<Employee>());
            }
            map.get(position).add(employee);
        }
        for (EmployeePosition employeePosition : map.keySet()) {
            List<Employee> list = map.get(employeePosition);
            var sum = 0.0;
            for (var employee : list) {
                sum += employee.salary();
            }
            System.out.println(employeePosition.name() + ": " + sum);
        }
    }

    public void printEmployeesByPositions(List<Employee> employees){
        printPositionEmployeeMap(employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::position,
                        Collectors.toList()
                )));
    }

    private void printPositionEmployeeMap(Map<EmployeePosition, List<Employee>> stringListMap){
        for (Map.Entry<EmployeePosition, List<Employee>> entry : stringListMap.entrySet()) {
            EmployeePosition category = entry.getKey();
            List<Employee> employeesInCategory = entry.getValue();
            System.out.println(category.name());
            printEmployeeList(employeesInCategory);
            System.out.println();
        }
    }
    public void printEmployeesBySalaryCategories(List<Employee> employees){
        float minSalary = (float) employees.stream()
                .mapToDouble(Employee::salary)
                .min()
                .orElse(0.0);
        float maxSalary = (float) employees.stream()
                .mapToDouble(Employee::salary)
                .max()
                .orElse(0.0);
        float separator = (maxSalary - minSalary) / 3;
        double range1End = minSalary + separator;
        double range2End = range1End + separator;
        Map<String, List<Employee>> salaryMap = employees.stream()
                .collect(Collectors.groupingBy(employee -> {
                    double salary = employee.salary();
                    if (salary <= range1End) {
                        return "Low Salary";
                    } else if (salary <= range2End) {
                        return "Medium Salary";
                    } else {
                        return "High Salary";
                    }
                }));
        printSalaryEmployeeMap(salaryMap);
    }

    private void printSalaryEmployeeMap(Map<String, List<Employee>> stringListMap){
        for (Map.Entry<String, List<Employee>> entry : stringListMap.entrySet()) {
            String category = entry.getKey();
            List<Employee> employeesInCategory = entry.getValue();
            System.out.println(category);
            printEmployeeList(employeesInCategory);
            System.out.println();
        }
    }

    public static List<Employee> mergeTwoLists(List<Employee> employeeList1,List<Employee> employeeList2){
        Set<Employee> set1 = new LinkedHashSet<>(employeeList1);
        set1.addAll(employeeList2);
        return new ArrayList<>(set1);
    }

    public List<Employee> deleteFromListByMinYear(List<Employee> list,int year){
        return list.stream().filter(employee -> employee.birthYear()>year).toList();
    }

    public static void printEmployeeList(List<Employee> employees){
        System.out.printf("%-20s|%-30s|%-40s|%10s|%4s|%n", "Name", "Position", "Description", "Salary", "Year");
        System.out.println("-".repeat(114));
        employees.forEach(System.out::println);
    }
}
