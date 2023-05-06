package com.example.myshop;

public class UpdateActivity extends AppCompatActivity {

    private EditText mCategoryNameEditText;
    private Spinner mCategoryColorSpinner;
    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        // Отримання посилань на елементи форми
        mCategoryNameEditText = findViewById(R.id.edit_text_category_name);
        mCategoryColorSpinner = findViewById(R.id.spinner_category_color);

        // Отримання категорії, яку потрібно відредагувати, з інтенту
        Intent intent = getIntent();
        mCategory = intent.getParcelableExtra("category");

        // Заповнення поля назви категорії
        mCategoryNameEditText.setText(mCategory.getName());

        // Заповнення поля вибору кольору категорії
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.category_colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategoryColorSpinner.setAdapter(adapter);
        int position = adapter.getPosition(mCategory.getColor());
        mCategoryColorSpinner.setSelection(position);
    }

    private void saveChanges() {
        // Отримання нових даних про категорію з полів форми
        String categoryName = mCategoryNameEditText.getText().toString();
        String categoryColor = mCategoryColorSpinner.getSelectedItem().toString();

        // Оновлення даних про категорію в базі даних
        CategoryDatabaseHelper dbHelper = new CategoryDatabaseHelper(this);
        mCategory.setName(categoryName);
        mCategory.setColor(categoryColor);
        dbHelper.updateCategory(mCategory);

        // Повернення до попередньої активності
        setResult(RESULT_OK);
        finish();
    }
}
