package alc.appnaranja.vista;




import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferencias  extends PreferenceActivity {


	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferencias);


	}

	@Override
	public void onBackPressed(){
		super.onBackPressed();
	}

	

}
