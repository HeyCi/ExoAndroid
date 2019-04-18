package firstapp.heyci.com.firstapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener, OnContactClickListener {

    private Button btnAdd;
    private Button btnLoad;
    private RecyclerView rwContact;
    private ProgressBar progressBar;
    private ArrayList<Contact> contactList;
    private int nbContact = 0;
    private ContactAdapter adapter;
    private MonAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);
        btnAdd = findViewById(R.id.btnAdd);
        btnLoad = findViewById(R.id.btnLoad);
        rwContact = findViewById(R.id.rwContact);
        progressBar = findViewById(R.id.progressBar);

        btnAdd.setOnClickListener(this);
        btnLoad.setOnClickListener(this);

        contactList = new ArrayList<>();

        adapter = new ContactAdapter(contactList);
        adapter.setOnContactClickListener(this);

        rwContact.setAdapter(adapter);
        rwContact.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            Contact contact = new Contact("nom" + nbContact, "prenom" + nbContact);
            contactList.add(0, contact);
            Toast.makeText(this, "Ajout de " + contact.getFirstName(), Toast.LENGTH_SHORT).show();
            nbContact++;
            //adapter.notifyItemInserted(0);
            adapter.notifyDataSetChanged();
        } else if (v == btnLoad) {
            asyncTask = new MonAsyncTask();
            asyncTask.execute();
            progressBar.setVisibility(v.VISIBLE); // ou alors on peut le mettre dans onPreExecute()
        }
}

    @Override
    public void onContactClic(Contact contact, int position) {
        contactList.remove(position);
        contactList.add(0, contact);
        adapter.notifyItemMoved(position, 0);
    }

    @Override
    public void onContactLongClick(Contact contact, int position) {
        contactList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public class MonAsyncTask extends AsyncTask {
        private Contact resultat;
        private Exception exception;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                resultat = WSUtils.loadContactFromWeb();
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(exception != null) {
                Toast.makeText(RecyclerViewActivity.this, "Erreur : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
               contactList.add(0, resultat);
               progressBar.setVisibility(View.GONE);
               adapter.notifyDataSetChanged();
            }
        }
    }
}
