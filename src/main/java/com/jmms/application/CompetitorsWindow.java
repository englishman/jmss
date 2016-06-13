package com.jmms.application;

import com.jmms.domain.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class CompetitorsWindow extends HBox {

    private final ObservableList<Member> members;
    private final ObservableList<Member> competitors;

    private final TableView<Member> membersTable = new TableView<>();
    private final TableView<Member> competitorsTable = new TableView<>();

    public CompetitorsWindow(List<Member> members, List<Member> competitors) {
        this.members = FXCollections.observableList(members);
        this.competitors = FXCollections.observableList(competitors);

        getChildren().addAll(createMembersPane(), createButtonsPane(), createCompetitorsPane());
    }

    private Pane createMembersPane() {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        pane.setTop(new Label("Members List"));

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("lastName"));

        membersTable.setEditable(true);
        membersTable.getColumns().addAll(firstNameCol, lastNameCol);
        membersTable.setItems(members);

        pane.setCenter(membersTable);

        return pane;
    }

    private Node createButtonsPane() {
        final VBox pane = new VBox();
        pane.setSpacing(5);
        pane.setPadding(new Insets(40, 10, 10, 10));

        Button aNew = new Button("Add >>");
        aNew.setMaxWidth(Double.MAX_VALUE);
        aNew.setOnAction(e -> {
            TableView.TableViewSelectionModel<Member> tableSelectionModel = membersTable.getSelectionModel();
            int index = tableSelectionModel.getSelectedIndex();
            if (index >= 0) {
                Member member = members.remove(index);
                competitors.add(member);
            }
        });

        Button delete = new Button("<< Delete");
        delete.setMaxWidth(Double.MAX_VALUE);
        delete.setOnAction(e -> {
            TableView.TableViewSelectionModel<Member> tableSelectionModel = competitorsTable.getSelectionModel();
            int index = tableSelectionModel.getSelectedIndex();
            if (index >= 0) {
                Member member = competitors.remove(index);
                members.add(member);
            }
        });

        pane.getChildren().addAll(aNew, delete);
        return pane;
    }

    private Pane createCompetitorsPane() {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        pane.setTop(new Label("Competitors List"));

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("lastName"));

        competitorsTable.setEditable(true);
        competitorsTable.getColumns().addAll(firstNameCol, lastNameCol);
        competitorsTable.setItems(competitors);

        pane.setCenter(competitorsTable);

        return pane;
    }
}
