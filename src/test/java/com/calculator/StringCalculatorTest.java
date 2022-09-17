package com.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    void add2() {
        var calc = new StringCalculator();
        assertAll(() -> assertEquals(0, calc.add("")),
                () -> assertEquals(1, calc.add("1")),
                () -> assertEquals(3, calc.add("1,2")),
                () -> assertEquals(6, calc.add("1,2,3,1004")),
                () -> assertEquals(6, calc.add("1,2\n3")),
                () -> assertEquals(15, calc.add("//g\n1,2,3g4g5")),
                () -> assertEquals(17, calc.add("//[*][**][***][****][*****]\n1***3****5**2*****1***1*4")),
                () -> assertEquals(10, calc.add("//[*][ab][a]\n1a2ab3*4")),
                ()-> assertThrows(Exception.class, () -> calc.add("//[da]\n")),
                ()-> assertThrows(Exception.class, () -> calc.add("//[d[sas]dd[asd]asd]\n")),
                ()-> assertThrows(Exception.class, () -> calc.add("1,2,-3,-4")),
                ()-> assertThrows(Exception.class, () -> calc.add("//b\n1,2b-3b-6")),
                ()-> assertThrows(Exception.class, () -> calc.add("//aboba\n1,2aboba-6aboba7")),
                ()-> assertThrows(Exception.class, () -> calc.add("//aboba\n1,2aboba6abobaaboba7")),

                ()-> assertThrows(Exception.class, () -> calc.add("//[d][b]\n12d23b4 5 7d13")));

    }
}