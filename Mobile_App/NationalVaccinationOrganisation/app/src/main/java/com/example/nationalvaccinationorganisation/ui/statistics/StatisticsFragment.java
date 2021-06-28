package com.example.nationalvaccinationorganisation.ui.statistics;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nationalvaccinationorganisation.R;
import com.example.nationalvaccinationorganisation.api.AreaInformation;
import com.example.nationalvaccinationorganisation.api.RetrofitClient;
import com.example.nationalvaccinationorganisation.databinding.FragmentStatisticsBinding;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel statisticsViewModel;
    private FragmentStatisticsBinding binding;

    //list view inside this fragment
    private ListView listView;

    //button and dates inputs inside fragment
    private Button btn;
    private EditText date_to , date_from;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statisticsViewModel =
                new ViewModelProvider(this).get(StatisticsViewModel.class);

        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textStatistics;
        statisticsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        listView = (ListView) root.findViewById(R.id.listViewStatistics);
        btn = (Button) root.findViewById(R.id.StatsButton);

        //call api on button click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call api
                date_from = (EditText)  root.findViewById(R.id.date_from);
                date_to = (EditText)  root.findViewById(R.id.date_to);
                btn.setEnabled(false);
                btn.setText("Παρακαλώ Περιμένετε");
                getStatistics(date_from.getText().toString().trim(),date_to.getText().toString().trim());
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //api call
    private void getStatistics(String date_from , String date_to){
        Call<List<AreaInformation>> call = RetrofitClient.getInstance().getMyApi().getStatistics("Token af10bae09621a1cda7683d0e70cd61b1a870dc42",date_from ,date_to);
        call.enqueue(new Callback<List<AreaInformation>>() {
            @Override
            public void onResponse(Call<List<AreaInformation>> call, Response<List<AreaInformation>> response) {
                List<AreaInformation> infoList = response.body();

                //Creating an String array for the ListView
                String[] info = new String[infoList.size()];

                //looping through all the areas
                for (int i = 0; i < infoList.size(); i++) {
                    int dailydose_1=0 , dailydose_2=0 , totalVaccinnations=0 , daytotal=0;
                    String date = "" ;


                    for(int k = 0; k < infoList.size(); k++){
                        if( infoList.get(i).getReferencedate().equals(infoList.get(k).getReferencedate()) ){
                            dailydose_1 = dailydose_1+infoList.get(k).getDailydose1();
                            dailydose_2 = dailydose_2+infoList.get(k).getDailydose2();
                            totalVaccinnations = totalVaccinnations+infoList.get(k).getTotalvaccinations();
                            daytotal = daytotal+infoList.get(k).getDaytotal();
                        }
                    }


                    for(int l=0 ; l<infoList.get(i).getReferencedate().length() ; l++){
                        if(infoList.get(i).getReferencedate().charAt(l) == 'T' ){
                            break;
                        }
                        date = date + infoList.get(i).getReferencedate().charAt(l);
                    }

                    info[i] = "\nHμερομηνία: "+date+"\n\nEμβόλια : "+daytotal+"\n\nEμβόλια ά δόσης: "+dailydose_1+"\n\nEμβόλια β΄ δόσης: "+dailydose_2+" \n\nΣυνολικά εμβόλια μέχρι σήμερα: "+totalVaccinnations+"\n";
                }

                //remove duplicates
                info = Arrays.stream(info).distinct().toArray(String[]::new);
                Arrays.sort(info);

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, info));

                btn.setText("Ακολουθούν τα στατιστικά στοιχεία");
            }

            @Override
            public void onFailure(Call<List<AreaInformation>> call, Throwable t) {
                //Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}