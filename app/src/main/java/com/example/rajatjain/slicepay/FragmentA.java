package com.example.rajatjain.slicepay;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;

public class FragmentA extends android.support.v4.app.Fragment {
    private ListView listview;
    private ArrayList<Items> arraylist=new ArrayList<>();
    private Items data;
    protected View mView;

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment, container, false);
            this.mView = view;
            showContacts();
            if(savedInstanceState!=null){
                this.arraylist=savedInstanceState.getParcelableArrayList("list");
            }
            return view;
        }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.

            ArrayList<Items> contacts = getContactNames();
            Itemsadapter adapter = new Itemsadapter(getContext(), R.layout.row_item,contacts);
            listview=(ListView)mView.findViewById(R.id.list);
            listview.setAdapter(adapter);
            listview.setNestedScrollingEnabled(true);
        }
    }

    private ArrayList<Items> getContactNames() {
        ContentResolver cr = getContext().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null,  ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        String name_arr = new String();
        String[] check = new String[cur.getCount()];
        String phone_arr = new String();
        listview= (ListView) getActivity().findViewById(R.id.list);

        String phone = null;
        int i = 0;
        int j = 0;
        int count=cur.getCount();
        cur.moveToFirst();
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));

                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    name_arr = name;
                    i++;
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        if(!Arrays.asList(check).contains(id)) {
                            check[i]= id;
                            phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            phone_arr = phone;
                            data = new Items(name_arr, phone_arr);
                            arraylist.add(data);
                            j++;
                        }
                    }
                    pCur.close();
                }
            }
        }
        cur.close();
        return arraylist;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(getContext(), "Until you grant the permission, we cannot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            arraylist = savedInstanceState.getParcelableArrayList("list");
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list", arraylist);

    }
}
