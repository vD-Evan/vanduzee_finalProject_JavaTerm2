package vanduzee.objects;

import java.io.Serializable;
import java.time.LocalDate;

import vanduzee.models.tableFormatting.AssessmentTable;

public class Assessment implements Serializable {
    // general variables
    String name;
    boolean masterCopy;

    // master variables
    double total;
    double weight;
    LocalDate dateDue;

    // student variables
    double score;
    double scoreWeighted;
    LocalDate dateSubmit;
    int daysLate;
    double finalMark;

    // #region constructors
    public Assessment(String newName, double newTotal, double newWeight, LocalDate newDateDue) {
        masterCopy = true;
        name = newName;
        total = newTotal;
        weight = newWeight;
        dateDue = newDateDue;
    }

    public Assessment(Assessment masterAssessment) {
        masterCopy = false;
        name = masterAssessment.getName();
        total = masterAssessment.getTotal();
        weight = masterAssessment.getWeight();
        dateDue = masterAssessment.getDateDue();

        score = -1;
        scoreWeighted = -1;
        dateSubmit = LocalDate.of(0, 1, 1);
        finalMark = 0;
    }
    // #endregion constructors

    // #region Get/Setters:
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMasterCopy() {
        return this.masterCopy;
    }

    public boolean getMasterCopy() {
        return this.masterCopy;
    }

    public void setMasterCopy(boolean masterCopy) {
        this.masterCopy = masterCopy;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getDateDue() {
        return this.dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScoreWeighted() {
        return this.scoreWeighted;
    }

    public void setScoreWeighted(double scoreWeighted) {
        this.scoreWeighted = scoreWeighted;
    }

    public LocalDate getDateSubmit() {
        return this.dateSubmit;
    }

    public void setDateSubmit(LocalDate dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    public double getFinalMark() {
        return this.finalMark;
    }

    public void setFinalMark(double finalMark) {
        this.finalMark = finalMark;
    }
    // #endregion get/set

    // #region table view (master)
    private String tableName;
    private String tableTotal;
    private String tableWeight;
    private String tableDateDue;

    public String getTableName() {
        tableName = AssessmentTable.formatName(name);
        return tableName;
    }

    public String getTableTotal() {
        tableTotal = AssessmentTable.formatTotal(total);
        return tableTotal;
    }

    public String getTableWeight() {
        tableWeight = AssessmentTable.formatWeight(weight);
        return tableWeight;
    }

    public String getTableDateDue() {
        tableDateDue = AssessmentTable.formatDate(dateDue);
        return tableDateDue;
    }
    // #endregion master

    // #region table view (student)
    private String tableScore;
    private String tablePercent;
    private String tableDateSubmit;

    public String getTableScore() { // overridden
        return tableScore;
    }

    public String getTablePercent() { // overridden
        return tablePercent;
    }

    public String getTableDateSubmit() {
        tableDateSubmit = AssessmentTable.formatDate(dateSubmit);
        return tableDateSubmit;
    }
    // #endregion student
}
