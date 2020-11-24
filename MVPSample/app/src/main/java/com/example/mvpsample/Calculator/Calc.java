package com.example.mvpsample.Calculator;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *  Call Sample : new Calculator().processStr("5*(5+5)")
 * */
public class Calc {
    private Object c;

    private ArrayList splitTokens(String expr) {
        String[] exp = expr.split("");
        ArrayList tokens = new ArrayList();
        int value = 0;
        String op = "";
        boolean flag = false;
        for (String c : exp) {
            if (c.equals(" ")) {
                continue;
            }

            if ("0123456789".contains(c)) {
                value = value * 10 + Integer.parseInt(c);
                flag = true;
            } else {
                if (flag) {
                    tokens.add(value);
                    value = 0;
                }
                flag = false;
                tokens.add(c);
            }
        }

        if (flag) {
            tokens.add(value);
        }

        return tokens;
    }

    private ArrayList infixToPostfix(ArrayList tokens) {
        ArrayList result = new ArrayList();
        HashMap prec = new HashMap();
        Stack opStack = new Stack();
        prec.put("×", 3);
        prec.put("÷", 3);
        prec.put("+", 2);
        prec.put("−", 2);
        prec.put("(", 1);
        for (Object c : tokens) {
            if (c.equals("(")) {
                opStack.push(c);
            } else if (c.equals(")")) {
                while (!opStack.peek().equals("(")) {
                    Object val = opStack.pop();
                    if (!val.equals("(")) {
                        result.add(val);
                    }
                }
                opStack.pop();
            } else if (prec.containsKey(c)) {
                if (opStack.isEmpty()) {
                    opStack.push(c);
                } else {
                    if((int)prec.get(opStack.peek()) >= (int)prec.get(c)) {
                        result.add(opStack.pop());
                        opStack.push(c);
                    } else {
                        opStack.push(c);
                    }

                }
            } else {
                result.add(c);
            }
        }
        while(!opStack.isEmpty())

        {
            result.add(opStack.pop());
        }
        return result;
    }

    private int postFixEval(ArrayList expr) {
        Stack valueStack = new Stack();
        for(Object c:expr) {
            if(c instanceof Integer) {
                valueStack.push((Integer) c);
            } else if(c.equals("+")) {
                int num1 = (int) valueStack.pop();
                int num2 = (int) valueStack.pop();
                valueStack.push(num2+num1);
            } else if(c.equals("−")) {
                int num1 = (int) valueStack.pop();
                int num2 = (int) valueStack.pop();
                valueStack.push(num2-num1);
            } else if(c.equals("×")) {
                int num1 = (int) valueStack.pop();
                int num2 = (int) valueStack.pop();
                valueStack.push(num2*num1);
            } else if(c.equals("÷")) {
                int num1 = (int) valueStack.pop();
                int num2 = (int) valueStack.pop();
                valueStack.push(num2/num1);
            }
        }

        int result = (int) valueStack.pop();
        return result;
    }

    public int processInt(String expr) {
        ArrayList postfix = infixToPostfix(splitTokens(expr));
        int result = postFixEval(postfix);
        return result;
    }
    public String processStr(String expr) {
        try {
            ArrayList postfix = infixToPostfix(splitTokens(expr));
            String result = postFixEval(postfix) +"";
            return result;
        }catch (Exception e) {
            Log.e(">>>>>>>>>>>>>>>>>",e.getMessage());
            return "error";
        }
    }
}
