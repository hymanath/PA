package com.itgrids.partyanalyst.dto;


/**
 * @author Hyma
 *
 */
public class KeyValuePairVO {
	private Long id;
	private String name;
	
	public KeyValuePairVO(Long id,String name){
		this.id=id;
		this.name=name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj){
		if(id==null)
			id = -1L;
		if(obj instanceof KeyValuePairVO){
			KeyValuePairVO vo = (KeyValuePairVO) obj;
			return this.id.equals(vo.getId());
		}
		return false;
	}
	
	
}
