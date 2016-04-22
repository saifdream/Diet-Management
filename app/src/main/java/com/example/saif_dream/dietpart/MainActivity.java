package com.example.saif_dream.dietpart;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.saif_dream.dietpart.fragment.DietAddFragment;
import com.example.saif_dream.dietpart.fragment.DietChatFragment;
import com.example.saif_dream.dietpart.fragment.DietListFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("Add Diet");
        fragmentTransaction.add(R.id.dietFragmentView, new DietChatFragment(),"Diet Chart");
        fragmentTransaction.commit();
    }
}
