package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Characters;
import com.example.demo.entity.Diagnosis_results;
import com.example.demo.entity.Users;
import com.example.demo.service.CharacterService;
import com.example.demo.service.DiagnosisResultsService;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserListController {

	private final UsersService usersService;                       // ③（仮）
	private final DiagnosisResultsService diagnosisResultsService;
	private final CharacterService characterService;

	// GET /users → ユーザー一覧
	@GetMapping("/users")
	public String list(Model model) {
		List<Users> users = usersService.getAllUsers();           // ③（仮）

		// userId → 最新の診断結果のキャラクター名
		Map<Long, String> characterNames = new HashMap<>();
		Map<Long, String> characterImages = new HashMap<>();
		for (Users u : users) {
			List<Diagnosis_results> results =
					diagnosisResultsService.getDiagnosisResultsByUserId(u.getId());
			if (!results.isEmpty()) {
				Characters c = characterService.getCharacterById(results.get(0).getCharacterId());
				characterNames.put(u.getId(), c.getFamilyName() + " " + c.getFirstName());
				characterImages.put(u.getId(), c.getCharacterImagePath());
			}
		}

		model.addAttribute("users", users);
		model.addAttribute("characterNames", characterNames);
		model.addAttribute("characterImages", characterImages);
		return "diagnosis/users";
	}

	// POST /users/{id}/delete → 削除して一覧に戻る
	@PostMapping("/users/{id}/delete")
	public String delete(@PathVariable long id) {
		usersService.deleteUser(id);                              // ③（仮）
		return "redirect:/users";
	}
	
	@GetMapping("/users/{id}/edit")
	public String editUser(@PathVariable long id, Model model) {
	    Users user = usersService.getUserById(id);

	    List<Diagnosis_results> results =
	            diagnosisResultsService.getDiagnosisResultsByUserId(id);

	    String diagnosisResult = "未診断";

	    if (!results.isEmpty()) {
	        Characters c =
	                characterService.getCharacterById(results.get(0).getCharacterId());

	        if (c != null) {
	            diagnosisResult =
	                    c.getFamilyName() + " " + c.getFirstName();
	        }
	    }

	    model.addAttribute("user", user);
	    model.addAttribute("diagnosisResult", diagnosisResult);

	    return "diagnosis/user/user-edit";
	}
	
	// POST /users/{id}/edit → ユーザー情報を更新
	@PostMapping("/users/{id}/edit")
	public String updateUser(@PathVariable long id, Users user) {

	    user.setId(id);

	    usersService.updateUser(user);

	    return "redirect:/users";
	}
}