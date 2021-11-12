import enums.Degree;
import enums.TeacherType;
public class FullTimeTeacher extends Teacher {
    private double baseSalary;

    public FullTimeTeacher(String name, String lastName, String personalCode, int age, Degree degree,Integer experienceYear, double baseSalary) {
        super(name, lastName, personalCode,age,degree,experienceYear);
        this.baseSalary= baseSalary;
        setType(TeacherType.FULL_TIME);
        setSalary(calculateSalary());
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public Double calculateSalary() {
        return baseSalary - super.calculateInsurance(baseSalary) - super.calculateTax(baseSalary);
    }

    @Override
    public String toString() {
        return super.toString()+ "FullTimeTeacher{" +
                "baseSalary=" + baseSalary +
                '}';
    }
}
