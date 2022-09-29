package edu.sdccd.cisc191.template;

/**
 * Stores the derivatives of sin(x), cos(x), tan(x), and sec(x)
 * as String values.
 */
public interface DerivativesOfTrigFunctions {
    String DERIVATIVE_OF_SINX = "cos(x)";
    String DERIVATIVE_OF_COSX = "-sin(x)";
    String DERIVATIVE_OF_TANX = "sec^2(x)";
    String DERIVATIVE_OF_SECX = "sec(x)tan(x)";
}
