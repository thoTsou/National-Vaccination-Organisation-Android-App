package com.example.nationalvaccinationorganisation.ui.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nationalvaccinationorganisation.R;
import com.example.nationalvaccinationorganisation.databinding.FragmentGalleryBinding;
import com.example.nationalvaccinationorganisation.databinding.FragmentQuestionsBinding;

public class QuestionsFragment extends Fragment {

    private QuestionsViewModel questionsViewModel;
    private FragmentQuestionsBinding binding;

    //web view
    private WebView webView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        questionsViewModel =
                new ViewModelProvider(this).get(QuestionsViewModel.class);

        binding = FragmentQuestionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textQuestions;
        questionsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //load html page
        webView = (WebView) root.findViewById(R.id.CustomWebView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/html/questions.html");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}