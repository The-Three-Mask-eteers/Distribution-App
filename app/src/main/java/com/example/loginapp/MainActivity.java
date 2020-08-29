package com.example.loginapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDonateAMask();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCollectAMask();

            }
        });


                ViewPager viewPager = findViewById(R.id.viewPager);

                AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
                pagerAdapter.addFragmet(new LoginFragment());
                pagerAdapter.addFragmet(new RegisterFragment());
                viewPager.setAdapter(pagerAdapter);
            }

    private void openCollectAMask() {
    }

    private void openDonateAMask() {
    }

    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
                private ArrayList<Fragment> fragmentList = new ArrayList<>();

                public AuthenticationPagerAdapter(FragmentManager fm) {
                    super(fm);
                }

                @Override
                public Fragment getItem(int i) {
                    return fragmentList.get(i);
                }

                @Override
                public int getCount() {
                    return fragmentList.size();
                }
        /*String hello = "hello";
       String hello2 = Enc*/

                void addFragmet(Fragment fragment) {
                    fragmentList.add(fragment);
                }
            }
        }
