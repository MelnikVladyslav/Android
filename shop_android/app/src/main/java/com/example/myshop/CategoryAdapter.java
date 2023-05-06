package com.example.myshop;

public class CategoryAdapter extends ArrayAdapter<Category> {

    private LayoutInflater mInflater;

    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.category_list_item, parent, false);
        }

        Category category = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.text_category_name);
        nameTextView.setText(category.getName());

        Button editButton = convertView.findViewById(R.id.button_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Викликаємо Activity для редагування категорії
                Intent intent = new Intent(getContext(), EditCategoryActivity.class);
                intent.putExtra(EditCategoryActivity.EXTRA_CATEGORY_ID, category.getId());
                getContext().startActivity(intent);
            }
        });

        Button deleteButton = convertView.findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete category")
                        .setMessage("Are you sure you want to delete this category?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Видаляємо категорію з бази даних
                                CategoryDatabase.getInstance(getContext()).getCategoryDao().delete(category);
                                // Оновлюємо список категорій
                                clear();
                                addAll(CategoryDatabase.getInstance(getContext()).getCategoryDao().getAll());
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });

        return convertView;
    }
}

