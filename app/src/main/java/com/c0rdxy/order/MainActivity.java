package com.c0rdxy.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 方便匿名类使用
    private List<menu> menuList;
    private List<menu> menuList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 匹配对应id
        final EditText name = findViewById(R.id.name);
        final RadioGroup gender = findViewById(R.id.gender);
        final CheckBox peppery = findViewById(R.id.peppery);
        final CheckBox seafood = findViewById(R.id.seafood);
        final CheckBox acid = findViewById(R.id.acid);
        SeekBar sb = findViewById(R.id.sb);
        final TextView budget = findViewById(R.id.budget);
        Button search = findViewById(R.id.search);
        final ImageView img = findViewById(R.id.img);
        final Button next = findViewById(R.id.next);


        // 菜单
        menuList = new ArrayList<>();
        menuList.add(new menu("桂林米粉", 12, 1, R.mipmap.guilin));
        menuList.add(new menu("红烧肉", 35, 1, R.mipmap.hongshaorou));
        menuList.add(new menu("麻辣海鲜锅", 66, 2, R.mipmap.malahaixianguo));
        menuList.add(new menu("麻辣香锅", 15, 1, R.mipmap.malaxiangguo));
        menuList.add(new menu("木须肉", 22, 3, R.mipmap.muxurou));
        menuList.add(new menu("清蒸鲈鱼", 32, 2, R.mipmap.qingzhengluyu));
        menuList.add(new menu("水煮鱼", 32, 2, R.mipmap.shuizhuyu));
        menuList.add(new menu("酸辣汤", 28, 3, R.mipmap.suanlatang));
        menuList.add(new menu("酸菜牛肉面", 12, 3, R.mipmap.suncainiuroumian));
        menuList.add(new menu("娃娃菜", 10, 1, R.mipmap.wawacai));

        // 显示滑动条对应的数字
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                budget.setText(seekBar.getProgress() + "");
            }
        });

        // 寻找菜品按钮
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Budget = Integer.parseInt(budget.getText().toString());
                if (Budget != 0 && (peppery.isChecked() != false || seafood.isChecked() != false || acid.isChecked() != false)) {
                    menuList1 = new ArrayList<>();
                    int Peppery = peppery.isChecked() == true ? 1 : 0;
                    int Seafood = seafood.isChecked() == true ? 2 : 0;
                    int Acid = acid.isChecked() == true ? 3 : 0;
                    for (int i = 0; i < menuList.size(); i++) {
                        if (menuList.get(i).getPrice() <= Budget &&
                                (Peppery == menuList.get(i).getFlavour() || Seafood == menuList.get(i).getFlavour() || Acid == menuList.get(i).getFlavour())) {
                            menuList1.add(menuList.get(i));
                        }
                    }
                    Toast.makeText(MainActivity.this, "菜单查询完毕。", Toast.LENGTH_LONG).show();
                    img.setBackgroundResource(R.mipmap.search);
                } else {
                    Toast.makeText(MainActivity.this, "请选餐，谢谢配合。", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 下一个按钮
        next.setOnClickListener(new View.OnClickListener() {
            private int index = 0;
            private String Gender;
            private RadioButton radioButton;

            @Override
            public void onClick(View v) {
                radioButton = findViewById(gender.getCheckedRadioButtonId());
                Gender = radioButton.getText().toString();
                if (menuList1 != null) {
                    if (index < menuList1.size()) {
                        img.setBackgroundResource(menuList1.get(index).getImgPath());
                        Toast.makeText(MainActivity.this, name.getText().toString() + " " + Gender + " 可以吃" + "，" + menuList1.get(index).getPrice() + "元的" + menuList1.get(index).getName(), Toast.LENGTH_LONG).show();
                        index = index + 1;
                    } else {
                        Toast.makeText(MainActivity.this, "没有别的菜啦!!!", Toast.LENGTH_LONG).show();
                        index = 0;
                        menuList1.clear();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "请选餐，谢谢配合。", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
