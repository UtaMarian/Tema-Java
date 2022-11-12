public class Profesor implements Human  {

        protected String nume;
        protected String prenume;
        protected Integer age;
        protected String materie;
        protected String facultate;

        Profesor(String nume,String prenume,String facultate,int varsta,String materie) {
                this.nume=nume;
                this.prenume=prenume;
                this.age=varsta;
                this.materie=materie;
                this.facultate=facultate;
        }

        public void greeting() {
            System.out.println("Hello, numele meu este " + nume + " " + prenume + "!");
        }
        public void doWork() {
            System.out.println("Go to work...");
        }
        @Override
        public String toString() {
            return "[Prof. " +
                    "Nume='" + nume + '\'' +
                    " Prenume='" + prenume + '\'' +
                    " Facultate= "+facultate+
                    " Materie: "+materie+
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
