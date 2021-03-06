package apps.jayceleathers.me.spectrum.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.parse.ParseUser;

import apps.jayceleathers.me.spectrum.Activities.LaunchActivity;
import apps.jayceleathers.me.spectrum.Data.SpectrumUser;
import apps.jayceleathers.me.spectrum.R;


public class IdentifyFragment extends Fragment {

    private SeekBar sbGenderBiological;
    private SeekBar sbGenderIdentity;
    private SeekBar sbGenderExpression;
    private SeekBar sbSexualIdentity;

    private int genderBiological = 0;
    private int genderExpression = 0;
    private int genderIdentity = 0;
    private int sexualIdentity = 0;

    public IdentifyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container = (ViewGroup) inflater.inflate(R.layout.fragment_identify, container, false);

        registerSeekbars(container);

        Button btnSave = (Button) container.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpectrumUser user = (SpectrumUser) ParseUser.getCurrentUser();

                user.setGenderBiological(genderBiological);
                user.setGenderExpression(genderExpression);
                user.setGenderIdentity(genderIdentity);
                user.setSexualIdentity(sexualIdentity);
                user.saveInBackground();
                SharedPreferences sp = getActivity().getSharedPreferences(LaunchActivity.SP_DATA, LaunchActivity.MODE_PRIVATE);
                FragmentManager fm = getActivity().getFragmentManager();
                if(sp.getBoolean(LaunchActivity.KEY_FIRST_RUN, true)){
                    DescriptionFragment newFragment = new DescriptionFragment();
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.container, newFragment);
                    transaction.commit();
                }
                else {
                    fm.popBackStack();
                }
            }
        });
        return container;
    }

    private void registerSeekbars(ViewGroup container){
        sbGenderBiological = (SeekBar) container.findViewById(R.id.sbGenderBiological);
        sbGenderIdentity = (SeekBar) container.findViewById(R.id.sbGenderIdentity);
        sbGenderExpression = (SeekBar) container.findViewById(R.id.sbGenderExpression);
        sbSexualIdentity = (SeekBar) container.findViewById(R.id.sbSexualIdentity);

        sbGenderBiological.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
               genderBiological  = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // nothing needed
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //nothing needed
            }
        });

        sbGenderIdentity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                genderIdentity  = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // nothing needed
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //nothing needed
            }
        });

        sbGenderExpression.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                genderExpression  = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // nothing needed
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //nothing needed
            }
        });

        sbSexualIdentity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                sexualIdentity  = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // nothing needed
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //nothing needed
            }
        });
    }
}
