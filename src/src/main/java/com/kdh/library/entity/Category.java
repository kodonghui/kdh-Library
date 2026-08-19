/*
 * 의도1: 책의 분류를 내가 정한 여섯 가지로만 제한하고 외부에서 접근하지 못하게 한다.
 * 의도2: enum으로 설계함으로써 중복의 오류를 대비한다.
 */

package com.kdh.library.entity;

public enum Category
{

    LITERATURE("문학"), HUMANITIES("인문"), SOCIETY("사회"),
    SCIENCE_TECH("과학기술"), ART("예술"), PRACTICAL("실용") ;

    private final String genre;          // private: 밖에서 직접 못 만지게.  // final: 한 번 정하면 안 바뀌게 함

    Category(String genre) { this.genre = genre; }          // 상수 목록의 괄호 안 글자를 받아 genre 에 담기

    public String getGenre() { return genre;}           // 읽기만 열기


    public static Category category_Num (int menuNumber)        // 화면에서 고른 번호(1 ~ 6)를 그에 맞는 Category 상수로 바꿔 준다.
    {
        Category[] values = values();

        if (menuNumber < 1 || values.length < menuNumber)       // 범위 밖 번호면 값을 돌려주지 않고 IllegalArgumentException 을 던진다.
        {
            throw new IllegalArgumentException("1 ~ " + values.length + " 사이의 번호를 입력해주세요.");        // 6이라고 하드코딩 하지 않고 .length 활용해봄
        }

        return values[menuNumber -1];           // 사람이 세는 1번은 배열의 0번이기 때문에 '-1'
    }


}
