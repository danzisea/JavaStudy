package nested;

import java.util.Optional;

public class NestedClassTest {

	class Outer {
	    Nested nested;
	    Nested getNested() {
	        return nested;
	    }
	    public Outer(){
	    	nested = new Nested();
	    }
	}
	
	class Nested {
	    Inner inner;
	    Inner getInner() {
	        return inner;
	    }
	    public Nested() {
	    	inner = new Inner();
	    }
	}
	
	class Inner {
	    String foo = "Jerry";
	    String getFoo() {
	        return foo;
	    }
	}
	
	public void test1(){
		Outer outer = new Outer();
		if (outer != null && outer.nested != null && outer.nested.inner != null) {
		    System.out.println(outer.nested.inner.foo);
		}
	}
	
	public void test2(){
		Optional.of(new Outer()).map(Outer::getNested).map(Nested::getInner).map(Inner::getFoo)
			.ifPresent(System.out::println);
	}
	
	public static void print(Integer integer) {
		if(integer != null) {
			System.out.println("value: " + integer.intValue());
		}
	}
	public static void main(String[] args) {
		Integer integer = null;
		print(integer);
		
		integer = Integer.valueOf(1);
		print(integer);
	}
}
