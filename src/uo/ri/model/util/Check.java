package uo.ri.model.util;

public class Check {

    public static void isNull(Object o, String errorMsg)
	    throws IllegalStateException {
	isTrue(o == null, errorMsg);
    }

    public static void isNull(Object o) throws IllegalStateException {
	isTrue(o == null,
		o.getClass().getName() + " cannot be null here");
    }

    public static void isNotNull(Object o, String errorMsg)
	    throws IllegalStateException {
	isTrue(o != null, errorMsg);
    }

    public static void isNotNull(Object o)
	    throws IllegalStateException {
	isTrue(o != null,
		o.getClass().getName() + " cannot be null here");
    }

    public static void isFalse(boolean condition)
	    throws IllegalStateException {
	isTrue(!condition, "Invalid assertion");
    }

    public static void isFalse(boolean condition, String errorMsg)
	    throws IllegalStateException {
	isTrue(!condition, errorMsg);
    }

    public static void isTrue(boolean condition)
	    throws IllegalStateException {
	isTrue(condition, "Invalid assertion");
    }

    public static void isTrue(boolean condition, String errorMsg)
	    throws IllegalStateException {
	if (condition == true)
	    return;
	throw new IllegalStateException(errorMsg);
    }

}
