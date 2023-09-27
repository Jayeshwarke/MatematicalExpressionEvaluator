package jay;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MathExpressionEvaluatorTest 
{
    /**
     * Rigorous Test :-)
     */
   @Test
    public void testEvaluateExpression() {
        
            // test for addition// this test is failing
        try {
            String result = MathExpressionEvaluator.evaluateExpression("1+2+3+4");
            assertEquals("10", result); 
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }

        
    }

    //test for substraction
    @Test
    public void testEvaluateSubtraction() {
        try {
            String result = MathExpressionEvaluator.evaluateExpression("5 - 3");
            assertEquals("2", result);
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }


    // test for multipilcation
    @Test
    public void testEvaluateMultiplication() {
        try {
            String result = MathExpressionEvaluator.evaluateExpression("3 * 4");
            assertEquals("12", result);
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
        // test for  divison
    @Test
    public void testEvaluateDivision() {
        try {
            String result = MathExpressionEvaluator.evaluateExpression("10 / 2");
            assertEquals("5", result);
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    //test for multiple operator
    @Test
    public void testEvaluateComplexExpression() {
        try {
            String result = MathExpressionEvaluator.evaluateExpression("(5 * 2) + (3 / 2)");
            assertEquals("11.5", result);
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
            // testing for invalid expression
    // @Test
    // public void testEvaluateInvalidExpression() {
    //     try {
    //         String result = MathExpressionEvaluator.evaluateExpression("2 +");
    //         fail("Exception should be thrown for invalid expression");
    //     } catch (Exception e) {
          
    //         assertTrue(e instanceof IllegalArgumentException);
    //     }
    // }
}
