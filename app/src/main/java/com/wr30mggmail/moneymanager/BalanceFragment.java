package com.wr30mggmail.moneymanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class BalanceFragment extends Fragment {
    private static final int REQUEST_FRAGMENT = 1;

    public ImageButton changeBalanceButton;

    private TextView balanceText;

    BalanceManager balanceManager = BalanceManager.getInstance();

    public BalanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_balance, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changeBalanceButton = (ImageButton) view.findViewById(R.id.changeBalanceBtn);
        changeBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddBalanceFragment();
            }
        });

        balanceText = (TextView) view.findViewById(R.id.balance_text);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            Double newBalance = Double.parseDouble(data.getStringExtra(AddBalanceDialogFragment.NEW_BALANCE));
            updateBalance(newBalance);
        }
    }

    public void updateBalance(Double newBalance) {
        balanceManager.setBalance(newBalance);
        balanceText.setText(balanceManager.getBalance().toString());
    }

    public void showAddBalanceFragment() {
        FragmentManager fm = getFragmentManager();
        DialogFragment dialogFragment = AddBalanceDialogFragment.newInstance();
        dialogFragment.setTargetFragment(this, REQUEST_FRAGMENT);
        dialogFragment.show(fm, "change_balance_dialog");
    }
}
