package com.example.project2;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.plaf.basic.BasicMenuUI;

@SpringComponent
@UIScope
public class Editor  extends VerticalLayout implements KeyNotifier {
    private final EmployeeRepository repository;
    private Employee employee;
    TextField name = new TextField("Name");
    TextField description = new TextField("Description");
    TextField image = new TextField("Image");


    Button add = new Button("Add", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");

    Button delete = new Button("Delete", VaadinIcon.TRASH.create());

    HorizontalLayout buttons = new HorizontalLayout(add, cancel, delete);
    Binder<Employee> binder = new Binder<>(Employee.class);

    public Editor(EmployeeRepository repository){
        this.repository = repository;
        add(name, description, image, buttons);
        binder.bindInstanceFields(this);
        setSpacing(true);

        add.addClickListener(e -> add());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> edit(employee));
        setVisible(false);
    }

    void add(){
        repository.save(employee);
    }
    void delete(){
        repository.delete(employee);
    }
    void edit(Employee e){
        if (e == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = e.getId() != null;
        if (persisted) {
            employee = repository.findById(e.getId()).get();
        }
        else {
            employee = e;
        }
        cancel.setVisible(persisted);
        binder.setBean(employee);

        setVisible(true);
        name.focus();
    }//Tried making this auto-update after you click a button but it didn't work.
}
