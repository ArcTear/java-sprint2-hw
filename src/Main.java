import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int command;
        Scanner scanner = new Scanner(System.in);
        ArrayList<MonthlyReport> monthlyReports = null;
        ArrayList<YearlyReport> yearlyReports = null;
        do {
            System.out.println("Введите номер операции: ");
            printMenu();
            command = scanner.nextInt();
            if (command == 1) {
                monthlyReports = getMonthlyReports();
            } else if (command == 2) {
                yearlyReports = getYearlyReport();
            } else if (command == 3) {
                compareReports(monthlyReports, yearlyReports);
            } else if (command == 4) {
                printMonthlyAnalytics(monthlyReports);
            } else if (command == 5) {
                printYearlyAnalytics(yearlyReports);
            } else if (command == 6) {
                System.out.println("Выход.");
            } else {
                System.out.println("Операции с введенным номером не существует.");
            }
        } while (command != 6);
    }

    static void compareReports(ArrayList<MonthlyReport> monthlyReports, ArrayList<YearlyReport> yearlyReports) {
        if (!isDataOK(monthlyReports, yearlyReports)) {
            return;
        }
        boolean isCorrect = true;
        for (int i = 1; i <= 3; ++i) {
            if (monthlyReports.get(i - 1).getSumOfExpenses() != yearlyReports.get(0).getMonthExpense("0" + i) || monthlyReports.get(i - 1).getSumOfIncomes() != yearlyReports.get(0).getMonthIncome("0" + i)) {
                System.out.println("В " + i + " месяце обнаружено несоответствие.");
                isCorrect = false;
            }
        }
        if (isCorrect) {
            System.out.println("В отчетах ошибок не обнаружено! Все соответствует!");
        }
    }

    static void printMonthlyAnalytics(ArrayList<MonthlyReport> monthlyReports) {
        if (!areMonthlyReportsOK(monthlyReports)) {
            return;
        }
        for (int i = 1; i <= 3; i++) {
            System.out.print("Самая большая трата за ");
            if (i == 1) {
                System.out.print("январь - ");
                monthlyReports.get(0).printMaxExpense();
                System.out.print("Самая большая прибыль за январь - ");
                monthlyReports.get(0).printMaxIncome();
            } else if (i == 2) {
                System.out.print("февраль - ");
                monthlyReports.get(1).printMaxExpense();
                System.out.print("Самая большая прибыль за февраль - ");
                monthlyReports.get(1).printMaxIncome();
            } else {
                System.out.print("март - ");
                monthlyReports.get(2).printMaxExpense();
                System.out.print("Самая большая прибыль за март - ");
                monthlyReports.get(2).printMaxIncome();
            }
        }
    }

    static boolean areMonthlyReportsOK(ArrayList<MonthlyReport> monthlyReports) {
        if (monthlyReports == null) {
            System.out.println("Нет отчетов за месяцы.");
            return false;
        }
        return true;
    }

    static void printYearlyAnalytics(ArrayList<YearlyReport> yearlyReports) {
        if (!areYearlyReportsOK(yearlyReports)) {
            return;
        }
        System.out.println("Информация об отчётах за 2021 год: ");
        for (int i = 1; i <= 3; i++) {
            System.out.print("Прибыль за ");
            if (i == 1) {
                System.out.print("январь - ");
            } else if (i == 2) {
                System.out.print("февраль - ");
            } else {
                System.out.print("март - ");
            }
            System.out.println(yearlyReports.get(0).getMonthIncome("0" + i) - yearlyReports.get(0).getMonthExpense("0" + i) + ".");
        }
        System.out.println("Средний расход за все месяцы в году - " + yearlyReports.get(0).getAverageExpense() + ".");
        System.out.println("Средний доход за все месяцы в году - " + yearlyReports.get(0).getAverageIncome() + ".");
    }

    static boolean areYearlyReportsOK(ArrayList<YearlyReport> yearlyReports) {
        if (yearlyReports == null) {
            System.out.println("Нет отчета за год.");
            return false;
        }
        return true;
    }

    static boolean isDataOK(ArrayList<MonthlyReport> monthlyReports, ArrayList<YearlyReport> yearlyReports) {
        if (monthlyReports == null) {
            System.out.println("Нет отчетов за месяцы.");
            if (yearlyReports == null) {
                System.out.println("Нет отчета за год.");
            }
            return false;
        }
        if (yearlyReports == null) {
            System.out.println("Нет отчета за год.");
            return false;
        }
        return true;
    }

    static ArrayList<MonthlyReport> getMonthlyReports() {
        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            monthlyReports.add(new MonthlyReport("C:\\Users\\anton\\dev\\java-sprint2-hw\\resources\\m.20210" + i + ".csv"));
        }
        return monthlyReports;
    }

    static ArrayList<YearlyReport> getYearlyReport() {
        ArrayList<YearlyReport> yearlyReports = new ArrayList<>();
        yearlyReports.add(new YearlyReport("C:\\Users\\anton\\dev\\java-sprint2-hw\\resources\\y.2021.csv"));
        return yearlyReports;
    }

    static void printMenu() {
        System.out.println("1. Считать все месячные отчёты\n2. Считать годовой отчёт\n3. Сверить отчёты\n4. Вывести информацию о всех месячных отчётах\n5. Вывести информацию о годовом отчёте\n6. Выход");
    }
}
