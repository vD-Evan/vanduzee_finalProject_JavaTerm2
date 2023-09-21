package vanduzee.objects;

import java.time.LocalDate;

import vanduzee.models.tableFormatting.AssignmentTable;

public class Assignment extends Assessment {
    public Assignment(String newName, double newTotal, double newWeight, LocalDate newDateDue) {
        super(newName, newTotal, newWeight, newDateDue);
    }

    public Assignment(Assessment masterAssignment) {
        super(masterAssignment);
    }

    @Override
    public String getTableScore() {
        return AssignmentTable.formatScore(getScore(), getDaysLate());
    }

    @Override
    public String getTablePercent() {
        return AssignmentTable.formatPercent(getScore(), getTotal(), getDaysLate());
    }
}