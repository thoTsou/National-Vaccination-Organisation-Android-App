package com.example.nationalvaccinationorganisation.ui.slideshow;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nationalvaccinationorganisation.MainActivity;
import com.example.nationalvaccinationorganisation.R;
import com.example.nationalvaccinationorganisation.databinding.FragmentSlideshowBinding;
import com.example.nationalvaccinationorganisation.forFirebase.AppointmentInfo;
import com.example.nationalvaccinationorganisation.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    //for sending appointment info
    private Button btn;
    private EditText name , surname , phoneNum , email ,AMKA ;
    private DatabaseReference reff;
    long maxid = 0 ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //FORM for asking for appointment

        reff = FirebaseDatabase.getInstance().getReference().child("appointments");

        btn = (Button) root.findViewById(R.id.submitFormButton);
        name=(EditText) root.findViewById(R.id.name);
        surname=(EditText) root.findViewById(R.id.surname);
        AMKA=(EditText) root.findViewById(R.id.AMKA);
        email=(EditText) root.findViewById(R.id.email);
        phoneNum=(EditText) root.findViewById(R.id.Phone);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(false);
                btn.setText("Επεξεργαζόμαστε το αίτημα σας");
                addAppointment();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //for adding appointment into db
    public void addAppointment(){

        String NAME = name.getText().toString().trim();
        String SURNAME = surname.getText().toString().trim();
        String Amka = AMKA.getText().toString().trim();
        String PhoneNum = phoneNum.getText().toString().trim();
        String Email = email.getText().toString().trim();

        String id = reff.push().getKey();

        AppointmentInfo appointmentInfo = new AppointmentInfo();

        appointmentInfo.setAppointment_id(id);
        appointmentInfo.setName(NAME);
        appointmentInfo.setSurname(SURNAME);
        appointmentInfo.setAMKA(Amka);
        appointmentInfo.setPhoneNum(PhoneNum);
        appointmentInfo.setEmail(Email);

        reff.child(String.valueOf(maxid+1)).setValue(appointmentInfo);

        btn.setText("Το αίτημα σας καταχωρήθηκε,Ευχαριστούμε!");
    }

}