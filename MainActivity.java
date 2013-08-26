package slidenerd.vivz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    GridView grid;
    Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid= (GridView) findViewById(R.id.gridView);

        grid.setAdapter(new VivzAdapter(this));
        grid.setOnItemClickListener(this);
        intent=new Intent(this, DisplayDetails.class);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        RelativeLayout layout=(RelativeLayout)view;
        ImageView image= (ImageView) layout.findViewById(R.id.imageView);
        SingleRow row= (SingleRow) image.getTag();



        int imageId=row.image;
        String imageName=row.flagName;
        intent.putExtra("selectedFlagId",imageId);
        intent.putExtra("selectedFlagName",imageName);

        startActivity(intent);
    }
/*
1 create a class that extends BaseAdapter and implement all the methods
2 Maintain some array inside your BaseAdapter class that will contain all the data
[titles+descriptions+images]
3 use the getView method to fill the data from your array inside the custom structure
 of that single row for each row
 */
}
class SingleRow {
    String flagName;
    int image;
    SingleRow(String flagName,int image)
    {
        this.flagName=flagName;
        this.image=image;
    }
}
class VivzAdapter extends BaseAdapter
{
    Context context;
    ArrayList<SingleRow> list;
    VivzAdapter(Context c)
    {
        this.context=c;
        list=new ArrayList<SingleRow>();
        Resources res=c.getResources();
        String[] flagTitles=res.getStringArray(R.array.flags);
        int images[]={R.drawable.india_flag,R.drawable.united_states_flag,R.drawable.phillipines_flag,R.drawable.pakistan_flag,R.drawable.saudi_arabia_flag,R.drawable.turkey_flag,R.drawable.spain_flag,R.drawable.norway_flag,R.drawable.italy_flag,R.drawable.germany_flag};
        for(int i=0;i<images.length;i++)
        {
            list.add(new SingleRow(flagTitles[i],images[i]));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder
    {
        ImageView myImageView;
        ViewHolder(View view)
        {
            myImageView= (ImageView) view.findViewById(R.id.imageView);
        }
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder myHolder=null;
        View row=view;

        if(row==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.single_row,viewGroup,false);
            myHolder=new ViewHolder(row);
            row.setTag(myHolder);
        }
        else
        {
            myHolder= (ViewHolder) row.getTag();
        }
        SingleRow temp=list.get(i);
        myHolder.myImageView.setImageResource(temp.image);
        myHolder.myImageView.setTag(temp);
        return row;
    }
}
