package com.example.apple.firebaselogin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

 private FirebaseAuth mAuth;
  EditText email,password;
  Button button;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        Toast.makeText(MainActivity.this,"Already In",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);


        mAuth=FirebaseAuth.getInstance();


    }

    public  void  onRegister(View view){

        final String myEmail= email.getText().toString();
        final String myPassword= password.getText().toString();

        if(myEmail.isEmpty()){
            Toast.makeText(MainActivity.this,"Please Fill Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(myPassword.isEmpty()){
            Toast.makeText(MainActivity.this,"Please Fill Password",Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(myEmail,myPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Log.i("TAG","CreateUserWithEmail:Success");
                            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }

   public  void onLogin(View view){
       final String myEmail= email.getText().toString();
       final String myPassword= password.getText().toString();

       if(myEmail.isEmpty()){
           Toast.makeText(MainActivity.this,"Please Fill Email",Toast.LENGTH_SHORT).show();
           return;
       }
       if(myPassword.isEmpty()){
           Toast.makeText(MainActivity.this,"Please Fill Password",Toast.LENGTH_SHORT).show();
           return;
       }
       mAuth.signInWithEmailAndPassword(myEmail, myPassword)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           // Sign in success, update UI with the signed-in user's information
                           Log.i("TAG", "signInWithEmail:success");
                           FirebaseUser user = mAuth.getCurrentUser();
                           String userID= user.getUid().toString();
                           Toast.makeText(MainActivity.this, "Authentication Success.",
                                   Toast.LENGTH_SHORT).show();
                           Log.i("USER","USER:"+user.toString());
                           Log.i("USER","USER:"+userID);

                           //updateUI(user);
                       } else {
                           // If sign in fails, display a message to the user.
                           Log.i("TAG", "signInWithEmail:failure", task.getException());
                           Toast.makeText(MainActivity.this, "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();
                          // updateUI(null);
                       }

                       // ...
                   }
               });


   }


public  void logout(View view){

        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainActivity.this,"Sign Out",Toast.LENGTH_SHORT).show();
}



}
