package com.mobileapp.mobilelaba2.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobileapp.mobilelaba2.R;
import com.mobileapp.mobilelaba2.ui.dashboard.database.DBManager;
import com.mobileapp.mobilelaba2.ui.dashboard.database.StudentCourses;
import com.mobileapp.mobilelaba2.ui.dashboard.database.StudentsCoursesAdapter;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        DBManager DBManager = new DBManager(requireContext());

        DBManager.addStudent(new StudentCourses("Басаряба І.Ю.", "МПО", "65", "проспект Академіка Глушкова 4д"));
        DBManager.addStudent(new StudentCourses("Яловенко В.В.", "ІТ", "65", "Володимирська 60"));
        DBManager.addStudent(new StudentCourses("Пивко В.М.", "РПЗ", "65", "Володимирська 70"));
        DBManager.addStudent(new StudentCourses("Прилипко Д.С.", "ОУІТП", "70", "проспект Академіка Глушкова 4д"));
        DBManager.addStudent(new StudentCourses("Малишевська К.В.", "ОУІТП", "55", "проспект Академіка Глушкова 4д"));

        List<StudentCourses> studentCourses = DBManager.getAllStudents();
        StudentsCoursesAdapter studentsCoursesAdapter = new StudentsCoursesAdapter(requireContext(), studentCourses);
        recyclerView.setAdapter(studentsCoursesAdapter);

        Button buttonGrade = view.findViewById(R.id.buttonGrade);
        Button buttonAvarage = view.findViewById(R.id.button2);
        Button buttonShowAll = view.findViewById(R.id.button3);
        TextView textViewPercentage = view.findViewById(R.id.studentsPercentage);

        buttonGrade.setOnClickListener(v -> {
            RecyclerView recyclerView1 = view.findViewById(R.id.recyclerViewStudents);
            recyclerView1.setLayoutManager(new LinearLayoutManager(requireContext()));
            List<StudentCourses> studentsGradeMore60 = DBManager.getStudentsAbove60();
            StudentsCoursesAdapter studentsMore60Adapter = new StudentsCoursesAdapter(requireContext(), studentsGradeMore60);
            recyclerView1.setAdapter(studentsMore60Adapter);
        });

        buttonShowAll.setOnClickListener(v -> {
            RecyclerView recyclerView12 = view.findViewById(R.id.recyclerViewStudents);
            recyclerView12.setLayoutManager(new LinearLayoutManager(requireContext()));
            List<StudentCourses> studentsAll = DBManager.getAllStudents();
            StudentsCoursesAdapter studentsAllAdapter = new StudentsCoursesAdapter(requireContext(), studentsAll);
            recyclerView12.setAdapter(studentsAllAdapter);
        });

        buttonAvarage.setOnClickListener(v -> {
            double avarageGrade = DBManager.getAverageGrade();
            textViewPercentage.setText(String.format(Locale.getDefault(), "%.2f", avarageGrade));
        });


        return view;
    }

}