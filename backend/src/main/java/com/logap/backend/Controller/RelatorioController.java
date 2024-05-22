package com.logap.backend.Controller;

import com.logap.backend.Service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/relatorio")
@RestController
public class RelatorioController {
    @Autowired
    RelatorioService relatorioService;
    
    @GetMapping("/criar")
    public byte[] criaRelatorio(){
        return relatorioService.criaRelatorio();
    }
}
