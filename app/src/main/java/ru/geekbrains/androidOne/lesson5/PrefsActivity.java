package ru.geekbrains.androidOne.lesson5;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import ru.geekbrains.androidOne.lesson2.R;
import ru.geekbrains.androidOne.lesson4.BaseActivity;

// 1. Создайте активити с настройками, где включите выбор темы приложения.

public class PrefsActivity extends BaseActivity {

    private SwitchMaterial switchNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);

        // переключатель ночного режима
        switchNightMode = findViewById(R.id.switchNightMode);
        // Восстановление настроек из файла настроек
        loadPrefs(switchNightMode);

        // назначаем обработчик переключателя
        switchNightMode.setOnClickListener(new SwitchMaterial.OnClickListener(){
            @Override
            public void onClick(View v) {
                // сохраним настройки
                savePrefs(switchNightMode);
                // пересоздадим активити, чтобы тема применилась
                if (Build.VERSION.SDK_INT >= 11) {
                    recreate();
                } else {
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        // кнопка возврата в главную активити
        MaterialButton btnReturn = findViewById(R.id.btnReturn);
        // назначаем обработчик нажатия кнопки
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent();
                intentResult.putExtra("NIGHT_MODE", switchNightMode.isChecked());
                setResult(RESULT_OK, intentResult);
                // Метод finish() завершает активити
                finish();
            }
        });
    }
}