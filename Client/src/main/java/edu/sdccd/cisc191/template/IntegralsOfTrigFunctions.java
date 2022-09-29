package edu.sdccd.cisc191.template;

/**
 * Stores the intervals of sin(x), cos(x), tan(x), and sec(x)
 * as String values.
 */
public interface IntegralsOfTrigFunctions {
    /*
      These values work best in an interface because they are
      constant mathematical values that cannot be changed
     */
    String INTEGRAL_OF_SINX = "-cos(x) + C";
    String INTEGRAL_OF_COSX = "sin(x) + C";
    String INTEGRAL_OF_TANX = "-ln|cos x| + C";
    String INTEGRAL_OF_SECX = "ln|sec x + tan x| + C";
}

