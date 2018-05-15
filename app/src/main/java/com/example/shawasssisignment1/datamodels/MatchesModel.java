package com.example.shawasssisignment1.datamodels;

import com.example.shawasssisignment1.model.Matches;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.function.Consumer;

public class MatchesModel {

    private DatabaseReference mDatabase;
    private HashMap<DatabaseReference, ValueEventListener> listeners;

    public MatchesModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listeners = new HashMap<>();
    }

    public void addMatchesItem(Matches item) {
        DatabaseReference matchesItemsRef = mDatabase.child("matches");
        matchesItemsRef.push().setValue(item);
    }

    public void getMatchesItems(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
        DatabaseReference matchesItemsRef = mDatabase.child("matches");
        ValueEventListener matchesItemsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChangedCallback.accept(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dataErrorCallback.accept(databaseError);

            }
        };
        matchesItemsRef.addValueEventListener(matchesItemsListener);
        listeners.put(matchesItemsRef, matchesItemsListener);
    }

    public void updateMatchesItemById(Matches item) {
        DatabaseReference matchesItemsRef = mDatabase.child("matches");
        matchesItemsRef.child(item.uid).setValue(item);
    }

    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(Query::removeEventListener);
    }

}
