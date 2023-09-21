package vanduzee.objects;

/* #region imports */
import java.io.Serializable;
import java.util.ArrayList;

import vanduzee.models.tableFormatting.StudentTable;
/* #endregion */

public class Student implements Serializable {
    /* #region object variables */
    private String name;
    private int id;

    private ArrayList<Assignment> assignments;
    private int submitAssignmants;
    private ArrayList<Test> tests;
    private int submitTests;

    private double totalGrade;
    private double totalTest;
    /* #endregion */

    /* #region constructor */
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.submitTests = -1;
        this.submitAssignmants = -1;

        this.tests = new ArrayList<Test>();
        this.assignments = new ArrayList<Assignment>();

        this.totalGrade = -1;
        this.totalTest = -1;
    }
    /* #endregion */

    /* #region Get / Set */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Assignment> getAssignments() {
        return this.assignments;
    }

    public void addAssignment(Assignment newAssignment) {
        this.assignments.add(newAssignment);
    }

    public void removeAssignment(Assignment oldAssignment) {
        this.assignments.remove(oldAssignment);
    }

    public void clearAssignments() {
        this.assignments.clear();
    }

    public int getSubmitAssignmants() {
        return this.submitAssignmants;
    }

    public void setSubmitAssignmants(int submitAssignmants) {
        this.submitAssignmants = submitAssignmants;
    }

    public ArrayList<Test> getTests() {
        return this.tests;
    }

    public void addTest(Test newTest) {
        this.tests.add(newTest);
    }

    public void removeTest(Test oldTest) {
        this.tests.remove(oldTest);
    }

    public void clearTest() {
        this.tests.clear();
    }

    public int getSubmitTests() {
        return this.submitTests;
    }

    public void setSubmitTests(int submitTests) {
        this.submitTests = submitTests;
    }

    public double getTotalGrade() {
        return this.totalGrade;
    }

    public void setTotalGrade(double totalGrade) {
        this.totalGrade = totalGrade;
    }

    public double getTotalTest() {
        return this.totalTest;
    }

    public void setTotalTest(double totalTest) {
        this.totalTest = totalTest;
    }
    /* #endregion */

    /* #region formatted variables for tables */
    private String tableName;
    private String tableId;
    private String tableAssignments;
    private String tableTests;
    private String tableTestGrade;
    private String tableOverallGrade;

    public String getTableName() {
        tableName = StudentTable.formatName(name);
        return this.tableName;
    }

    public String getTableId() {
        tableId = StudentTable.formatId(id);
        return this.tableId;
    }

    public String getTableAssignments() {
        tableAssignments = StudentTable.formatAssignments(assignments);
        return this.tableAssignments;
    }

    public String getTableTests() {
        tableTests = StudentTable.formatTests(tests);
        return this.tableTests;
    }

    public String getTableTestGrade() {
        tableTestGrade = StudentTable.formatTotalTest(totalTest);
        return this.tableTestGrade;
    }

    public String getTableOverallGrade() {
        tableOverallGrade = StudentTable.formatTotalMark(totalGrade);
        return this.tableOverallGrade;
    }
    /* #endregion */
}
