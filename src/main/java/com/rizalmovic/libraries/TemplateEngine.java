package com.rizalmovic.libraries;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

public class TemplateEngine {
    private static PebbleEngine engine;
    private TemplateEngine() {}

    public static synchronized PebbleEngine getInstance(ServletContext context) {
        if(engine == null) {
            ServletLoader loader = new ServletLoader(context);
            loader.setPrefix("/WEB-INF/resources/");
            loader.setSuffix(".html");
            engine = new PebbleEngine
                .Builder()
                .loader(loader)
                .build();
        }

        return engine;
    }

    public static String render(String template) throws IOException {
        Writer writer = new StringWriter();
        PebbleTemplate compiledTemplate = engine.getTemplate(template);
        compiledTemplate.evaluate(writer);
        return writer.toString();
    }

    public static String render(String template, Map<String, Object> data) throws IOException {
        Writer writer = new StringWriter();
        PebbleTemplate compiledTemplate = engine.getTemplate(template);
        compiledTemplate.evaluate(writer, data);
        return writer.toString();
    }
}