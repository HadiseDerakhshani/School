import enums.Degree;
import enums.TeacherType;
public class PartTimeTeacher extends Teacher {
    private int hourlySalary;
    private int hourPerMonth;

    public PartTimeTeacher(String name, String lastName, String personalCode,int age,Degree degree,Integer experienceYear, int hourPerMonth, int hourlySalary) {
        super(name, lastName, personalCode,age,degree,experienceYear);
        this.hourlySalary = hourlySalary;
        this.hourPerMonth = hourPerMonth;
        setType(TeacherType.PART_TIME);
        setSalary(calculateSalary());
    }

    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public int getHourPerMonth() {
        return hourPerMonth;
    }

    public void setHourPerMonth(int hourPerMonth) {
        this.hourPerMonth = hourPerMonth;
    }

    @Override
    public Double calculateSalary() {
        double baseSalary = hourlySalary * hourPerMonth;
        return baseSalary - super.calculateInsurance(baseSalary) - super.calculateTax(baseSalary);
    }

    @Override
    public String toString() {
        return  super.toString() +"PartTimeTeacher{" +
                "hourlySalary=" + hourlySalary +
                ", hourPerMonth=" + hourPerMonth +
                '}';
    }
}
