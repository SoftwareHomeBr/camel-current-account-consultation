package br.com.santander.cxa.util;

import java.util.Set;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.spi.ExchangeFormatter;
import org.apache.camel.support.CamelContextHelper;
import org.apache.camel.support.processor.DefaultExchangeFormatter;
import org.apache.camel.util.ObjectHelper;


/**
 * Exemplo de uso:
 * 				.process(e -> {
 *					String dump = MessageHelper.dumpMessageHistoryStacktrace(e, CamelHelper.getOrCreateExchangeFormatter(e.getContext()), false);
 *					System.out.println(dump);
 *				}).id("debuglog:balance")
 * @author X200001
 *
 */
public class CamelHelper {

	private static ExchangeFormatter formatter;

	public static ExchangeFormatter getOrCreateExchangeFormatter(CamelContext camelContext) {
        if (formatter == null) {
            Set<ExchangeFormatter> formatters = camelContext.getRegistry().findByType(ExchangeFormatter.class);
            if (formatters != null && formatters.size() == 1) {
                formatter = formatters.iterator().next();
            } else {
                // setup exchange formatter to be used for message history dump
                DefaultExchangeFormatter def = new DefaultExchangeFormatter();
                def.setShowExchangeId(true);
                def.setMultiline(true);
                def.setShowHeaders(true);
                def.setStyle(DefaultExchangeFormatter.OutputStyle.Fixed);
                try {
                    Integer maxChars = CamelContextHelper.parseInteger(camelContext, camelContext.getGlobalOption(Exchange.LOG_DEBUG_BODY_MAX_CHARS));
                    if (maxChars != null) {
                        def.setMaxChars(maxChars);
                    }
                } catch (Exception e) {
                    throw ObjectHelper.wrapRuntimeCamelException(e);
                }
                formatter = def;
            }
        }
        return formatter;
    }	
	
}
