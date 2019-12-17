package it.mandese.carrello.config;

import it.mandese.carrello.converters.CartConverter;
import it.mandese.carrello.converters.CartItemConverter;
import it.mandese.carrello.converters.CartItemDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class WebConfig implements WebMvcConfigurer
{

  @Autowired
  ApplicationContext applicationContext;

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new CartConverter(applicationContext));
    registry.addConverter(new CartItemConverter());
    registry.addConverter(new CartItemDtoConverter());
  }
}
