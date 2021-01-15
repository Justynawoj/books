package com.kodilla.books;

import com.kodilla.books.domain.Book;
import com.kodilla.books.domain.BookForm;
import com.kodilla.books.service.BookService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private BookService bookService = BookService.getInstance();
    private Grid grid = new Grid<>(Book.class);

    //Filtrowanie wynikow
    private TextField filter = new TextField();
    private BookForm form = new BookForm(this);

    public MainView() {
    /*    grid.setColumns("title", "author", "publicationYear", "type");
        add(grid);
        setSizeFull();

        //konfiguracja buttona z filtrowania wynikow
        filter.setPlaceholder("Filter by title");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        update();
        add(filter, grid);
        // na koncu
        refresh();*/

        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("title", "author", "publicationYear", "type");
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(filter, mainContent);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(bookService.getBooks());
    }


    private void update() {
        grid.setItems(bookService.findByTitle(filter.getValue()));
    }

}
