package client;

import app.controller.DoctorController;
import app.controller.PatController;

import java.util.Scanner;
public class Client {
    private static PatController patController;
    private static DoctorController doctorController;
    private static Scanner scanner;

    public static void main(String[] args) {

        try {
            patController = new PatController();
             DoctorController doctorController = new DoctorController();
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println("Выберите желаемую операцию");
            System.out.println("1 - работа с пациентами");
            System.out.println("2 - работа с докторами");
            System.out.println("0 - выход");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    patOperations();
                    break;
                case "2":
                    doctorOperations();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Некорректный ввод!");
                    break;
            }
        }

    }

    public static void patOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую работу с пациентами:");
                System.out.println("1 - добавить пациента");
                System.out.println("2 - получить список всех пациентов");
                System.out.println("3 - найти пациента  по идентификатору");
                System.out.println("4 - обновить информацию о пациенте");
                System.out.println("5 - удалить пациента по идентификатору");
                System.out.println("6 - удалить пациента по названию");
                System.out.println("7 - восстановить пациента по идентификатору");
                System.out.println("8 - показать количество пациентов");
                System.out.println("9 - получить общую стоимость всех пациентов");
                System.out.println("10 - показать среднюю стоимость пациента");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя продукта");
                        String title = scanner.nextLine();
                        System.out.println("Введите стоимость лечения");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.println(patController.save(title, price));
                        break;
                    case "2":
                        patController.getAllActivePats().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор пациента");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(patController.getActivePatById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор пациента");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите новую стоимость лечения пациента");
                        price = Double.parseDouble(scanner.nextLine());
                        patController.update(id, price);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор пациента");
                        id = Integer.parseInt(scanner.nextLine());
                        patController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите имя пациента");
                        title = scanner.nextLine();
                        patController.deleteByTitle(title);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор пациента");
                        id = Integer.parseInt(scanner.nextLine());
                        patController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество пациентов: " + patController.getActivePatsCount());
                        break;
                    case "9":
                        System.out.println("Суммарная стоимость лечения пациентов: " +
                                patController.getActivePatsTotalCost());
                        break;
                    case "10":
                        System.out.println("Средняя стоимость лечения пациентов: " +
                                patController.getActivePatsAveragePrice());
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void doctorOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую работу с докторами:");
                System.out.println("1 - добавить доктора");
                System.out.println("2 - показать всех докторов");
                System.out.println("3 - показать доктора по идентификатору");
                System.out.println("4 - редактировать доктора");
                System.out.println("5 - удалить доктора по идентификатору");
                System.out.println("6 - удалить доктора по имени");
                System.out.println("7 - восстановить доктора по идентификатору");
                System.out.println("8 - показать количество докторов");
                System.out.println("9 - получить стоимость услуг доктора");
                System.out.println("10 - показать среднюю стоимость лечения одного пациента");
                System.out.println("11 - добавить услугу в корзину доктора");
                System.out.println("12 - удалить услугу из корзины доктора");
                System.out.println("13 - очистить корзину доктора");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя доктора");
                        String name = scanner.nextLine();
                        System.out.println(doctorController.save(name));
                        break;
                    case "2":
                        doctorController.getAllActiveDoctors().forEach(System.out:: println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(doctorController.getActiveDoctorById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите новое имя доктора");
                        name = scanner.nextLine();
                        doctorController.update(id, name);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        doctorController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите имя доктора");
                        name = scanner.nextLine();
                        doctorController.deleteByName(name);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        doctorController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество докторов: " +
                                doctorController.getActiveDoctorCount());
                        break;
                    case "9":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Стоимость  услуг доктора: " +
                                doctorController.getDoctorCartTotalPrice(id));
                        break;
                    case "10":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Средняя цена корзины покупателя: " +
                                doctorController.getDoctorCartAveragePrice(id));
                        break;
                    case "11":
                        System.out.println("Введите идентификатор покупателя");
                        int doctorId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите идентификатор продукта");
                        int patId = Integer.parseInt(scanner.nextLine());
                        doctorController.addPatToDoctorCart(doctorId, patId);
                        break;
                    case "12":
                        System.out.println("Введите идентификатор покупателя");
                        doctorId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите идентификатор продукта");
                        patId = Integer.parseInt(scanner.nextLine());
                        doctorController.removePatFromDoctorCart(doctorId, patId);
                        break;
                    case "13":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        doctorController.clearDoctorCart(id);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
