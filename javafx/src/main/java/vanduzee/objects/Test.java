package vanduzee.objects;

import java.time.LocalDate;

import vanduzee.models.tableFormatting.*;

public class Test extends Assessment {
    public Test(String newName, double newTotal, double newWeight, LocalDate newDateDue) {
        super(newName, newTotal, newWeight, newDateDue);
    }

    public Test(Assessment masterTest) {
        super(masterTest);
    }

    @Override
    public String getTableScore() {
        return TestTable.formatScore(getScore(), getDaysLate());
    }

    @Override
    public String getTablePercent() {
        return TestTable.formatPercent(getScore(), getTotal(), getDaysLate());
    }
}
