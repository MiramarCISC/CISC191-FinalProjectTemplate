package edu.sdccd.cisc191.template;

/**
 * Stores the intervals of sin(x), cos(x), tan(x), and sec(x)
 * as String values.
 */
public interface IntegralsAndDerivativesOfTrigFunctions {
    /*
      These values work best in an interface because they are
      constant mathematical values that cannot be changed
     */
    String INTEGRAL_OF_SINX = "-cos(x) + C";
    String INTEGRAL_OF_COSX = "sin(x) + C";
    String INTEGRAL_OF_TANX = "-ln|cos x| + C";
    String INTEGRAL_OF_SECX = "ln|sec x + tan x| + C";
    String DERIVATIVE_OF_SINX = "cos(x)";
    String DERIVATIVE_OF_COSX = "-sin(x)";
    String DERIVATIVE_OF_TANX = "sec^2(x)";
    String DERIVATIVE_OF_SECX = "sec(x)tan(x)";

    String[] ANSWERS = {INTEGRAL_OF_SINX, INTEGRAL_OF_COSX, INTEGRAL_OF_TANX,
                        INTEGRAL_OF_SECX, DERIVATIVE_OF_SINX, DERIVATIVE_OF_COSX,
                        DERIVATIVE_OF_TANX, DERIVATIVE_OF_SECX};

    String[] QUESTIONS = {"Integral of sin(x)?", "Integral of cos(x)?", "Integral of tan(x)?",
                        "Integral of sec(x)?", "Derivative of sin(x)?", "Derivative of cos(x)?",
                        "Derivative of tan(x)?", "Derivative of sec(x)?"};

}

