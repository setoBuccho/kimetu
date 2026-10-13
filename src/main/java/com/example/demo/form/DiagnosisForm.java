package com.example.demo.form;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisForm {
//	private List<Integer> selectedChoiceIds;
	   private String name;

	private Map<Integer, Integer> selects = new HashMap<>();
	
//	public List<Integer> getSelectedChoiceIds() { return selectedChoiceIds; }
//	
//	public void setSelectedChoiceIds(List<Integer> selectedChoiceIds) {
//		this.selectedChoiceIds = selectedChoiceIds;
//	}
}
