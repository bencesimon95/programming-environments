package hu.nye.progkor.bookcatalog.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hu.nye.progkor.bookcatalog.data.model.Genre;
import hu.nye.progkor.bookcatalog.data.model.Book;
import hu.nye.progkor.bookcatalog.data.repository.Repository;
import hu.nye.progkor.bookcatalog.service.BookService;

    class DefaultBookServiceTest {

    private static final Long DUMMY_BOOK_ID = 1L;
    private static final Book DUMMY_BOOK = new Book(DUMMY_BOOK_ID,"title","author",Genre.ACTION,2003,300);

    @Mock
    private Repository<Book, Long> bookRepository;
    private BookService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new DefaultBookService(bookRepository);
    }

    @Test
    void createBookShouldDelegateToTheRepositoryAndReturnSavedBook() {
        // Given
        given(bookRepository.save(DUMMY_BOOK)).willReturn(DUMMY_BOOK);

        // When
        final Book actual = underTest.createBook(DUMMY_BOOK);

        // Then
        assertThat(actual, equalTo(DUMMY_BOOK));
        verify(bookRepository).save(DUMMY_BOOK);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void retrieveBookByIdShouldDelegateToTheRepositoryAndReturnFoundBook() {
        // Given
        given(bookRepository.getById(DUMMY_BOOK_ID)).willReturn(Optional.of(DUMMY_BOOK));

        // When
        final Optional<Book> actual = underTest.retrieveBookById(DUMMY_BOOK_ID);

        // Then
        assertThat(actual, equalTo(Optional.of(DUMMY_BOOK)));
        verify(bookRepository).getById(DUMMY_BOOK_ID);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void retrieveAllBooksShouldDelegateToTheRepositoryAndReturnAllFoundBooks() {
        // Given
        given(bookRepository.getAll()).willReturn(List.of(DUMMY_BOOK));

        // When
        final List<Book> actual = underTest.retrieveAllBooks();

        // Then
        assertThat(actual, equalTo(List.of(DUMMY_BOOK)));
        verify(bookRepository).getAll();
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void updateBookShouldDelegateToTheRepositoryAndUpdateSelectedBook() {
        // Given
        given(bookRepository.update(DUMMY_BOOK)).willReturn(DUMMY_BOOK);

        // When
        final Book actual = underTest.updateBook(DUMMY_BOOK);

        // Then
        assertThat(actual, equalTo(DUMMY_BOOK));
        verify(bookRepository).update(DUMMY_BOOK);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void deleteBookByIdShouldDelegateToTheRepositoryAndDeleteSelectedBookById() {
        // Given

        // When
        underTest.deleteBookById(DUMMY_BOOK_ID);

        // Then
        verify(bookRepository).deleteById(DUMMY_BOOK_ID);
    }

}
