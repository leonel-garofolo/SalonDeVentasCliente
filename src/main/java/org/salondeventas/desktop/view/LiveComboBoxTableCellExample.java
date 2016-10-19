package org.salondeventas.desktop.view;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.IndexRange;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class LiveComboBoxTableCellExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        TableView<Item> table = new TableView<>();

        TableColumn<Item, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Item, String> valueCol = new TableColumn<>("Value");
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        table.getColumns().add(typeCol);
        table.getColumns().add(valueCol);

        typeCol.setCellFactory(new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {

            @Override
            public TableCell<Item, String> call(TableColumn<Item, String> param) {
                return new LiveComboBoxTableCell<>(FXCollections.observableArrayList("String", "Integer"));
            }

        });

        for (int i = 1 ; i <= 10; i++) {
            table.getItems().add(new Item( (i % 2 == 0 ? "String" : "Integer"), "Item "+i));
        }

        Button checkButton = new Button("Run check");
        checkButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for (Item item : table.getItems()) {
                    System.out.println(item.getType() + " : "+item.getValue());
                }              
            }

        });

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(checkButton);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class LiveComboBoxTableCell<S,T> extends TableCell<S, T> implements EventHandler<KeyEvent> {

        private final ComboBox<T> comboBox ;
        private StringBuilder sb;
        private int lastLength;
        private ObservableList<T> data;

        public LiveComboBoxTableCell(ObservableList<T> items) {
            this.comboBox = new ComboBox<>(items);
            sb = new StringBuilder();
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            comboBox.setEditable(true);
            data = comboBox.getItems();
           
            this.comboBox.setOnKeyReleased(this);
            comboBox.valueProperty().addListener(new ChangeListener<T>() {
                @Override
                public void changed(ObservableValue<? extends T> obs, T oldValue, T newValue) {
                    // attempt to update property:
                    ObservableValue<T> property = getTableColumn().getCellObservableValue(getIndex());
                    if (property instanceof WritableValue) {
                        ((WritableValue<T>) property).setValue(newValue);
                    }
                }
            });
            
            this.comboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    comboBox.hide();
                }
            });
            // add a focus listener such that if not in focus, reset the filtered typed keys
            this.comboBox.getEditor().focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        // in focus
                    }
                    else {
                        lastLength = 0;
                        sb.delete(0, sb.length());
                        selectClosestResultBasedOnTextFieldValue(false, false);
                    }
                }
            });
            
            this.comboBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {                
                    selectClosestResultBasedOnTextFieldValue(true, true);
                }            
            });
            
           
        }

        @Override
        public void handle(KeyEvent event) {
            // this variable is used to bypass the auto complete process if the length is the same.
            // this occurs if user types fast, the length of textfield will record after the user
            // has typed after a certain delay.
            if (lastLength != (comboBox.getEditor().getLength() - comboBox.getEditor().getSelectedText().length()))
                lastLength = comboBox.getEditor().getLength() - comboBox.getEditor().getSelectedText().length();
            
            if (event.isControlDown() || event.getCode() == KeyCode.BACK_SPACE ||
                event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT || 
                event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.HOME || 
                event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB
                )
                return;
            
            IndexRange ir = comboBox.getEditor().getSelection();
            sb.delete(0, sb.length());
            sb.append(comboBox.getEditor().getText());
            // remove selected string index until end so only unselected text will be recorded
            try {
                sb.delete(ir.getStart(), sb.length());
            } catch (Exception e) { }
                
            ObservableList items = comboBox.getItems();
            for (int i=0; i<items.size(); i++) {
                if (items.get(i).toString().toLowerCase().startsWith(comboBox.getEditor().getText().toLowerCase())
                    )
                {
                    try {
                        comboBox.getEditor().setText(sb.toString() + items.get(i).toString().substring(sb.toString().length()));
                    } catch (Exception e) {
                        comboBox.getEditor().setText(sb.toString());
                    }
                    comboBox.getEditor().positionCaret(sb.toString().length());
                    comboBox.getEditor().selectEnd();
                    comboBox.getSelectionModel().select(i);
                    comboBox.show();
                    break;
                }
            }
            
        }

        /*
         * selectClosestResultBasedOnTextFieldValue() - selects the item and scrolls to it when
         * the popup is shown.
         * 
         * parameters:
         *  affect - true if combobox is clicked to show popup so text and caret position will be readjusted.
         *  inFocus - true if combobox has focus. If not, programmatically press enter key to add new entry to list.
         * 
         */
        private void selectClosestResultBasedOnTextFieldValue(boolean affect, boolean inFocus) {
            ObservableList items = this.comboBox.getItems();
            boolean found = false;
            for (int i=0; i<items.size(); i++) {
                if (this.comboBox.getEditor().getText().toLowerCase().equals(items.get(i).toString().toLowerCase())) {
                    try {
                        ListView lv = ((ComboBoxListViewSkin) this.comboBox.getSkin()).getListView();
                        lv.getSelectionModel().clearAndSelect(i);
                        lv.scrollTo(lv.getSelectionModel().getSelectedIndex());
                        found = true;
                        break;
                    } catch (Exception e) { }
                }
            }

            String s = comboBox.getEditor().getText();
            if (!found && affect) {            
                comboBox.getSelectionModel().clearSelection();
                comboBox.getEditor().setText(s);
                comboBox.getEditor().end();
            }
            
            if (!inFocus && comboBox.getEditor().getText() != null && comboBox.getEditor().getText().trim().length() > 0) {
                // press enter key programmatically to have this entry added
                //KeyEvent ke = KeyEvent.impl_keyEvent(comboBox, KeyCode.ENTER.toString(), KeyCode.ENTER.getName(), KeyCode.ENTER.impl_getCode(), false, false, false, false, KeyEvent.KEY_RELEASED);
                //comboBox.fireEvent(ke);
            }
        }
        
    

        @Override
        public void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                comboBox.setValue(item);
                setGraphic(comboBox);
            }
        }       
    }
    

    public static class Item {
        private final StringProperty type = new SimpleStringProperty();
        private final StringProperty value = new SimpleStringProperty();

        public Item(String type, String value) {
            setType(type);
            setValue(value);
        }

        public final StringProperty typeProperty() {
            return this.type;
        }

        public final String getType() {
            return this.typeProperty().get();
        }

        public final void setType(final String type) {
            this.typeProperty().set(type);
        }

        public final StringProperty valueProperty() {
            return this.value;
        }

        public final String getValue() {
            return this.valueProperty().get();
        }

        public final void setValue(final String value) {
            this.valueProperty().set(value);
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}