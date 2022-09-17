package org.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        var calc = new StringCalculator();
        var br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String input = br.readLine().replace("\\n", "\n");
                if (input.equals("q")) break;
                System.out.println(calc.add(input));
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Input Error");
            }
        }
    }
}