package TESTE;

import java.lang.reflect.Field;

public class Teste {

	public static void main(String[] args) {
		
		String txt = "EDUAR10";
		
		System.out.println(new Mapper(txt).mapObject(Pessoa.class).toString());
	}
}
