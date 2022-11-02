import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<Item> monthlyExpansesAndIncomes = new ArrayList<>();

    private class Item {
        public String name;
        public boolean isExpense;
        public int quantity;
        public int priceOfOne;

        public Item(String[] parameters) {
            name = parameters[0];
            isExpense = parameters[1].equals("TRUE");
            quantity = Integer.parseInt(parameters[2]);
            priceOfOne = Integer.parseInt(parameters[3]);
        }
    }

    public MonthlyReport(String path) {
        var lines = readFileContentsOrNull(path);
        if (lines == null) {
            return;
        }
        parseStringsToItems(lines);
        System.out.println("Месячный отчет успешно считан!");
    }

    public int getSumOfExpenses() {
        int sum = 0;
        for (Item item : monthlyExpansesAndIncomes) {
            if (item.isExpense) {
                sum += item.priceOfOne * item.quantity;
            }
        }
        return sum;
    }

    public int getSumOfIncomes() {
        int sum = 0;
        for (Item item : monthlyExpansesAndIncomes) {
            if (!item.isExpense) {
                sum += item.priceOfOne * item.quantity;
            }
        }
        return sum;
    }

    private String[] readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path)).split("\n");
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    private void parseStringsToItems(String[] lines) {
        for (int i = 1; i < lines.length; i++) {
            monthlyExpansesAndIncomes.add(new Item(lines[i].split(",")));
        }
    }

    public void printMaxExpense() {
        int max = 0;
        String maxName = "";
        for (Item item : monthlyExpansesAndIncomes) {
            if (item.isExpense && item.quantity * item.priceOfOne > max) {
                max = item.quantity * item.priceOfOne;
                maxName = item.name;
            }
        }
        System.out.println(maxName + ", " + max + ".");
    }

    public void printMaxIncome() {
        int max = 0;
        String maxName = "";
        for (Item item : monthlyExpansesAndIncomes) {
            if (!item.isExpense && item.quantity * item.priceOfOne > max) {
                max = item.quantity * item.priceOfOne;
                maxName = item.name;
            }
        }
        System.out.println(maxName + ", " + max + ".");
    }
}
