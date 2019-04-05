package firstapp.heyci.com.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd;
    private RecyclerView rwContact;
    private ArrayList<Contact> contactList;
    private int nbContact = 0;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);
        btnAdd = findViewById(R.id.btnAdd);
        rwContact = findViewById(R.id.rwContact);

        btnAdd.setOnClickListener(this);

        contactList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        Contact contact = new Contact("nom" + nbContact, "prenom" + nbContact);
        contactList.add(contact);
        Toast.makeText(this, "Ajout de " + contact.getFirstName(), Toast.LENGTH_SHORT).show();
        nbContact++;
    }
}
