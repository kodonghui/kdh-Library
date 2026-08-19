/*
* 의도: 책에 번호·제목·분류를 담는다
*/

package com.kdh.library.entity;

public class Book
{

    private int bookNumber;             // private: 번호·제목·분류는 나만 핸들링 할 수 있도록 한다.
    private String title;
    private Category category;

    public Book(int bookNumber, String title, Category category)
    {
        this.bookNumber = bookNumber;
        this.title = title;
        this.category = category;
    }

    public int getBookNumber() { return bookNumber; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }        // this.title 은 필드, 그냥 title 은 매개변수인데, 이름이 같아 구분이 필요하다.

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }        // 메뉴 5번(수정)에서 활용

    @Override
    public String toString ()
    {
        return bookNumber + " | " + title + " | " + category.getGenre();        //  책 한권에 대한 정보를 한 줄로 표현한다.
    }


}
