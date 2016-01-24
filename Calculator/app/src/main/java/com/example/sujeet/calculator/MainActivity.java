package com.example.sujeet.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public static double eval(final String str) {
        class Parser {
            int pos = -1, c;

            void eatChar() {
                c = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            void eatSpace() {
                while (Character.isWhitespace(c)) eatChar();
            }

            double parse() {
                eatChar();
                double v = parseExpression();
                if (c != -1) throw new RuntimeException("Unexpected: " + (char)c);
                return v;
            }



            double parseExpression() {
                double v = parseTerm();
                for (;;) {
                    eatSpace();
                    if (c == '+') { // addition
                        eatChar();
                        v += parseTerm();
                    } else if (c == '-') { // subtraction
                        eatChar();
                        v -= parseTerm();
                    } else {
                        return v;
                    }
                }
            }

            double parseTerm() {
                double v = parseFactor();
                for (;;) {
                    eatSpace();
                    if ((c == '/')||(c =='%')) { // division
                        eatChar();
                        v /= parseFactor();
                    } else if (c == '*' || c == '(') { // multiplication
                        if (c == '*') eatChar();
                        v *= parseFactor();
                    } else {
                        return v;
                    }
                }
            }

            double parseFactor() {
                double v;
                boolean negate = false;
                eatSpace();
                if (c == '+' || c == '-') { // unary plus & minus
                    negate = c == '-';
                    eatChar();
                    eatSpace();
                }
                if (c == '(') { // brackets
                    eatChar();
                    v = parseExpression();
                    if (c == ')') eatChar();
                } else { // numbers
                    StringBuilder sb = new StringBuilder();
                    while ((c >= '0' && c <= '9') || c == '.') {
                        sb.append((char)c);
                        eatChar();
                    }
                    if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)c);
                    v = Double.parseDouble(sb.toString());
                }
                eatSpace();
                if (c == '^') { // exponentiation
                    eatChar();
                    v = Math.pow(v, parseFactor());
                }
                if (negate) v = -v; // unary minus is applied after exponentiation; e.g. -3^2=-9
                return v;
            }
        }
        return new Parser().parse();
    }
















    public String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length() - 1);
    }





    String test="";

    public void onclick(View v) {



            int c = 0;




            Boolean pl = false, mi = false, dv = false, mu = false;

            TextView t1 = (TextView) findViewById(R.id.sum);
            int d = v.getId();
            System.out.println(d);
        try {
            // c=Integer.parseInt(e1.getText().toString()) +Integer.parseInt(e2.getText().toString());
            //  t1.setText(Integer.toString(c));
            switch (v.getId()) {
                case R.id.one:
                    test = test + "1";
                    t1.setText(test);
                    break;
                case R.id.two:
                    test = test + "2";
                    t1.setText(test);
                    break;
                case R.id.three:
                    test = test + "3";
                    t1.setText(test);
                    break;
                case R.id.four:
                    test = test + "4";
                    t1.setText(test);
                    break;
                case R.id.five:
                    test = test + "5";
                    t1.setText(test);
                    break;
                case R.id.six:
                    test = test + "6";
                    t1.setText(test);
                    break;
                case R.id.seven:
                    test = test + "7";
                    t1.setText(test);
                    break;
                case R.id.eight:
                    test = test + "8";
                    t1.setText(test);
                    break;
                case R.id.nine:
                    test = test + "9";
                    t1.setText(test);
                    break;
                case R.id.mipl:
                    /*
                    double x = Double.parseDouble(test);
                    x = -1 * x;

                    test = Double.toString(x);
                    */
                    if(test.substring(test.length()-1)=="-")
                   test=test.substring(0,test.length()-2)+test.substring(test.length());
                   else
                        test=test.substring(0,test.length()-1)+"-"+test.substring(test.length());
                    t1.setText(test);
                    break;
                case R.id.plus:

                    // c=c+Integer.parseInt(test);
                    //   int op1=Integer.parseInt(test);

                    test = test + "+";
                    t1.setText(test);
                    break;

                case R.id.minus:
                    test = test + "-";
                    t1.setText(test);
                    break;
                case R.id.multi:
                    test = test + "*";
                    t1.setText(test);
                    break;
                case R.id.point:
                    test = test + ".";
                    t1.setText(test);
                    break;

                case R.id.div:
                    test = test + "/";
                    t1.setText(test);
                    break;

                case R.id.zero:
                    test = test + "0";
                    t1.setText(test);
                    break;
                case R.id.br1:
                    test = test + "(";
                    t1.setText(test);
                    break;
                case R.id.br2:
                    test = test + ")";
                    t1.setText(test);
                    break;
                case R.id.per:
                    test = test + "%";
                    t1.setText(test);
                    break;

                case R.id.power:
                    test = test + "^";
                    t1.setText(test);
                    break;
                case R.id.cancel:
                    test = "";
                    t1.setText(test);
                    break;
                case R.id.delete:
                    String aaaa = removeLastChar(test);
                    test = aaaa;
                    t1.setText(test);
                    break;
                case R.id.eq:

                    double ans = eval(test);
                    test = Double.toString(ans);
                    t1.setText(Double.toString(ans));
                    String[] a = test.split("");
                    //  ArrayList<String> ar =new ArrayList<String>();

                    //    int ans=Integer.parseInt(test);

                    break;


            }


        } catch (Exception e) {

           // t1.setText(#f40c0c);


            t1.setText("Invalid Operation");
            test="";
        }
    }

}







