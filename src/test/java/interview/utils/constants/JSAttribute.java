package interview.utils.constants;



public enum JSAttribute{
	
	SET_VALUE("arguments[0].value='","'");
	
	
	
    

	
	private String attribute;
    private String sulfix;
    JSAttribute(String attribute, String sulfix){
        this.attribute = attribute;
        this.sulfix = sulfix;
    }

    public String getSulfix() {
    	return sulfix;
    }
    
    public String getAttribute(){
        return attribute;
    }
    
}
