import java.util.*;
import java.util.stream.Collectors;

import enums.Degree;
import enums.TeacherType;

import javax.management.openmbean.TabularType;

public class TeacherService {

    List<Teacher> teacher = new ArrayList<>();

    public TeacherService() {
        teacher.add(new FullTimeTeacher("ahmad", "admadi", "1007", 33, Degree.MA, 8, 20000));
        teacher.add(new FullTimeTeacher("zahra", "zahraei", "1006", 28, Degree.PHD, 3, 100000));
        teacher.add(new FullTimeTeacher("ali", "aliyani", "1005", 50, Degree.BS, 10, 500000));
        teacher.add(new PartTimeTeacher("ahmad", "admadi", "1003", 33, Degree.MA, 10, 4_500_000, 20));
        teacher.add(new PartTimeTeacher("zahra", "zahraei", "1002", 28, Degree.PHD, 2, 120_000, 10));
        teacher.add(new PartTimeTeacher("ali", "aliyani", "1001", 50, Degree.BS, 5, 20_000, 50));
    }


    public Optional<Teacher> findByPersonalCode(String personalCode) {
        return teacher.stream().filter(i -> i.getPersonalNumber().equalsIgnoreCase(personalCode)).findAny();
    }

    public Teacher addSchool(String personalCode, School school) {
        Optional<Teacher> foundedTeacher = findByPersonalCode(personalCode);
        if (foundedTeacher.isPresent()) {
            Set<School> school1 = foundedTeacher.get().getSchool();
            school1.add(school);
            foundedTeacher.get().setSchool(school1);
            return foundedTeacher.get();
        } else
            throw new RuntimeException("TEACHER NOT FOUNDED");

    }

    public Teacher addCourse(String personalCode, Course course) {
        Optional<Teacher> foundedTeacher = findByPersonalCode(personalCode);
        if (foundedTeacher.isPresent()) {

            Set<Course> course1 = foundedTeacher.get().getCourse();
            course1.add(course);
            foundedTeacher.get().setCourse(course1);
            return foundedTeacher.get();
        } else
            throw new RuntimeException("TEACHER NOT FOUNDED");

    }

    public List<Teacher> findHighSalaryFullTime() {
        double avg = getSalaryAverage();
        return teacher.stream().filter(i -> i.getSalary() > avg).collect(Collectors.toList());
    }

    public Double getSalaryAverage() {
        Double sum = teacher.stream().filter(teacher -> teacher.getType().equals(TeacherType.FULL_TIME)).
                map(Teacher::getSalary).reduce(0.0, Double::sum);
        long count = teacher.stream().filter(teacher -> teacher.getType().equals(TeacherType.FULL_TIME)).count();
        return sum / count;
    }

    public Map<TeacherType, List<Teacher>> listTeacherExperienceYear() {
        Map<TeacherType, List<Teacher>> collect = teacher.stream().filter(i -> i.getExperienceYear() == 10).
                collect(Collectors.groupingBy(Teacher::getType));
        return collect;
    }

    public List<Teacher> findByDegreePartTime() {
        return teacher.stream().filter(teacher -> teacher.getType().equals(TeacherType.PART_TIME)).
                filter(i -> i.getDegree().equals(Degree.BS))
                .filter(partTimeTeacher -> partTimeTeacher.getSchool().stream().anyMatch(school -> school.getDegree() >= 2))
                .filter(course -> course.getCourse().size() >= 2)
                .collect(Collectors.toList());
    }


    public Set<School> findSchoolByTeacherList() {
        Set<Set<School>> teacherSet = teacher.stream().map(i -> i.getSchool()).collect(Collectors.toSet());
        Set<School> schools = new HashSet<>();
        teacherSet.forEach(i -> i.addAll(schools));
        return schools;
    }

    public Map<School, List<Teacher>> mapSchoolTeachers(Set<School> schools) {
        Map<School, List<Teacher>> map = new HashMap<>();
        for (School item : schools) {
            List<Teacher> collect = teacher.stream().filter(i -> i.getSchool().contains(item)).
                    collect(Collectors.toList());
            map.put(item, collect);
        }
        return map;
    }


}
