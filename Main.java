import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongAge extends Exception { }
class WrongNumber extends Exception { }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            } catch(WrongAge e) {
                System.out.println("Błędny wiek!");
            } catch(WrongNumber e) {
                System.out.println("Błędny numer!");
            }
        }
    }

    public static int menu() throws WrongNumber {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        String input = scan.nextLine();
        if (input.length() != 1) {
          throw new WrongNumber();
        }
        char menu = input.charAt(0);
        int ascii = (int) menu;
        if (ascii >= 48 && ascii <= 57) {
          int z = Character.getNumericValue(menu);
          if (z > 3 || z < 0) {
            throw new WrongNumber();
          }
          return z;
        }
      else {
        throw new WrongNumber();
      }
    }
    public static String ReadName() throws WrongStudentName {
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();

        return name;
    }
    public static int ReadAge() throws WrongAge {
      scan.nextLine();
      System.out.println("Podaj wiek: ");
      var age = scan.nextInt();
      if(age < 1 || age > 100)
        throw new WrongAge();
      return age;
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongAge {
        var name = ReadName();
        var age = ReadAge();
      
        scan.nextLine();
        System.out.println("Podaj datę urodzenia DD-MM-YYY");
        var date = scan.nextLine();
        (new Service()).addStudent(new Student(name, age, date));
    }
    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}
