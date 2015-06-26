package wear.darkgeat.weartutorial;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import wear.darkgeat.utilities.Item;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    private ListView lista_ejemplos;
    private int eventId = 1598;
    private String EXTRA_EVENT_ID = "Id de Evento";
    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";
    private String replyLabel = "NOse";
    private Item[] examples = new Item[]{new Item(R.mipmap.ic_launcher,"Notificacion Simple"),
            new Item(R.mipmap.ic_notification,"Notification with actions"),
            new Item(R.mipmap.ic_maps,"Notification with Location"),
            new Item(R.mipmap.icfenixlauncher,"Big Notification"),
            new Item(R.mipmap.icfenixlauncher,"Voice Notification")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista_ejemplos = (ListView)findViewById(R.id.lista);
        View header = getLayoutInflater().inflate(R.layout.header,null);
        lista_ejemplos.addHeaderView(header);
        AdapterLista adapter = new AdapterLista(this,examples);
        lista_ejemplos.setAdapter(adapter);
        lista_ejemplos.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == 1){
            Notification notificacion = new NotificationCompat.Builder(getApplication())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(((BitmapDrawable)getResources().getDrawable(R.drawable.background)).getBitmap())
                    .setContentTitle("Hello Cruel World")
                    .setContentText("Este es mi mundo Cruel ajajaj!!!")
                    .extend(new NotificationCompat.WearableExtender())
                    .build();
            NotificationManagerCompat nm = NotificationManagerCompat.from(getApplication());
            int notificationId = 1;
            nm.notify(notificationId,notificacion);
        }else if(position == 2){
            CrearNotificacion(position);
        }else if(position == 3){
            CrearNotificationLocation(position);
        }else if(position == 4){
            CrearBigNotification(position);
        }else if(position == 5){
            VoiceNotification(position);
        }
    }

    public void CrearNotificacion(int position){
        Intent viewIntent = new Intent(this,ViewEventActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID,eventId);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this,0,viewIntent,0);
        Notification notificacion = new NotificationCompat.Builder(getApplication())
                .setSmallIcon(examples[position-1].getImagen())
                .setLargeIcon(((BitmapDrawable)getResources().getDrawable(R.mipmap.ic_notification)).getBitmap())
                .setContentTitle(examples[position - 1].getMensaje())
                .setContentText("Este es mi mundo Cruel ajajaj!!!")
                .setContentIntent(viewPendingIntent)
                .extend(new NotificationCompat.WearableExtender())
                .build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(getApplication());
        int notificationId = 87;
        nm.notify(notificationId,notificacion);
    }

    public void CrearNotificationLocation(int position){
        Intent viewIntent = new Intent(this,ViewEventActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID,eventId);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this,0,viewIntent,0);

        Intent mapintent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("20.656880,-103.397461(iTexico Matrix)"));
        mapintent.setData(geoUri);
        PendingIntent pi = PendingIntent.getActivity(this,0,mapintent,0);
        Notification notificacion = new NotificationCompat.Builder(getApplication())
                .setSmallIcon(examples[position-1].getImagen())
                .setLargeIcon(((BitmapDrawable)getResources().getDrawable(R.mipmap.ic_maps)).getBitmap())
                .setContentTitle(examples[position-1].getMensaje())
                .setContentText("Location found!!!")
                .setContentIntent(viewPendingIntent)
                .extend(new NotificationCompat.WearableExtender())
                .addAction(examples[position-1].getImagen(),"iTexico Location",pi)
                .build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(getApplication());
        int notificationId = 87;
        nm.notify(notificationId,notificacion);
    }

    public void CrearBigNotification(int position){
        Intent viewIntent = new Intent(this,ViewEventActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID,eventId);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this,0,viewIntent,0);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText("Esto es una prueba de que tan grande podemos hacer el texto en una notificacion para un wear y verla de manera correcta");
        Notification notificacion = new NotificationCompat.Builder(getApplication())
                .setSmallIcon(examples[position - 1].getImagen())
                .setLargeIcon(((BitmapDrawable) getResources().getDrawable(R.mipmap.icfenixlauncher)).getBitmap())
                .setContentTitle(examples[position - 1].getMensaje())
                .setContentText("Location found!!!")
                .setContentIntent(viewPendingIntent)
                .extend(new NotificationCompat.WearableExtender())
                .setStyle(bigTextStyle)
                .build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(getApplication());
        int notificationId = 87;
        nm.notify(notificationId,notificacion);
    }

    public void VoiceNotification(int position){
        Intent viewIntent = new Intent(this,ViewEventActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID,eventId);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this,0,viewIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText("Esto es una prueba de que tan grande podemos hacer el texto en una notificacion para un wear y verla de manera correcta");

        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY).setLabel(replyLabel).build();

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.fenix,"Response",viewPendingIntent).addRemoteInput(remoteInput).build();
        Notification notificacion = new NotificationCompat.Builder(getApplication())
                .setSmallIcon(examples[position - 2].getImagen())
                .setLargeIcon(((BitmapDrawable) getResources().getDrawable(R.mipmap.icfenixlauncher)).getBitmap())
                .setContentTitle(examples[position - 2].getMensaje())
                .setContentIntent(viewPendingIntent)
                .extend(new NotificationCompat.WearableExtender().addAction(action))
                .setStyle(bigTextStyle)
                .build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(getApplication());
        int notificationId = 87;
        nm.notify(notificationId,notificacion);
    }
}
