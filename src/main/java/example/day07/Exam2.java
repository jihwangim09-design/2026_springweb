package example.day07;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.springframework.data.repository.aot.generate.AotRepositoryClassBuilder.ClassCustomizer;

interface Calculator{int plus ( int x , int y ); } // 추상메소드
public class Exam2 {
    public static void main(String[] args) {
        // 1. 구현체: 추상메소드(인터페이스) 구현한 객체
        // 2. 익명구현체: 추상메소드 구현한 클래스 없이 직접(1회성) 구현
        // Calculator calc = new Calculator() { 추상메소드구현};
        Calculator calc = new Calculator() { 
            @Override public int plus( int x , int y ) {return x+y;}
        };
        int result1 = calc.plus(5, 3);
        // 3. 람다표현식 : 인터페이스의 추상메소드를 간편하게 구현
        Calculator calc2 = ( x , y ) -> x + y; // @Override public int plus( int x , int y ) {return x+y;}
        int result2 = calc2.plus(5, 3);
        // ( x , y ) -> { return x + y; }; 원래 이렇게 써야함 
        // 4. 람다표현식 사용하는 인터페이스들
        // ** 제네릭이란? 클래스/인터페이스 안에서 사용할 타입을 정한다.
        // List< MemberDto > , 즉 List에 저장할 자료들의 타입을 MemberDto로 정한다.

        // Function< 인수타입 , 반환타입 > , apply( 인수 ) , 매개변수o / 반환 o
        // Function = 용도: T 타입 입력을 받아 가공/변환 후 R 타입으로 반환 (DTO 변환, 매핑 등)
        Function< Integer , Integer> function = (x) -> {return x * 2;}; // 첫번째가 매개변수 두번째가 반환타입
        System.out.println( function.apply(3)); // 6

        // Supplier< 반환타입 > , get( ) , 매개변수x / 반환o  
        // Supplier = 매개변수 없이 결과값을 공급/생성 (Lazy Evaluation, 기본값 생성 등)
        Supplier< Integer > supplier = ( ) -> { return 2; };
        System.out.println( supplier.get()); 

        // Consumer< 인수타입 > , accept( 인수 ) , 매개변수 o / 반환 x 
        // Consumer = 매개변수를 소비하고 반환값 없음 (출력, 로깅, 상태 변경 등)
        Consumer< String > consumer = (str) -> { System.out.println( str ); };
        consumer.accept("유재석");

        // Predicate<T> 구조: T -> boolean, 메서드: boolean test(T t)
        // Predicate = T 타입 입력을 받아 조건 검사 후 결과를 true/false 반환 (필터링, 유효성 검증)
        Predicate< Integer > predicate = ( x ) -> { return  x % 2 == 0;};
        boolean result4 = predicate.test(3);

        // 활용처: 위 4가지 직접적인 사용이 아닌 API(남이만든 클래스/메서드) 에서 활용가능
        // forEach -> Consumer , map -> Function , filter -> Predicate
        // 즉 스트림API 주로 사용된다. 
    }
}
