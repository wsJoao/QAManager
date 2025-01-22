package com.jppin.quickaccountmanager.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.jppin.quickaccountmanager.R;
import com.jppin.quickaccountmanager.models.BankAccount;
import com.jppin.quickaccountmanager.models.Transaction;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    BankAccount bankAccount = new BankAccount();
    Transaction transaction = new Transaction();

    public void deposit(View view) {
        try {
            bankAccount.deposit(transaction.getAmount());
            updateStatus("Deposit succeeded");
        } catch (IllegalArgumentException e) {
            updateStatus(e.getMessage());
        } finally {
            transaction.clearAmount();
            updateUI(view);
        }
    }

    public void withdraw(View view) {
        try {
            bankAccount.withdraw(transaction.getAmount());
            updateStatus("Withdrawal succeeded");
        } catch (IllegalArgumentException | IllegalStateException e) {
            updateStatus(e.getMessage());
        } finally {
            transaction.clearAmount();
            updateUI(view);
        }
    }

    public void addMoney50(View view) {
        try {
            transaction.addAmount(50);
            updateStatus("Amount added");
        } catch (IllegalArgumentException e) {
            updateStatus(e.getMessage());
        }
        updateUI(view);
    }

    public void addMoney100(View view) {
        try {
            transaction.addAmount(100);
            updateStatus("Amount added");
        } catch (IllegalArgumentException e) {
            updateStatus(e.getMessage());
        }
        updateUI(view);
    }

    public void catchAmount (View view) {
        TextInputEditText catchAmount = findViewById(R.id.editCatchAmount);
        int textAmount = Integer.parseInt(Objects.requireNonNull(catchAmount.getText()).toString());
        if (textAmount <= 0) {
            updateStatus("Amount must be greater than zero");
            return;
        }
        transaction.addAmount(textAmount);
        catchAmount.getText().clear();
        updateUI(view);

    }

    private void updateStatus(String message) {
        TextView textStatus = findViewById(R.id.textStatus);
        textStatus.setText(message);
    }

    private void updateUI(View view) {
        showAmount(view);
        showBankMoney(view);
    }

    @SuppressLint("SetTextI18n")
    private void showAmount(View view) {
        TextView textAmountOrder = findViewById(R.id.textAmountOrder);
        textAmountOrder.setText("Amount: " + transaction.getAmount() + " R$");
    }

    @SuppressLint("SetTextI18n")
    private void showBankMoney(View view) {
        TextView textMyMoney = findViewById(R.id.textMyMoney);
        textMyMoney.setText("Bank Money: " + bankAccount.getBalance() + " R$");
    }

    public void clearAmount(View view) {
        transaction.clearAmount();
        updateUI(view);

    }
}
