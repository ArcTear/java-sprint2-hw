import java.util.HashMap;

public class YearlyReport {
    HashMap<Integer, MonthlyExpenseAndIncome> yearlyExpansesAndIncomes = new HashMap<>();

    private class MonthlyExpenseAndIncome {
        public int expense;
        public int income;

        public void setExpense(int expense) {
            this.expense = expense;
        }

        public void setIncome(int income) {
            this.income = income;
        }
    }

    public YearlyReport(String path) {
        var lines = CSVFileReader.readFileContentsOrNull(path);
        if (lines == null) {
            return;
        }
        parseStringsToHashMap(lines);
        System.out.println("Годовой отчет успешно считан!");
    }

    private void parseStringsToHashMap(String[] lines) {
        for (int i = 1; i < lines.length; i++) {
            String[] splittedLine = lines[i].split(",");
            Integer currentKey = Integer.parseInt(splittedLine[0]);
            if (!yearlyExpansesAndIncomes.containsKey(currentKey)) {
                yearlyExpansesAndIncomes.put(currentKey, new MonthlyExpenseAndIncome());
            }
            if (Boolean.parseBoolean(splittedLine[2])) {
                yearlyExpansesAndIncomes.get(currentKey).setExpense(Integer.parseInt(splittedLine[1]));
            } else {
                yearlyExpansesAndIncomes.get(currentKey).setIncome(Integer.parseInt(splittedLine[1]));
            }
        }
    }

    public int getMonthExpense(int month) {
        return yearlyExpansesAndIncomes.get(month).expense;
    }

    public int getMonthIncome(int month) {
        return yearlyExpansesAndIncomes.get(month).income;
    }

    public double getAverageExpense() {
        double sum = 0;
        for (MonthlyExpenseAndIncome monthlyExpenseAndIncome : yearlyExpansesAndIncomes.values()) {
            sum += monthlyExpenseAndIncome.expense;
        }
        return sum / yearlyExpansesAndIncomes.size();
    }

    public double getAverageIncome() {
        double sum = 0;
        for (MonthlyExpenseAndIncome monthlyExpenseAndIncome : yearlyExpansesAndIncomes.values()) {
            sum += monthlyExpenseAndIncome.income;
        }
        return sum / yearlyExpansesAndIncomes.size();
    }
}
