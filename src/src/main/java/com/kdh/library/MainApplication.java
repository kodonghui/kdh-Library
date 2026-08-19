/// 의도 1: main에서는 호출만 하고, method와 데이터 저장은 나눠서 서로 다른 파일에서 실행되도록 설계했습니다.
/// 의도 2: 기능이 다르면 서로 다른 클래스나 메소드를 활용하는 편이 편해서, 그와 같이 설계하였습니다.

///==================================================================//
/// 코드 스타일: 중괄호 사이의 내용이 두 줄 이상 들어가야 할 경우,
/*
 method(){

 }
*/

// 과 같은 스타일보다,

/*
method()
   {

   }
*/

/// 와 같은 형식이 보기에 편해서 이와 같은 방식으로 코딩했습니다.
///==================================================================//

package com.kdh.library;

import com.kdh.library.entity.Book;
import com.kdh.library.entity.Category;
import com.kdh.library.repository.BookStore;
import com.kdh.library.view.ConsoleUI;

public class MainApplication
{

    public static void main(String[] args)
    {

        BookStore store = new BookStore();
        ConsoleUI ui = new ConsoleUI();

        store.loadSampleBooks();

        while (true)            // 메뉴를 계속 다시 보여 주는 고리. 7번(종료)에서 return 으로 빠져나갈 때까지 돌도록.
        {
            ui.showMenu();
            int choice = ui.inputNumber("번호 선택: ");

            if (choice == 1)
            {
                addBook(store, ui);
            }

                else if (choice == 2)
                {
                ui.showBookList(store.getAllBooks());
                }

                else if (choice == 3)
                {
                searchTitle(store, ui);
                }

                else if (choice == 4)
                {
                showByCategory(store, ui);
                }

                else if (choice == 5)
                {
                editBook(store, ui);
                }

                else if (choice == 6)
                {
                deleteBook(store, ui);
                }

                else if (choice == 7)
                {
                ui.showMessage("프로그램을 종료합니다.");

                return;
                }

                else
                {
                ui.showMessage("1 ~ 7 사이의 번호를 입력해주세요.");
                }
        }
    }

    private static void addBook(BookStore store, ConsoleUI ui)      // 등록. 제목과 분류를 묻고(ui) 저장(store)
    {
        String title = ui.inputRequiredText("제목: ");
        Category category = ui.selectCategory("분류를 선택하세요.");
        store.add(title, category);
        ui.showMessage("등록되었습니다.");
    }

    private static void searchTitle(BookStore store, ConsoleUI ui)      // 제목 검색.
    {
        String keyword = ui.inputText("검색할 제목: ");
        ui.showBookList(store.findTitleContaining(keyword));
    }

    private static void showByCategory(BookStore store, ConsoleUI ui)       // 분류 조회.
    {
        Category category = ui.selectCategory("조회할 분류를 선택하세요.");
        ui.showBookList(store.filterByCategory(category));
    }

    private static void editBook(BookStore store, ConsoleUI ui)         // 수정. 번호로 책을 찾고, 그런 번호가 없으면 알리고 끝냄.
    {
        int bookNumber = ui.inputNumber("수정할 책 번호: ");
        Book book = store.getBookByNumber(bookNumber);

        if (book == null)
        {
            ui.showMessage("해당 번호의 책이 없습니다.");
            return;
        }

        String title = ui.inputRequiredText("새 제목: ");
        Category category = ui.selectCategory("새 분류를 선택하세요.");
        book.setTitle(title);
        book.setCategory(category);
        ui.showMessage("수정되었습니다.");
    }

    private static void deleteBook(BookStore store, ConsoleUI ui)           // 삭제. removeBook 이 돌려주는 true / false 로 알림 문구
    {
        int bookNumber = ui.inputNumber("삭제할 책 번호: ");
        if (store.removeBook(bookNumber)) {
            ui.showMessage("삭제되었습니다.");
        } else {
            ui.showMessage("해당 번호의 책이 없습니다.");
        }
    }
}

