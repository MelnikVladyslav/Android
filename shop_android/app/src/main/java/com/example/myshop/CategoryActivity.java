package com.example.myshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myshop.application.HomeApplication;
import com.example.myshop.contants.Urls;

public class CategoryActivity extends AppCompatActivity {
    private EditText categoryNameEditText;
    private Button addButton;

    ProgressBar progressBar = findViewById(R.id.progress_bar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryNameEditText = findViewById(R.id.categoryNameEditText);
        addButton = findViewById(R.id.addButton);

        // Створити екземпляр Retrofit із базовою URL сервера
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Створити інтерфейс, що описує методи для взаємодії з сервером
        CategoryApi categoryApi = retrofit.create(CategoryApi.class);

        // Створити об'єкт категорії, що містить ім'я нової категорії
        Category newCategory = new Category(categoryName);

        // Відправити запит на додавання категорії із створеним об'єктом категорії
        Call<Void> call = categoryApi.addCategory(newCategory);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Обробка успішної відповіді від сервера
                Toast.makeText(CategoryActivity.this, "Категорію успішно додано!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Обробка помилки від сервера
                Toast.makeText(CategoryActivity.this, "Помилка при додаванні категорії", Toast.LENGTH_SHORT).show();
            }
        });

        EditText categoryNameEditText = findViewById(R.id.category_name);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = categoryNameEditText.getText().toString();
            }
        });

        URL url = new URL(BASE);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        String params = "category_name=" + URLEncoder.encode(categoryName, "UTF-8");

        progressBar.setVisibility(View.VISIBLE);


        OutputStream outputStream = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        writer.write(params);
        int progress = calculateProgress();
        progressBar.setProgress(progress);
        writer.flush();
        writer.close();
        outputStream.close();

        progressBar.setVisibility(View.GONE);


        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            reader.close();
        }


    }
}
