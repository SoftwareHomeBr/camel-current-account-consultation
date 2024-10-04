package br.com.santander.cxa.currentaccount.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SwaggerController {
    @RequestMapping("/swagger-ui")
    public RedirectView redirectToUi() {
    	return new RedirectView("/webjars/swagger-ui/index.html?url=/bff-cxa-current-account/v1/api/swagger&validatorUrl=", true, false);
    }
}