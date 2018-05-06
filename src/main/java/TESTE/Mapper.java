package TESTE;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.Assert;


public class Mapper {

	protected String value;
	
	public Mapper(String value) {
		if (StringUtils.isEmpty(value))
			throw new IllegalArgumentException("A String para conversão deve ser informada.");
		this.value = value;
	}
	
	public <T> T  mapObject(Class<T> toClass) {
        Assert.notNull(toClass, "O Objeto para conversão não pode ser nulo.");
        
        T object = instanceFromClassName(toClass.getName());
        
		for (Field f : toClass.getDeclaredFields())
        {
            Expected s = f.getAnnotation(Expected.class); 
            
            try {
            	FieldUtils.writeField(f, object, convertValueByExpectedType(s.type(), convertString(s.start(), s.size())), true);
			} catch (IllegalAccessException e) {
				// aqui eu tenho que fazer um aviso
				e.printStackTrace();
			}
        }
		
		return object;
	}
	
	private Object convertValueByExpectedType(Class<?> expectedType, String value) {
		
		if (Integer.class.equals(expectedType)) {
			return Integer.parseInt(value);
		}
		
		return value;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T instanceFromClassName(String className) {
		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao instanciar classe do tipo " + className);
		}
	}
	
	private String convertString(int start, int size) {
		String newValue = "";
		
		try {
			newValue = value.substring(start, start + size);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("LOGA QUE NÃO FOI POSSIVEL");
		}
		
		return newValue;
	}
}
