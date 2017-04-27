package in.ac.utm.smartreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void about_me(View view){
        //Intent intent=new Intent(this,.class);
        //startActivity(intent);
    }
    public void up_click(View view){
        Intent intent=new Intent(this,register.class);
        startActivity(intent);
    }
    public void sign_click(View view){
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }

}
