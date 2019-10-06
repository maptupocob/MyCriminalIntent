package com.martirosov.sergey.mycriminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CrimeListFragment extends Fragment {
    private CrimeAdapter crimeAdapter;
    private RecyclerView crimeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (crimeAdapter == null) {
            crimeAdapter = new CrimeAdapter(crimes);
            crimeRecyclerView.setAdapter(crimeAdapter);
        } else {
            crimeAdapter.notifyDataSetChanged();
        }

    }

    private class CrimeHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dateTextView;
        private ImageView solvedImageView;
        private Crime crime;
//        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.ROOT);

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            titleTextView = itemView.findViewById(R.id.crime_title);
            dateTextView = itemView.findViewById(R.id.crime_date);
            solvedImageView = itemView.findViewById(R.id.crime_solved);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getActivity(), crime.getName() + " clicked!!!", Toast.LENGTH_SHORT).show();
                    startActivity(CrimeActivity.newIntent(getActivity(), crime.getId()));
                }
            });
        }

        public void bind(Crime crime) {

            this.crime = crime;
            titleTextView.setText(this.crime.getName());
            dateTextView.setText(this.crime.getDate().toString());
            solvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.INVISIBLE);
        }


    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder crimeHolder, int i) {
            crimeHolder.bind(crimes.get(i));
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
