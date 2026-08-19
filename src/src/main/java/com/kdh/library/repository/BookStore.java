/*
 * 의도1: 책을 담아 두고, 찾고, 지우는 일만 한다.
 * 의도2: 화면에 찍는 일은 하지 않는다 — 이 파일에 System.out 이 하나도 없다.
 * 의도3: 보여 주는 일은 ConsoleUI 가 맡는다.
 * 의도4: "어떤 책으로 시작하는가" 도 데이터에 관한 결정이라 여기서 정한다.
 */

package com.kdh.library.repository;

import com.kdh.library.entity.Book;
import com.kdh.library.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookStore
{

    private List<Book> shelf = new ArrayList<>();       // 장부 // 왼쪽은 List 인데 오른쪽은 ArrayList로.

    private int nextNumber = 1;         // 다음에 등록될 책에 붙일 번호. 등록할 때마다 1씩 올린다.

    public void add(String title, Category category) {          // 책 한 권을 만듬. 번호는 여기서 붙임.
        shelf.add(new Book(nextNumber, title, category));
        nextNumber++;
    }

    public void loadSampleBooks()               // 책 장부
    {
        add("칼의 노래", Category.LITERATURE);
        add("명상록", Category.HUMANITIES);
        add("정의란 무엇인가", Category.SOCIETY);
        add("부분과 전체", Category.SCIENCE_TECH);
        add("서양미술사", Category.ART);
        add("아주 작은 습관의 힘", Category.PRACTICAL);
    }


    public List<Book> getAllBooks()
    {
        return shelf;
    }           // 메뉴 2번(전체 조회)


    public Book getBookByNumber(int bookNumber)         // 수정(메뉴 5번)과 삭제. 번호가 없으면 null 을 돌려준다.
    {
        for (Book book : shelf)
        {
            if (book.getBookNumber() == bookNumber)
            {
                return book;
            }
        }
        return null;
    }


    public List<Book> findTitleContaining(String keyword)           // 제목에 keyword 가 들어간 책만 골라 새 목록으로 돌려준다.
    {
        return shelf.stream()
                .filter(book -> book.getTitle().contains(keyword))
                .collect(Collectors.toList());
    }


    public List<Book> filterByCategory(Category wanted)         // 메뉴 4번(분류 조회)
    {
        return shelf.stream()
                .filter(book -> book.getCategory() == wanted)
                .collect(Collectors.toList());
    }


    public boolean removeBook(int bookNumber)                     // 지웠으면 true, 그런 번호가 없으면 false
    {
        for (int i = 0; i < shelf.size(); i++)                    //
        {
            if (shelf.get(i).getBookNumber() == bookNumber)
            {
                shelf.remove(i);
                return true;
            }
        }
        return false;
    }


}
