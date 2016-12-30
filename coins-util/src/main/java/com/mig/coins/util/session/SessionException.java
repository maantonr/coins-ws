package com.mig.coins.util.session;

// PDTE Documentar
public class SessionException extends Exception {

	private static final long serialVersionUID = -9193964448777738993L;

	public SessionException()
	{
		super();
	}

	public SessionException(Exception e)
	{
		super(e);
	}

	public SessionException(String s)
	{
		super(s);
	}

	public SessionException(Thread thread, String s)
	{
		super("Thread: " + thread.toString() + ": " + s);
	}

}
