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

public class UploadActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Додати обробник події для кнопки вибору файлу
        Button chooseFileButton = findViewById(R.id.choose_file_button);
        chooseFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Створити інтент для вибору файлу
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*"); // Дозволяє вибрати будь-який тип файлу

                // Запустити інтент для вибору файлу і очікувати результат
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });

        // Додати обробник події для кнопки відправлення файлу на сервер
        Button uploadButton = findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Відправити файл на сервер
                uploadFile(fileUri);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Отримати Uri обраного файлу
            fileUri = data.getData();

            // Показати ім'я файлу на екрані
            TextView fileNameTextView = findViewById(R.id.file_name_text_view);
            fileNameTextView.setText(getFileName(fileUri));
        }
    }

    private void uploadFile(Uri fileUri) {
        // Використати Retrofit для відправлення файлу на сервер
        // Детальніше про відправлення файлів можна дізнатися з документації Retrofit
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
