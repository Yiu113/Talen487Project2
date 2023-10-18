package com.example.project2;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;
import java.util.Collection;

@Route
public class MainView extends VerticalLayout {

    private final EmployeeRepository repository;
    private final Editor editor;
    final Grid<Employee> grid;

    final TextField filter;
    private final Button add;
    private final Button refresh;

    public MainView(EmployeeRepository repository, Editor editor) {
        this.repository = repository;
        this.grid = new Grid<>(Employee.class);
        add(grid);
        listEmployees(null);
        this.editor = editor;
        this.add = new Button("New employee", VaadinIcon.PLUS.create());
        this.refresh = new Button("Refresh list", VaadinIcon.REFRESH.create());

        this.filter = new TextField();
        filter.setPlaceholder("Filter by name or ID");
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listEmployees(e.getValue()));

        HorizontalLayout menuBar = new HorizontalLayout(filter, add, refresh);
        add(menuBar, grid, editor);

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.edit(e.getValue());
        });

        add.addClickListener(e -> editor.edit(new Employee("", "", "")));
        //List view won't update after you click any edit commands; you'll need to run a filter to see changes.

        refresh.addClickListener(e -> listEmployees(filter.getValue()));
        refresh.addClickListener(e -> editor.setVisible(false));
        //Or click this button to refresh the list.

    }

    private void listEmployees(String filter) {
        //I'll start documentation here because this is where it actually gets meaty.
        //Textfield scans for what user inputs.
        if(filter != null && !filter.isEmpty()){
            if(filter.matches("\\d+")){//Checks if input is a number via regex.
                System.out.println(filter);
                grid.setItems(repository.findById(Long.parseLong(filter))); // If yes, searches by ID
            }else{
                grid.setItems(repository.findByName(filter));// Otherwise, searches by name.
            }
        }else{
            grid.setItems((Collection<Employee>) repository.findAll());//If the filter box is empty, puts the entire list.
        }
    }

}
