package br.com.santander.cxa.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Component
@Retention(RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface CurrencyValue 
{
}
