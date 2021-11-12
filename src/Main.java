import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Course course = new Course("math", 1);
        Course course1 = new Course("computer", 2);
        Course course2 = new Course("physic", 3);
        Course course3 = new Course("history", 4);
        Course course4 = new Course("art", 5);

        School school = new School("maktab", 1);
        School school1 = new School("madani", 2);
        School school2 = new School("alavi", 3);
        School school3 = new School("razavi", 3);
        School school4 = new School("jalal", 1);
        School school5 = new School("diba", 1);

        TeacherService teacherService = new TeacherService();
        System.out.println("---------------------------------------------------------------------");
        System.out.println(teacherService.addSchool("1002", school4));
        System.out.println("---------------------------------------------------------------------");
        System.out.println(teacherService.addCourse("1003", course4));
        System.out.println("---------------------------------------------------------------------");
        teacherService.findHighSalaryFullTime().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------");
        teacherService.listTeacherExperienceYear().forEach((i, j) -> System.out.println(i + ":" + j));
        System.out.println("---------------------------------------------------------------------");
        teacherService.findByDegreePartTime().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------");
        teacherService.findSchoolByTeacherList().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------");
        Set<School> schools = new HashSet<>();
        schools.add(school4);
        schools.add(school2);
        teacherService.mapSchoolTeachers(schools).forEach((i, j) -> System.out.println(i + ":" + j));
    }

}
