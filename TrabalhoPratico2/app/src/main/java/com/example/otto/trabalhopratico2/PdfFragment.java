package com.example.otto.trabalhopratico2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PdfFragment extends Fragment {
    TextView textView;
    String subjectName, fileType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        subjectName = this.getArguments().getString("subjectName");
        fileType = this.getArguments().getString("fileType");
        getActivity().setTitle(subjectName + " - " + fileType);
        return inflater.inflate(R.layout.fragment_pdf, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        textView = view.findViewById(R.id.textViewFragment);
        textView.setText(subjectName + " " + fileType);
    }
}
