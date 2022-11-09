package Semaine_3_java;

import java.util.ArrayList;

class Person extends SchoolAir {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public void whoAmI() {
    }

}

class Employee extends Person {
    protected String function;
    protected int salary;

    public Employee(String name, String function, int salary) {
        super(name);
        this.function = function;
        this.salary = salary;
    }

    public void whoAmI() {
        System.out.print(String.format("Je m'appelle %s, je gagne %d euros, je suis %s", name, salary, function));
    }

}

class Principal extends Employee {

    public Principal(String name) {
        super(name, "principal", 3000);

    }

}

class Teacher extends Employee {
    protected String lesson;
    protected Grade grade;

    public Teacher(String name, String lesson, Grade grade) {
        super(name, "professeur", 2000);
        this.lesson = lesson;
        this.grade = grade;
    }

    @Override
    public void whoAmI() {
        super.whoAmI();
        System.out.print(String.format(" de %s", lesson));
    }
}

class Volunteer extends Person {
    protected String job;

    public Volunteer(String name, String job) {
        super(name);
        this.job = job;
    }

    public void whoAmI() {
        System.out.print(String.format("Je m'appelle %s, je suis bénévole, je suis %s", name, job));
    }
}

class Kitchen extends Volunteer {
    public Kitchen(String name) {
        super(name, "cuisinier");
    }
}

class Cleaning extends Volunteer {
    public Cleaning(String name) {
        super(name, "agent de nettoyage");
    }
}

class Grade {
    public String name;

    public Grade(String name) {
        this.name = name;
    }

    public void getPersonsByGrade() {
        System.out.println(String.format("Les professeurs de la classe de %s : ", this.name));
        for (Teacher teacher : SchoolAir.getTeacherList()) {
            if (this.name.equals(teacher.grade.name)) {
                System.out.println("- " + teacher.name + " professeur de " + teacher.lesson);
            }
        }
        System.out.println(String.format("Les élèves de la classe de %s : ", this.name));
        for (Student student : SchoolAir.getStudentsList()) {
            if (this.name.equals(student.grade.name)) {
                System.out.println("- " + student.name);
            }
        }
    }
}

class Mark {
    Double number;

    // constructeur
    public Mark(Double number) {
        this.number = number;
    }

}

class Student extends Person {
    protected Grade grade;
    ArrayList<Mark> marks = new ArrayList<>();

    public Student(String name, Grade grade) {
        super(name);
        this.grade = grade;
    }

    public void whoAmI() {
        System.out.print(String.format("Je m'appelle %s, je suis étudiant(e)", name));
    }

    public void addMark(Mark mark) {
        marks.add(mark);
    }

    public ArrayList<Double> getAllMarks() {
        ArrayList<Double> allMarks = new ArrayList<>();
        for (Mark m : marks) {
            allMarks.add(m.number);
        }
        return allMarks;
    }

    public Double getAverage() {
        ArrayList<Double> allMarks = this.getAllMarks();
        Double total = 0.00;
        for (Double number : allMarks) {
            total += number;
        }
        return Double.isNaN((total / marks.size())) ? 0.0 : total / marks.size();
    }

}

public class SchoolAir {
    public static ArrayList<Person> persons = new ArrayList<>();

    public Person getPersonByName(String name) {
        for (Person person : persons) {
            if (person.name.equals(name))
                return person;
        }
        return null;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void ringBell() {
        for (Person person : persons) {
            person.whoAmI();
            System.out.println(".");
        }
    }

    public static ArrayList<Student> getStudentsList() {
        ArrayList<Student> students = new ArrayList<Student>();
        for (Person person : persons) {
            if (person.getClass().getSimpleName().equals("Student")) {
                students.add((Student) person);
            }
        }
        return students;
    }

    public static ArrayList<Teacher> getTeacherList() {
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        for (Person person : persons) {
            if (person.getClass().getSimpleName().equals("Teacher")) {
                teachers.add((Teacher) person);
            }
        }
        return teachers;
    }

    public static void main(String[] args) {

        SchoolAir school1 = new SchoolAir();

        Grade quatrieme = new Grade("quatrième");
        Grade cinquieme = new Grade("cinquième");
        school1.addPerson(new Principal("Jacques"));
        school1.addPerson(new Teacher("ALbert", "mathématiques", quatrieme));
        school1.addPerson(new Kitchen("Bernard"));
        school1.addPerson(new Cleaning("Laura"));
        school1.addPerson(new Teacher("Emeline", "physique", quatrieme));

        school1.addPerson(new Student("Rose", quatrieme));
        school1.addPerson(new Student("Paul", cinquieme));
        school1.addPerson(new Student("Romain", quatrieme));
        school1.ringBell();
        quatrieme.getPersonsByGrade();

    }

}
