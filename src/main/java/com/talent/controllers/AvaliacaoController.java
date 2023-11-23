package com.talent.controllers;

import com.talent.dto.AvaliacaoDTO;
import com.talent.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController  {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/{idInstituicao}")
    public ResponseEntity<Object> findByInstituicao(@PathVariable UUID idInstituicao) {
        return avaliacaoService.findByInstituicao(idInstituicao);
    }


    @PostMapping
    public ResponseEntity<Object> saveAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        return avaliacaoService.saveAvaliacao(avaliacaoDTO);
    }
}
