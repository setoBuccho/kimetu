package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Characters;
import com.example.demo.entity.Choices;
import com.example.demo.entity.Questions;
import com.example.demo.repository.CharacterMapper;
import com.example.demo.repository.ChoiceMapper;
import com.example.demo.repository.QuestionMapper;
import com.example.demo.service.TestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
	
	private final CharacterMapper characterMapper;
	private final QuestionMapper questionMapper;
	private final ChoiceMapper choiceMapper;
	
	// →CharactersService
	@Override
	public List<Characters> getAllCharacters() {
		return characterMapper.findAll();
	}
	
	@Override
	public Characters getCharacterById(int id) {
		return characterMapper.findById(id);
	}
	
	// →QuestionsService
	@Override
	public Map<Questions, List<Choices>> getQuestionsAndChoices() {
		Map<Questions, List<Choices>> qcMap = new HashMap<>();
		for (Questions question : questionMapper.findAll()) {
			qcMap.put(question, getChoices(question.getId()));
		}
		return qcMap;
	}
	
	// →QuestionsService　（ChoicesをQuestionsに統合）
	@Override
	public List<Choices> getChoices(int questionId) {
		return choiceMapper.findByQuestionId(questionId);
	}
	
	// →DiagnosisService
	@Override
	public Characters calculateResult(Map<Integer, Integer> selects) {
		if (selects == null || selects.isEmpty()) {
			return null;
		}
		

		// キャラクターIDとその獲得ポイントを記録するマップ
		// Key: characters_id, Value: 獲得ポイント
		Map<Integer, Integer> scoreMap = new HashMap<>();
		
		// 1.選ばれた選択肢ごとにキャラクターのポイントを集計
		for (Integer choiceId : selects.values()) {
			Choices choice = choiceMapper.findById(choiceId);
			
			if (choice != null && choice.getCharacterId() != null) {
				Integer characterId = choice.getCharacterId();
				
				scoreMap.put(characterId, scoreMap.getOrDefault(characterId, 0) + 1);
			}
		}
		
		// 2.最もポイントが高いキャラクターIDを見つける
		Integer bestCharacterId = null;
		int maxSchore = -1;
		
		for (Map.Entry<Integer, Integer> entry : scoreMap.entrySet()) {
			if (entry.getValue() > maxSchore) {
				maxSchore = entry.getValue();
				bestCharacterId = entry.getKey();
			}
		}
		
		// 3.最多得点のキャラクターの情報をDBから取得、して返す
		if (bestCharacterId != null) {
			Characters testchar = characterMapper.findById(bestCharacterId);
			if (testchar == null) {
				System.out.println("ヌルヌルヌルヌル");
			}
			System.out.println(testchar.getFirstName());
			return characterMapper.findById(bestCharacterId);
		}
		
		return null;
	}
	// 有木：15問の回答を集計して、最高得点のキャラクターをすべて取得する。
	// 有木：TestControllerから呼び出され、1位が1人なのか同点なのかを判断するために使う。
	@Override
	public List<Characters> getTopCharacters(Map<Integer, Integer> selects) {

		// 有木：キャラクターIDごとの獲得ポイントを記録する。
		// 有木：KeyがキャラクターID、Valueがそのキャラクターの得点。
		Map<Integer, Integer> scoreMap = new HashMap<>();

		// 有木：ユーザーが選んだ選択肢を1つずつ確認して、対応するキャラクターに1点加える。
		for (Integer choiceId : selects.values()) {
			Choices choice = choiceMapper.findById(choiceId);

			if (choice != null && choice.getCharacterId() != null) {
				Integer characterId = choice.getCharacterId();

				scoreMap.put(
					characterId,
					scoreMap.getOrDefault(characterId, 0) + 1
				);
			}
		}

		// 有木：現在の最高得点を記録する。
		int maxScore = -1;

		// 有木：最高得点になったキャラクターのIDを入れるリスト。
		List<Integer> topCharacterIds = new java.util.ArrayList<>();

		// 有木：キャラクターごとの得点を確認して、最高得点のキャラクターを探す。
		for (Map.Entry<Integer, Integer> entry : scoreMap.entrySet()) {

			// 有木：今までの最高得点より高ければ、今までの候補をリセットしてこのキャラクターを1位にする。
			if (entry.getValue() > maxScore) {
				maxScore = entry.getValue();
				topCharacterIds.clear();
				topCharacterIds.add(entry.getKey());

			// 有木：最高得点と同じなら、同点1位として追加する。
			} else if (entry.getValue() == maxScore) {
				topCharacterIds.add(entry.getKey());
			}
		}

		// 有木：最高得点になったキャラクターIDを使って、DBからキャラクター情報を取得する。
		List<Characters> topCharacters = new java.util.ArrayList<>();

		for (Integer characterId : topCharacterIds) {
			topCharacters.add(characterMapper.findById(characterId));
		}

		// 有木：最高得点のキャラクターを1人または複数人返す。
		return topCharacters;
	}
	

	// 有木：IDを指定して質問を1件取得する。
	// 有木：TestControllerから呼ばれて、16問目の最後の質問を取得する。
	@Override
	public Questions getQuestionById(int id) {
		return questionMapper.findById(id);
	}

	// 有木：同点になったキャラクターだけの最終質問の選択肢を取得する。
	// 有木：TestControllerから呼ばれて、16問目の選択肢を同点キャラだけに絞り込む。
	@Override
	public List<Choices> getFinalChoices(List<Integer> characterIds) {
		return choiceMapper.findFinalChoices(16, characterIds);
	}

	@Override
	public Choices getChoiceById(int id) {
	    return choiceMapper.findById(id);
	}
}
