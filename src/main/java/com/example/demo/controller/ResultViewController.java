package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Characters;
import com.example.demo.entity.Diagnosis_results;
import com.example.demo.service.CharacterService;
import com.example.demo.service.DiagnosisResultsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResultViewController {

    private final DiagnosisResultsService diagnosisResultsService;
    private final CharacterService characterService;

    // GET /result/{userId} → 診断結果画面を表示
    @GetMapping("/result/{userId}")
    public String showResult(@PathVariable long userId, Model model) {
        List<Diagnosis_results> results =
                diagnosisResultsService.getDiagnosisResultsByUserId(userId);

        // characterId → キャラクター情報
        Map<Integer, Characters> characters = new HashMap<>();
        for (Diagnosis_results r : results) {
            characters.computeIfAbsent(r.getCharacterId(),
                    id -> characterService.getCharacterById(id));
        }

        // 追加：最新の診断結果のキャラクターを result.html 用に渡す
        if (!results.isEmpty()) {
            model.addAttribute("character", characters.get(results.get(0).getCharacterId()));
        }

        model.addAttribute("results", results);
        model.addAttribute("characters", characters);
        return "diagnosis/result";
    }
}