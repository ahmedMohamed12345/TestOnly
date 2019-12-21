package com.elgindy.testonly;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    List<String> arrayList = new ArrayList<>();
    DatabaseReference eventIdRef;

    private MutableLiveData<List<String>> departmentNameData = new MutableLiveData<>();


    public MutableLiveData<List<String>> getDepartmentName() {
        getArrayOfDepartmentName();
        departmentNameData.setValue(arrayList);
        return departmentNameData;
    }


    public List<String> getArrayOfDepartmentName() {
        eventIdRef = FirebaseDatabase.getInstance().getReference().child("departments");
        eventIdRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String plate = snapshot.child("name").getValue().toString();

                    arrayList.add(plate);
                    Log.d("test", plate);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return arrayList;
    }


}
