package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Characters;
import com.example.demo.entity.Choices;
import com.example.demo.entity.Questions;

public interface TestService {
	List<Characters> getAllCharacters();
	Characters getCharacterById(int id);
	
	Map<Questions, List<Choices>> getQuestionsAndChoices();
	List<Choices> getChoices(int questionId);

//	Characters calculateResult(List<Integer> selectedChoiceIds);
	Characters calculateResult(Map<Integer, Integer> selects);
	
	// 有木：同点キャラクターを取得する処理。
	// TestControllerから呼び出され、15問の回答を集計した結果、
	// 最高得点が複数キャラクターになった場合に使用する。
	List<Characters> getTopCharacters(Map<Integer, Integer> selects);
	

	// 有木：IDを指定して質問を1件取得する。
	// 有木：TestControllerから呼ばれて、16問目の最後の質問を取得する。
	Questions getQuestionById(int id);

	// 有木：同点になったキャラクターだけの最終質問の選択肢を取得する。
	// 有木：TestControllerから呼ばれて、16問目の選択肢を絞り込む。
	List<Choices> getFinalChoices(List<Integer> characterIds);
	Choices getChoiceById(int id);
}
