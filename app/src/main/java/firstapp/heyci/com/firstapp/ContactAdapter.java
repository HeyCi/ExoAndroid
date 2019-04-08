package firstapp.heyci.com.firstapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<Contact> contactList;

    public ContactAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.row_contact, vg, false);
        return new ContactAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.firstName.setText(contact.getFirstName());
        holder.lastName.setText(contact.getLastName());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstName;
        TextView lastName;
        ImageView imgContact;

        public ViewHolder(View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            imgContact = itemView.findViewById(R.id.imgContact);
        }
    }
}
