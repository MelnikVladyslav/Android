public class RegisterActivity extends AppCompatActivity {
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmPasswordEditText;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstNameEditText = findViewById(R.id.first_name_edit_text);
        mLastNameEditText = findViewById(R.id.last_name_edit_text);
        mEmailEditText = findViewById(R.id.email_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mConfirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
        mRegisterButton = findViewById(R.id.register_button);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstNameEditText.getText().toString().trim();
                String lastName = mLastNameEditText.getText().toString().trim();
                String email = mEmailEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(firstName)) {
                    mFirstNameEditText.setError("Введіть ім'я");
                    return;
                }

                if (TextUtils.isEmpty(lastName)) {
                    mLastNameEditText.setError("Введіть прізвище");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmailEditText.setError("Введіть адресу електронної пошти");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPasswordEditText.setError("Введіть пароль");
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)) {
                    mConfirmPasswordEditText.setError("Підтвердіть пароль");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    mConfirmPasswordEditText.setError("Паролі не співпадають");
                    return;
                }

                String imageBase64 = ""; // отримання фото як Base64,
