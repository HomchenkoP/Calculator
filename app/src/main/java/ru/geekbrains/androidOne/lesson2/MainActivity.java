package ru.geekbrains.androidOne.lesson2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.geekbrains.androidOne.lesson3.CalculatorModel;
import ru.geekbrains.androidOne.lesson3.Operator;
import ru.geekbrains.androidOne.lesson4.BaseActivity;

// 1. Напишите обработку каждой кнопки из макета калькулятора.
// * Создайте светлую и тёмную тему для приложения.

public class MainActivity extends BaseActivity {

    private final static String PARCELABLE_KEY = "CalculatorModel";

    private CalculatorModel engine;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        engine = new CalculatorModel();
        initView();
        initController();
        display.setText(engine.getEntry());
    }

    private void initView() {
        display = findViewById(R.id.displayView);
    }

    private void initController() {
        // цифровые кнопки
        Button button_dot = findViewById(R.id.button_dot);
        Button button_0 = findViewById(R.id.button_0);
        Button button_1 = findViewById(R.id.button_1);
        Button button_2 = findViewById(R.id.button_2);
        Button button_3 = findViewById(R.id.button_3);
        Button button_4 = findViewById(R.id.button_4);
        Button button_5 = findViewById(R.id.button_5);
        Button button_6 = findViewById(R.id.button_6);
        Button button_7 = findViewById(R.id.button_7);
        Button button_8 = findViewById(R.id.button_8);
        Button button_9 = findViewById(R.id.button_9);

        // назначаем единый обработчик для всех цифровых кнопок
        button_dot.setOnClickListener(digitButtonClickListener);
        button_0.setOnClickListener(digitButtonClickListener);
        button_1.setOnClickListener(digitButtonClickListener);
        button_2.setOnClickListener(digitButtonClickListener);
        button_3.setOnClickListener(digitButtonClickListener);
        button_4.setOnClickListener(digitButtonClickListener);
        button_5.setOnClickListener(digitButtonClickListener);
        button_6.setOnClickListener(digitButtonClickListener);
        button_7.setOnClickListener(digitButtonClickListener);
        button_8.setOnClickListener(digitButtonClickListener);
        button_9.setOnClickListener(digitButtonClickListener);

        // кнопки очистки
        Button button_ac = findViewById(R.id.button_ac);
        Button button_ce = findViewById(R.id.button_ce);
        // кнопки команд
        Button button_percent = findViewById(R.id.button_percent);
        Button button_slash = findViewById(R.id.button_slash);
        Button button_asterisk = findViewById(R.id.button_asterisk);
        Button button_minus = findViewById(R.id.button_minus);
        Button button_plus = findViewById(R.id.button_plus);
        Button button_calc = findViewById(R.id.button_calc);

        // назначаем единый обработчик для кнопок очистки
        button_ac.setOnClickListener(operatorButtonClickListener);
        button_ce.setOnClickListener(operatorButtonClickListener);
        // и кнопок команд
        button_percent.setOnClickListener(operatorButtonClickListener);
        button_slash.setOnClickListener(operatorButtonClickListener);
        button_asterisk.setOnClickListener(operatorButtonClickListener);
        button_minus.setOnClickListener(operatorButtonClickListener);
        button_plus.setOnClickListener(operatorButtonClickListener);
        button_calc.setOnClickListener(operatorButtonClickListener);
    }

    // обработчик нажатия цифровых кнопок
    public View.OnClickListener digitButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            не надо строить логику на значении текстового поля из кнопки
//            Button button = (Button)v;
//            engine.appendEntry(button.getText().toString());
            switch (v.getId()) {
                case R.id.button_dot: engine.appendEntry("."); break;
                case R.id.button_0: engine.appendEntry("0"); break;
                case R.id.button_1: engine.appendEntry("1"); break;
                case R.id.button_2: engine.appendEntry("2"); break;
                case R.id.button_3: engine.appendEntry("3"); break;
                case R.id.button_4: engine.appendEntry("4"); break;
                case R.id.button_5: engine.appendEntry("5"); break;
                case R.id.button_6: engine.appendEntry("6"); break;
                case R.id.button_7: engine.appendEntry("7"); break;
                case R.id.button_8: engine.appendEntry("8"); break;
                case R.id.button_9: engine.appendEntry("9"); break;
            }
            display.setText(engine.getEntry());
        }
    };

    // обработчик нажатий кнопок команд
    public View.OnClickListener operatorButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // очистка
                case R.id.button_ac: engine.allClean(); break;
                case R.id.button_ce: engine.cleanEntry(); break;
                // вычисления
                case R.id.button_percent: engine.take_percentage(); break;
                case R.id.button_slash: engine.perform(Operator.SLASH); break;
                case R.id.button_asterisk: engine.perform(Operator.ASTERISK); break;
                case R.id.button_minus: engine.perform(Operator.MINUS); break;
                case R.id.button_plus: engine.perform(Operator.PLUS); break;
                case R.id.button_calc: engine.perform(Operator.CALC); break;
            }
            display.setText(engine.getEntry());
        }
    };

    // Сохранение данных
    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(PARCELABLE_KEY, engine);
    }

    // Восстановление данных
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        engine = instanceState.getParcelable(PARCELABLE_KEY);
        display.setText(engine.getEntry());
    }
}