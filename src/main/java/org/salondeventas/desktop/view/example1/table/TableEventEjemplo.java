package org.salondeventas.desktop.view.example1.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableEventEjemplo extends Application {

    //=============================================================================================
    public class EntityEvent {
        private String m_Name;
        private PropertyChangeSupport m_NamePCS = new PropertyChangeSupport(this);
        private int m_ActionCounter;
        private PropertyChangeSupport m_ActionCounterPCS = new PropertyChangeSupport(this);

        public EntityEvent(String name, int actionCounter) {
            m_Name = name;
            m_ActionCounter = actionCounter;
        }

        public String getName() {
            return m_Name;
        }

        public void setName(String name) {
            String lastName = m_Name;
            m_Name = name;
            System.out.println("Name changed: " + lastName + " -> " + m_Name);
            m_NamePCS.firePropertyChange("Name", lastName, m_Name);
        }

        public void addNameChangeListener(PropertyChangeListener listener) {
            m_NamePCS.addPropertyChangeListener(listener);
        }   

        public int getActionCounter() {
            return m_ActionCounter;
        }

        public void setActionCounter(int actionCounter) {
            int lastActionCounter = m_ActionCounter;
            m_ActionCounter = actionCounter;
            System.out.println(m_Name + ": ActionCounter changed: " + lastActionCounter + " -> " + m_ActionCounter);
            m_ActionCounterPCS.firePropertyChange("ActionCounter", lastActionCounter, m_ActionCounter);
        }

        public void addActionCounterChangeListener(PropertyChangeListener listener) {
            m_ActionCounterPCS.addPropertyChangeListener(listener);
        }   
    }

    //=============================================================================================
    private class AddPersonCell extends TableCell<EntityEvent, String> {
        Button m_Button = new Button("Undefined");
        StackPane m_Padded = new StackPane();

        AddPersonCell(final TableView<EntityEvent> table) {
            m_Padded.setPadding(new Insets(3));
            m_Padded.getChildren().add(m_Button);

            m_Button.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                    // Do something
                }
            });
        }

        @Override protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(m_Padded);
                m_Button.setText(item);
            }
        }
    }

    //=============================================================================================
    private ObservableList<EntityEvent> m_EventList;

    //=============================================================================================
    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Table View test.");

        VBox container = new VBox();

        m_EventList = FXCollections.observableArrayList(
                new EntityEvent("Event 1", -1),
                new EntityEvent("Event 2", 0),
                new EntityEvent("Event 3", 1)
                );
        final TableView<EntityEvent> table = new TableView<EntityEvent>();
        table.setItems(m_EventList);            

        TableColumn<EntityEvent, String> eventsColumn = new TableColumn<>("Events");
        TableColumn<EntityEvent, String> actionCol = new TableColumn<>("Actions");
        actionCol.setSortable(false);

        eventsColumn.setCellValueFactory(new Callback<CellDataFeatures<EntityEvent, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<EntityEvent, String> p) {
                EntityEvent event = p.getValue();
                event.addActionCounterChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent event) {                    	
                        System.out.println("test");
                    }
                });
                return new ReadOnlyStringWrapper(event.getName());
            }
        });

        actionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EntityEvent, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<EntityEvent, String> ev) {
                String text = "NONE";
                if(ev.getValue() != null) {
                    text = (ev.getValue().getActionCounter() != 0) ? "Edit" : "Create";
                }
                System.out.println("" + text);
                return new ReadOnlyStringWrapper(text);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        actionCol.setCellFactory(new Callback<TableColumn<EntityEvent, String>, TableCell<EntityEvent, String>>() {
            @Override
            public TableCell<EntityEvent, String> call(TableColumn<EntityEvent, String> personBooleanTableColumn) {
                return new AddPersonCell(table);
            }
        });

        table.getColumns().setAll(eventsColumn, actionCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Add Resources Button
        Button btnInc = new Button("+");
        btnInc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                System.out.println("+ clicked.");
                EntityEvent entityEvent = table.getSelectionModel().getSelectedItem();
                if (entityEvent == null) {
                    System.out.println("No Event selected.");
                    return;
                }
                entityEvent.setActionCounter(entityEvent.getActionCounter() + 1);
                // TODO: I expected the TableView to be updated since I modified the object.
            }
        });

        // Add Resources Button
        Button btnDec = new Button("-");
        btnDec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                System.out.println("- clicked.");
                EntityEvent entityEvent = table.getSelectionModel().getSelectedItem();
                if (entityEvent == null) {
                    System.out.println("No Event selected.");
                    return;
                }
                entityEvent.setActionCounter(entityEvent.getActionCounter() - 1);
                // TODO: I expected the TableView to be updated since I modified the object.
            }
        });

        container.getChildren().add(table);
        container.getChildren().add(btnInc);
        container.getChildren().add(btnDec);

        Scene scene = new Scene(container, 300, 600, Color.WHITE);        

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    //=============================================================================================
    public TableEventEjemplo() {
    }

    //=============================================================================================
    public static void main(String[] args) {
        launch(TableEventEjemplo.class, args);
    }

}