package com.example.tradingcompany.config.web;

import com.example.tradingcompany.formatter.CustomDateTimeFormatter;
import com.example.tradingcompany.formatter.CustomDateTimeRangeFormatter;
import com.example.tradingcompany.formatter.DateRangeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    private final CustomDateTimeFormatter customDateTimeFormatter;
    private final CustomDateTimeRangeFormatter customDateTimeRangeFormatter;

    public WebMVCConfig(CustomDateTimeFormatter customDateTimeFormatter, CustomDateTimeRangeFormatter customDateTimeRangeFormatter) {
        this.customDateTimeFormatter = customDateTimeFormatter;
        this.customDateTimeRangeFormatter = customDateTimeRangeFormatter;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles/tiles.xml" });
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(customDateTimeFormatter);
        registry.addFormatter(customDateTimeRangeFormatter);
    }
}
