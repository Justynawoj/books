package com.kodilla.books;

import com.kodilla.books.domain.Book;
import com.kodilla.books.service.BookService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private BookService bookService = BookService.getInstance();
    private Grid grid = new Grid<>(Book.class);

    public MainView() {
        grid.setColumns("title", "author", "publicationYear", "type");
        add(grid);
        setSizeFull();

        // na koncu
        refresh();
    }

    public void refresh() {
        grid.setItems(bookService.getBooks());
    }
}
