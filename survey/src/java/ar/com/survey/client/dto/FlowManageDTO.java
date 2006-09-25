package ar.com.survey.client.dto;

public class FlowManageDTO {
	
	private String description;
	private String action;
	private int section;
	
	public FlowManageDTO(String description, String action, int section) {
		this.description = description;
		this.action = action;
		this.section = section;
	}

	public String getAction() {
		return action;
	}

	public String getDescription() {
		return description;
	}

	public int getSection() {
		return section;
	}
	
}
