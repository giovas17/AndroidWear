package wear.darkgeat.weartutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by darkgeat on 20/03/15.
 */
public class ViewEventActivity extends ActionBarActivity {

    Intent response;
    TextView respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_event_activity);
        respuesta = (TextView)findViewById(R.id.textResponse);
        response = getIntent();
        respuesta.setText(respuesta.getText() + "\n" + getTextMessage(response));

    }

    public CharSequence getTextMessage(Intent intent) {
        Bundle remote = RemoteInput.getResultsFromIntent(intent);
        if(remote != null)
            return remote.getCharSequence(MainActivity.EXTRA_VOICE_REPLY);
        return null;
    }
}
