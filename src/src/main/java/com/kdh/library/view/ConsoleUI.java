/*
 * 의도1: 화면에 찍고 사람 입력을 받는 일만 한다.
 * 의도2: 책이 몇 권인지 모르고 저장도 하지 않음. 그건 BookStore 쪽에서.
 * 의도3: Scanner 는 이 파일에만 둔다. 입력받는 자리를 한 곳으로 모아야 편함 (그냥 제 스타일..)
 * 의도4: "숫자만 입력해주세요" 같은 되묻기를 한 군데서 처리.
 */

package com.kdh.library.view;

import com.kdh.library.entity.Book;
import com.kdh.library.entity.Category;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI 
{

    private Scanner keyboard = new Scanner(System.in);

    public void showMenu() 
    {
        System.out.println();
        System.out.println("===== kdh 도서 관리 =====");
        System.out.println("1. 등록");
        System.out.println("2. 전체 조회");
        System.out.println("3. 제목 검색");
        System.out.println("4. 분류 조회");
        System.out.println("5. 수정");
        System.out.println("6. 삭제");
        System.out.println("7. 종료");
    }

    
    public void showBookList( List<Book> books)         // 전체 조회·제목 검색·분류 조회
    {
        if (books.isEmpty()) 
        {
            System.out.println("표시할 책이 없습니다.");
            return;
        }
        
        for (Book book : books)
        {
            System.out.println(book);
        }
    }


    public void showMessage(String message) { System.out.println(message); }            // 알림



    private String readOneLine()         // 한 줄 읽어 오는 작업.
    {
        if (!keyboard.hasNextLine())          // 더 읽을 줄이 있는지 먼저 확인.
        {
            System.out.println();
            System.out.println("입력이 더 이상 없어 프로그램을 종료합니다.");
            System.exit(0);             // 문제 없이 끝나는 경우 '0' 리턴
        }

        return keyboard.nextLine();
    }

    
    public int inputNumber(String question)         // 숫자를 받을 때까지 되묻기.
    {
        while (true) 
        {
            System.out.println(question);
            String typed = readOneLine();
            try                                         // 글자를 치면 parseInt 가 NumberFormatException 을 던지고, 잡아서 다시 묻는다.
            {
                return Integer.parseInt(typed);
            }
            catch (NumberFormatException e)
            {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }


    public String inputText(String question)        // 검색어
    {
        System.out.println(question);
        return readOneLine();
    }


    public String inputRequiredText(String question)        // 제목
    {
        while (true) {
            System.out.println(question);
            String typed = readOneLine();
            if (!typed.trim().isEmpty())            // 비어 있지 않을 때만 통과.
            {
                return typed.trim();
            }
            System.out.println("빈 값은 넣을 수 없습니다.");
        }
    }


    public Category selectCategory( String question)
    {
        while (true)            // 제대로 고를 때까지 되묻기.
        {
            System.out.println(question);

            int number = 1;
            for (Category category : Category.values())
            {
                System.out.println(number + ". " + category.getGenre());
                number++;
            }

            System.out.println("번호 선택: ");

            String typed = readOneLine();

            try
            {
                return Category.category_Num(Integer.parseInt(typed));
            }
                catch (NumberFormatException e)     // ㄱ 처럼 숫자가 아닌 것을 쳤을 때를 대비.
            {
                System.out.println("숫자만 입력해주세요.");
            }
                catch (IllegalArgumentException e)          // 9 처럼 1 ~ 6 밖의 숫자를 쳤을 때를 대비.
            {
                System.out.println(e.getMessage());
            }
        }
    }
    
    
    
}
