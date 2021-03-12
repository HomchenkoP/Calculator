package ru.geekbrains.androidOne.lesson3;

import java.math.BigDecimal;
import java.math.MathContext;

// 2. Создайте объект с данными и операциями калькулятора.
//    Продумайте, каким образом будете хранить введённые пользователем данные.

public class CalculatorModel {

    private final int ENTRY_CAPACITY = 10;
    private StringBuilder entry;
    private BigDecimal result;
    private Operator lastOperator;
    private boolean start;

    public CalculatorModel() {
        allClean();
    }

    public void allClean() {
        this.entry = new StringBuilder("0");
        this.entry.ensureCapacity(ENTRY_CAPACITY);
        this.result = BigDecimal.ZERO;
        this.lastOperator = Operator.CALC; // "="
        start = false;
    }

    public void cleanEntry() {
        if (this.entry.length() > 1) {
            // удаляем крайнюю правую цифру из строки ввода
            this.entry.deleteCharAt(this.entry.length() - 1);
        } else {
            // удаляем последнюю цифру из строки ввода
            this.entry = new StringBuilder("0");
        }
    }

    public void appendEntry(String input) {
        if (start) {
            this.entry = new StringBuilder("0");
            start = false;
        }
        if (this.entry.length() < ENTRY_CAPACITY) {
            if (input.equals(".") && this.entry.indexOf(".") == -1) {
                // добавляем точку в конец строки ввода
                this.entry.append(".");
            } else if (this.entry.toString().equals("0")) {
                // заменяем "0" на введеную цифру
                this.entry = new StringBuilder(input);
            } else {
                // добавляем цифру в конец строки ввода
                this.entry.append(input);
            }
        }
    }

    public void take_percentage() {
        BigDecimal percentage = result.divide(new BigDecimal(100)).multiply(new BigDecimal(this.entry.toString()));
        this.entry = new StringBuilder(percentage.toString());
    }

    public void perform(Operator operator) {
        if (start) {
            lastOperator = operator;
        } else {
            calculate(new BigDecimal(this.entry.toString()));
            lastOperator = operator;
            start = true;
        }
    }

    private void calculate(BigDecimal x) {
        switch (lastOperator) {
            case PERCENT: break;
            case SLASH: result = (x.compareTo(BigDecimal.ZERO) == 0) ? BigDecimal.ZERO : result.divide(x, MathContext.DECIMAL32); break;
            case ASTERISK: result = result.multiply(x); break;
            case MINUS: result = result.subtract(x); break;
            case PLUS: result = result.add(x); break;
            case CALC: result = x; break;
        }
        if (result.compareTo(BigDecimal.ZERO) == 0) {
            result = BigDecimal.ZERO;
        }
        if (result.toString().length() > ENTRY_CAPACITY) {
            this.entry = new StringBuilder(result.toString().substring(0, ENTRY_CAPACITY));
        } else {
            this.entry = new StringBuilder(result.toString());
        }
    }

    public String getEntry() {
        return entry.toString();
    }
}
