package in.ac.utm.smartreminder;

/**
 * Created by milton on 3/19/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Users extends AppCompatActivity  {
    Button remind;
    Button near;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        remind = (Button) findViewById(R.id.set_remind);
        near = (Button) findViewById(R.id.near_by);

    }
    public void near_click(View view){
        Intent intent=new Intent(this,Chat.class);
        startActivity(intent);
    }
    public void remind_click(View view){
        Intent intent=new Intent(this,Chat.class);
        startActivity(intent);
    }
}

