package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Characters;
import com.example.demo.entity.Diagnosis_results;
import com.example.demo.entity.Users;
import com.example.demo.form.DiagnosisForm;
import com.example.demo.service.DiagnosisResultsService;
import com.example.demo.service.TestService;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {

	private final UsersService usersService;
	private final TestService testService;
	private final DiagnosisResultsService diagnosisResultsService;

	@GetMapping("/test")
	public String testView() {
		return "diagnosis/tester/index";
	}

	@GetMapping("/test/characters")
	public String testCharacter(Model model) {
		model.addAttribute("characterList", testService.getAllCharacters());
		return "diagnosis/tester/characters";
	}

	@GetMapping("/test/question")
	public String testQuestionsView(@ModelAttribute DiagnosisForm form, Model model) {
		model.addAttribute("map", testService.getQuestionsAndChoices());
		return "diagnosis/tester/question";
	}

	@PostMapping("/test/diagnosis/submit")
	public String testSubmit(@ModelAttribute DiagnosisForm form, Model model) {
		int totalQuestions = testService.getQuestionsAndChoices().size();
		int answeredQuestions = form.getSelects().size();
		if (answeredQuestions < totalQuestions) {
			model.addAttribute("map", testService.getQuestionsAndChoices());
			model.addAttribute("diagnosisForm", form);
			model.addAttribute("error", "すべての質問に回答してください。");
			return "diagnosis/tester/question";
		}

		Users user = usersService.findUserByName(form.getName());
		if (user == null) {
			user = new Users();
			user.setName(form.getName());
			usersService.saveUser(user);
		}

		List<Characters> topCharacters = testService.getTopCharacters(form.getSelects());
		if (topCharacters.size() == 1) {
			Characters resultCharacter = topCharacters.get(0);
			Diagnosis_results diagnosisResult = new Diagnosis_results();
			diagnosisResult.setUserId(user.getId());
			diagnosisResult.setCharacterId(resultCharacter.getId());
			diagnosisResultsService.overwriteDiagnosisResult(diagnosisResult);
			model.addAttribute("character", resultCharacter);
			return "diagnosis/result";
		}
		model.addAttribute("topCharacters", topCharacters);
		model.addAttribute("question", testService.getQuestionById(16));

		List<Integer> topCharacterIds = topCharacters.stream().map(Characters::getId).toList();
		model.addAttribute("choices", testService.getFinalChoices(topCharacterIds));
		model.addAttribute("userId", user.getId());

		return "diagnosis/tester/finalQuestion";
	}
	
	/*------------------------------- -----------------------------*/
	
	@GetMapping("/")
	public String diagnosisView(Model model) {
		return "diagnosis/index";
	}

	@GetMapping("/characters")
	public String charactersView(Model model) {
		model.addAttribute("characterList", testService.getAllCharacters());
		return "diagnosis/characters";
	}

	@GetMapping("/questions")
	public String questionsView(@ModelAttribute DiagnosisForm form, Model model) {
		model.addAttribute("map", testService.getQuestionsAndChoices());
		return "diagnosis/questions";
	}

	@PostMapping("/diagnosis/submit")
	public String submit(@ModelAttribute DiagnosisForm form, Model model) {
		int totalQuestions = testService.getQuestionsAndChoices().size();
		int answeredQuestions = form.getSelects().size();
		if (answeredQuestions < totalQuestions) {
			model.addAttribute("map", testService.getQuestionsAndChoices());
			model.addAttribute("diagnosisForm", form);
			model.addAttribute("error", "すべての質問に回答してください。");
			return "diagnosis/question";
		}

		Users user = usersService.findUserByName(form.getName());
		if (user == null) {
			user = new Users();
			user.setName(form.getName());
			usersService.saveUser(user);
		}

		List<Characters> topCharacters = testService.getTopCharacters(form.getSelects());
		if (topCharacters.size() == 1) {
			Characters resultCharacter = topCharacters.get(0);
			Diagnosis_results diagnosisResult = new Diagnosis_results();
			diagnosisResult.setUserId(user.getId());
			diagnosisResult.setCharacterId(resultCharacter.getId());
			diagnosisResultsService.overwriteDiagnosisResult(diagnosisResult);
			model.addAttribute("character", resultCharacter);
			return "diagnosis/result";
		}
		model.addAttribute("topCharacters", topCharacters);
		model.addAttribute("question", testService.getQuestionById(16));

		List<Integer> topCharacterIds = topCharacters.stream().map(Characters::getId).toList();
		model.addAttribute("choices", testService.getFinalChoices(topCharacterIds));
		model.addAttribute("userId", user.getId());

		return "diagnosis/finalQuestion";
	}
	
	

//	@PostMapping("/test/diagnosis/final")
//	public String finalSubmit(@RequestParam int choiceId, @RequestParam long userId, Model model) {
//		Choices choice = testService.getChoiceById(choiceId);
//		Characters resultCharacter = testService.getCharacterById(choice.getCharacterId());
//		Diagnosis_results diagnosisResult = new Diagnosis_results();
//		diagnosisResult.setUserId(userId);
//		diagnosisResult.setCharacterId(resultCharacter.getId());
//		diagnosisResultsService.overwriteDiagnosisResult(diagnosisResult);
//		model.addAttribute("character", resultCharacter);
//		return "diagnosis/result";
//	}
//
//	@GetMapping("/username")
//	public String usernameView() {
//		return "diagnosis/username";
//	}
}

