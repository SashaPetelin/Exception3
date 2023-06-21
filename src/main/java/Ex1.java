import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ex1 {
    static boolean checkbox = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные: Фамилия Имя Отчество Дата рождения(в формате ДД.ММ.ГГГГ) Номер телефона(в формате 89996543210) и Пол(М или Ж)");
        String input = scanner.nextLine();
        String[] data = input.split(" ");

        if (data.length != 6) {
            System.err.println("Не все данные были введены!");
            return;
        }
        
        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String dayOfBirth = data[3];
        String phone = data[4];
        String genderSt = data[5];
        LocalDate DOB;

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            DOB = LocalDate.parse(dayOfBirth, format);
        } catch (Exception e) {
            System.err.println("Неправильно введена дата рождения");
            return;
        }

        long phoneNum;
        try {
            phoneNum = Long.parseLong(phone);
        } catch (NumberFormatException e) {
            System.err.println("Неправильно введен номер телефона");
            return;
        }

        char gender;
        if (genderSt.length() != 1 || (genderSt.charAt(0) == 'Ж' && genderSt.charAt(0) == 'М')) {
            System.err.println("Неверно указан пол");
            return;
        } else {
            gender = genderSt.charAt(0);
        }

        String fileName = firstName + " " + lastName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(lastName + " " + firstName + " " + middleName + ", " + DOB.format(DateTimeFormatter.ISO_LOCAL_DATE) + ", " + phoneNum + ", " + gender + ';');
            System.out.println("Файл успешно записан!");
            checkbox = false;
        } catch (IOException e) {
            System.err.println("Не удалось записать данные в файл!");
            e.printStackTrace();
        }
    }
}
