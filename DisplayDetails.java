package slidenerd.vivz;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayDetails extends Activity {
    ImageView image;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        int imageResource=getIntent().getIntExtra("selectedFlagId",R.drawable.india_flag);
        String flag=getIntent().getStringExtra("selectedFlagName");
        image=(ImageView)findViewById(R.id.imageView);
        text= (TextView) findViewById(R.id.textView);
        if(flag!=null)
        {
            text.setText(flag);
            image.setImageResource(imageResource);
        }
        else
        {
            Log.e("VIVZ","selected flag was null");

        }

    }
    public void wrapUp(View v)
    {
        finish();
    }


    
}
