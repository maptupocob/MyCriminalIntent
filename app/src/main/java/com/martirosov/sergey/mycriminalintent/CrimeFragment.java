package com.martirosov.sergey.mycriminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

public class CrimeFragment extends Fragment {
    public static final String ARG_CRIME_ID = "ARG_CRIME_ID";
    private Crime crime;
    private EditText textField;
    private Button dateButton;
    private CheckBox solvedCheckBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        crime = CrimeLab.get(getActivity()).getCrime(id);
    }

    public static CrimeFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, id);
        CrimeFragment cf = new CrimeFragment();
        cf.setArguments(args);
        return cf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        textField = v.findViewById(R.id.crime_title);
        textField.setText(crime.getName());
        textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                crime.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dateButton = v.findViewById(R.id.crime_date);
        dateButton.setText(crime.getDate().toString());
        dateButton.setEnabled(false);

        solvedCheckBox = v.findViewById(R.id.crime_solved);
        solvedCheckBox.setChecked(crime.isSolved());
        solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setSolved(isChecked);
            }
        });

        return v;
    }
}
