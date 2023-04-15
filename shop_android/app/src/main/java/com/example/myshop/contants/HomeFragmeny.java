public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Порожній конструктор, необхідний для фрагментів
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Надути макет для фрагменту
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }
}