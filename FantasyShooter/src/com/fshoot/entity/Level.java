package com.fshoot.entity;

public class Level {

	private int num_of_smallM;
	private int num_of_middleM;
	private int num_of_bigM;
	
	public Level(int num_of_smallM, int num_of_middleM, int num_of_bigM) {
		super();
		this.num_of_smallM = num_of_smallM;
		this.num_of_middleM = num_of_middleM;
		this.num_of_bigM = num_of_bigM;
	}
	
	public boolean hasMoreMonster(){
		boolean result = false;
		
		if(num_of_smallM>0||num_of_middleM>0||num_of_bigM>0){
			result = true;
		}
		
		return result;
	}
	
	// Getter and Setter
	public int getNum_of_smallM() {
		return num_of_smallM;
	}
	public void setNum_of_smallM(int num_of_smallM) {
		this.num_of_smallM = num_of_smallM;
	}
	public int getNum_of_middleM() {
		return num_of_middleM;
	}
	public void setNum_of_middleM(int num_of_middleM) {
		this.num_of_middleM = num_of_middleM;
	}
	public int getNum_of_bigM() {
		return num_of_bigM;
	}
	public void setNum_of_bigM(int num_of_bigM) {
		this.num_of_bigM = num_of_bigM;
	}
	
	
}
