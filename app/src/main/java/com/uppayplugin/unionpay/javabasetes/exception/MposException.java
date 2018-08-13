package com.uppayplugin.unionpay.javabasetes.exception;

/**
 * MPOS异常基类
 * @author Administrator
 *
 */
public class MposException extends Exception {

	private static final long serialVersionUID = -3588220000708930148L;
	protected int errorCode ;
	protected String errorMsg ;
	
	public MposException(int errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

    public int getErrorCode() {
        return errorCode;
    }
    public String getErrorMsg() {
        return errorMsg;
    }


	
	
	
}
