module vanduzee {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens vanduzee to javafx.fxml;
    opens vanduzee.controllers to javafx.fxml;
    opens vanduzee.controllers.assignmentControllers to javafx.fxml;
    opens vanduzee.controllers.studentControllers to javafx.fxml;
    opens vanduzee.controllers.testControllers to javafx.fxml;

    opens vanduzee.objects to javafx.base;

    exports vanduzee;
    exports vanduzee.controllers;
    exports vanduzee.controllers.assignmentControllers;
    exports vanduzee.controllers.studentControllers;
    exports vanduzee.controllers.testControllers;

    exports vanduzee.objects;
}
