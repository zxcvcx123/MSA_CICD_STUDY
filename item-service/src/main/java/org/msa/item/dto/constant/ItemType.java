package org.msa.item.dto.constant;

public enum ItemType {
	
	FOOD("F", "음식"),
	CLOTHES("C", "옷");
	
	private String cd;
	private String desc;
	
	/**
	 *	생성자 
	 */
	ItemType(String cd, String desc){
		this.cd = cd;
		this.desc = desc;
	}
	
	/**
	 *	아이템 코드로 검증
	 */
	public boolean hasItemCd(String cd) {
		return this.cd.equals(cd);
	}

}
