package vanduzee.models.tableFormatting;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class TableUtils {
    public static <S, T> void centerColumnValues(TableColumn<S, T> column) {
        column.setCellFactory(new Callback<TableColumn<S, T>, TableCell<S, T>>() {
            @Override
            public TableCell<S, T> call(TableColumn<S, T> param) {
                TableCell<S, T> cell = new TableCell<S, T>() {
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.toString());
                            setAlignment(Pos.CENTER);
                        }
                    }
                };
                return cell;
            }
        });
    }
}