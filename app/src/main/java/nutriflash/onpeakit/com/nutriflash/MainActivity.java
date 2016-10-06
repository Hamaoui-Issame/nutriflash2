package nutriflash.onpeakit.com.nutriflash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * http://tutorielsandroid.com/lire-un-qr-code-ou-un-code-barre-dans-son-application-android/
 * https://github.com/journeyapps/zxing-android-embedded
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mybutton = (Button) findViewById(R.id.scan_button);
        mybutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scan_button) {
            // on lance le scanner au clic sur notre bouton
            new IntentIntegrator(this).initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // nous utilisons la classe IntentIntegrator et sa fonction parseActivityResult pour parser le résultat du scan
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

            // nous récupérons le contenu du code barre
            String scanContent = scanningResult.getContents();

            // nous récupérons le format du code barre
            String scanFormat = scanningResult.getFormatName();

            TextView scan_format = (TextView) findViewById(R.id.scan_format);
            TextView scan_content = (TextView) findViewById(R.id.scan_content);

            // nous affichons le résultat dans nos TextView
            scan_format.setText("FORMAT: " + scanFormat);
            scan_content.setText("CONTENT: " + scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Aucune donnée reçu!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
