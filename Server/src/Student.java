
public class Student implements Human{
    protected String nume;
    protected String prenume;
    protected Integer age;
    protected int an_de_studiu;
    protected String facultate;

    Student(String nume,String prenume,String facultate,int varsta,int an) {
        this.nume=nume;
        this.prenume=prenume;
        this.age=varsta;
        this.an_de_studiu=an;
        this.facultate=facultate;
    }

    public void greeting() {
        System.out.println("Hello, numele meu este " + nume + " " + prenume + "!");
    }
    public void doWork() {
        System.out.println("Go to facultate...");
    }
    @Override
    public String toString() {
        return "[Sd. " +
                "Nume='" + nume + '\'' +
                " Prenume='" + prenume + '\'' +
                " Facultate= "+facultate+
                " An de studiu: "+an_de_studiu+
                ", Varsta: " + age +" ani"+
                ']';
    }
    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Human o) {
        return this.age.compareTo(o.getAge());
    }
}
