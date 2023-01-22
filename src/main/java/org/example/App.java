package org.example;

import org.example.model.Employee;
import org.example.model.Job;
import org.example.service.EmployeeService;
import org.example.service.EmployeeServiceImpl;
import org.example.service.JobService;
import org.example.service.JobServiceImpl;

import java.util.Objects;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EmployeeService employeeService = new EmployeeServiceImpl();
        JobService jobService = new JobServiceImpl();
        boolean isTrue = true;
        while (isTrue){
            System.out.println("""
                    1 - Job
                    2 - Employee
                    3 - Stop
                    """);
            int choose = new Scanner(System.in).nextInt();
            switch (choose) {
                case 1:
                    boolean isJob = true;
                    while (isJob) {
                        System.out.println("""
                                ----------JOB----------
                                1 - Create Job Table
                                2 - Add Job
                                3 - Find Job By Id
                                4 - Sort By Experience
                                5 - Find Job By Employee Id
                                6 - Delete Description Column
                                7 - Update Job
                                8 - Delete Table
                                9 - Clean Table
                                10 - Add Description Column
                                11 - Exit
                                """);
                        int case1 = new Scanner(System.in).nextInt();
                        switch (case1) {
                            case 1:
                                jobService.createJobTable();
                                break;
                            case 2:
                                System.out.println("Select position: (Mentor, Management, Instructor)");
                                String position = new Scanner(System.in).nextLine();
                                System.out.println("Select profession: (Java, JavaScript)");
                                String profession = new Scanner(System.in).nextLine();
                                System.out.println("Select description: (Backend developer, Fronted developer)");
                                String description = new Scanner(System.in).nextLine();
                                System.out.println("Enter experience: ");
                                int experience = new Scanner(System.in).nextInt();
                                Job job = new Job(position, profession, description, experience);
                                jobService.addJob(job);
                                break;
                            case 3:
                                System.out.println("Enter ID: ");
                                Long id = new Scanner(System.in).nextLong();
                                System.out.println(jobService.getJobById(id));
                                break;
                            case 4:
                                System.out.println("Select (asc or desc): ");
                                String sort = new Scanner(System.in).nextLine();
                                System.out.println(jobService.sortByExperience(sort));
                                break;
                            case 5:
                                System.out.println("Enter employee ID: ");
                                Long employeeId = new Scanner(System.in).nextLong();
                                System.out.println(jobService.getJobByEmployeeId(employeeId));
                                break;
                            case 6:
                                System.out.println("Are you sure you want to remove the 'Description' column?: (y/n)");
                                String DropChoose = new Scanner(System.in).nextLine();
                                if (DropChoose.equals("y") || DropChoose.equals("yes")) {
                                    jobService.deleteDescriptionColumn();
                                } else {
                                    isJob = false;
                                }
                                break;
                            case 7:
                                System.out.println("Enter Job Id: ");
                                Long jobId = new Scanner(System.in).nextLong();
                                if (Objects.equals(jobService.getJobByEmployeeId(jobId).getId(), jobId)) {
                                    System.out.println("Select position: (Mentor, Management, Instructor)");
                                    String updatePosition = new Scanner(System.in).nextLine();
                                    System.out.println("Select profession: (Java, JavaScript)");
                                    String updateProfession = new Scanner(System.in).nextLine();
                                    System.out.println("Select description: (Backend developer, Fronted developer)");
                                    String updateDescription = new Scanner(System.in).nextLine();
                                    System.out.println("Enter experience: ");
                                    int updateExperience = new Scanner(System.in).nextInt();
                                    Job updateJob = new Job(updatePosition,
                                            updateProfession, updateDescription, updateExperience);
                                    jobService.updateJob(jobId, updateJob);
                                }else {
                                    System.out.println("There are no jobs with this id in the database!");
                                }
                                break;
                            case 8:
                                jobService.dropTable();
                                break;
                            case 9:
                                jobService.cleanTable();
                                break;
                            case 10:
                                jobService.addColumn();
                                break;
                            case 11:
                                isJob = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean isEmployee = true;
                    while (isEmployee) {
                        System.out.println("""
                                ----------EMPLOYEE----------
                                1 - Create Employee Table
                                2 - Add Employee
                                3 - Delete Table
                                4 - Clean Table
                                5 - Update Employee
                                6 - Show All Employees
                                7 - Find By Email
                                8 - Find Employee By Id
                                9 - Find Employee By Position
                                10 - Exit
                                """);
                        int case2 = new Scanner(System.in).nextInt();
                        switch (case2) {
                            case 1:
                                employeeService.createEmployee();
                                break;
                            case 2:
                                System.out.println("Enter fist name: ");
                                String firstName = new Scanner(System.in).nextLine();
                                System.out.println("Enter last name: ");
                                String lastName = new Scanner(System.in).nextLine();
                                System.out.println("Enter age: ");
                                byte age = new Scanner(System.in).nextByte();
                                System.out.println("Enter email: ");
                                String email = new Scanner(System.in).nextLine();
                                System.out.println("Enter Job(Id): ");
                                int jobId = new Scanner(System.in).nextInt();
                                Employee employee = new Employee(firstName, lastName, age, email, jobId);
                                employeeService.addEmployee(employee);
                                break;
                            case 3:
                                System.out.println("Are you sure you want to delete the 'Employee' table?: (y/n)");
                                String DropTable = new Scanner(System.in).nextLine();
                                if (DropTable.equals("y") || DropTable.equals("yes")) {
                                    employeeService.dropTable();
                                } else {
                                    isEmployee = false;
                                }
                                break;
                            case 4:
                                System.out.println("Are you sure you want to clear the 'Employees' table?: (y/n)");
                                String truncate = new Scanner(System.in).nextLine();
                                if (truncate.equals("y") || truncate.equals("yes")) {
                                    employeeService.cleanTable();
                                } else {
                                    isEmployee = false;
                                }
                                break;
                            case 5:
                                System.out.println("Enter Employee Id: ");
                                Long employeeId = new Scanner(System.in).nextLong();
                                if(!employeeService.getEmployeeById(employeeId).isEmpty()) {
                                    System.out.println("Enter fist name: ");
                                    String updateFirstName = new Scanner(System.in).nextLine();
                                    System.out.println("Enter last name: ");
                                    String updateLastName = new Scanner(System.in).nextLine();
                                    System.out.println("Enter age: ");
                                    byte updateAge = new Scanner(System.in).nextByte();
                                    System.out.println("Enter email: ");
                                    String updateEmail = new Scanner(System.in).nextLine();
                                    System.out.println("Enter Job(Id): ");
                                    int updateJobId = new Scanner(System.in).nextInt();
                                    Employee updateEmployee = new Employee(updateFirstName,
                                            updateLastName, updateAge, updateEmail, updateJobId);
                                    employeeService.updateEmployee(employeeId, updateEmployee);
                                }else {
                                    System.out.println("There is no employee with this id in the database!");
                                }
                                break;
                            case 6:
                                System.out.println(employeeService.getAllEmployees());
                                break;
                            case 7:
                                System.out.println("Enter email: ");
                                System.out.println(employeeService.findByEmail(new Scanner(System.in).nextLine()));
                                break;
                            case 8:
                                System.out.println("Enter employee id: ");
                                System.out.println(employeeService.getEmployeeById(new Scanner(System.in).nextLong()));
                                break;
                            case 9:
                                System.out.println("Select position: (Mentor, Management, Instructor)");
                                System.out.println(employeeService.getEmployeeByPosition(new Scanner(System.in).nextLine()));
                                break;
                            case 10:
                                isEmployee = false;
                                break;
                            default:
                                System.out.println("No such number!");
                                break;

                        }
                    }
                    break;
                case 3:
                    isTrue = false;
                    break;
            }
        }
    }
}
