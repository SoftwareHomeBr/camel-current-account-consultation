package br.com.santander.cxa.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.santander.cxa.annotations.CurrencyValue;

public class StringNumberFormatter {
	public static String format (String data)
	{
		Locale locale = new Locale("pt", "BR");
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);
		boolean negative = data.endsWith("-");
		data = data.replaceAll("[+|-]", "");
		double value = Double.parseDouble(data);
		
		if (negative)
			value = - value;
		
		value = value/100;
		return numberFormat.format(value);
	}
	
	public static void formatObject (Object o)
	{
		Field[] fields = o.getClass().getDeclaredFields();
		
		for (Field field: fields)
		{
			if (field.isAnnotationPresent(CurrencyValue.class))
			{
				try {
					Method getMethod = o.getClass().getDeclaredMethod(getGetterName(field.getName()), null);
					String value = (String)getMethod.invoke(o, new Object[] {});
					if (value != null)
					{
						Method setMethod = o.getClass().getDeclaredMethod(getSetterName(field.getName()), String.class);
						setMethod.invoke(o, new Object[] {format(value)});
					}
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}


		private static String getGetterName(String name) {
		return "get" + upperFirstLetter(name);
	}

	private static String getSetterName(String name) {
		return "set" + upperFirstLetter(name);
	}
	
	private static String upperFirstLetter(String name)
	{
		return String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
	}
	
	
}
