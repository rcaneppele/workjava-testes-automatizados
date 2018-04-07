package br.com.congressodeti.workjava.rh;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BusinessException(String msg) {
		super(msg);
	}

}
