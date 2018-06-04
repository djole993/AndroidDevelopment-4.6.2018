package rs.aleph.android.example1.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import rs.aleph.android.example1.R;
import rs.aleph.android.example1.providers.JeloProvider;

import rs.aleph.android.example1.fragments.DetailFragment;
import rs.aleph.android.example1.fragments.ListFragment;
// Each activity extends Activity class
public class FirstActivity extends Activity implements ListFragment.OnItemSelectedListener {
    //04.06.2018
	// Screen orientation
	private boolean landscape = false;

	// Position of the item to be displayed in the detail fragment
	private int position = 0;

	private ListFragment listFragment = null;

	private DetailFragment detailFragment= null;


	static final int PICK_CONTACT_REQUEST = 0;  // The request code

	// The activity's state
	private int a;
	private float b;
	private String c;
	private int[] d = {0, 1, 2, 3, 4};

	// onCreate method is a lifecycle method called when he activity is starting
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{

		// Each lifecycle method should call the method it overrides
		super.onCreate(savedInstanceState);

		// Shows a toast message (a pop-up message)
		Toast.makeText(getBaseContext(), "FirstActivity.onCreate()", Toast.LENGTH_SHORT).show();

		// Draws layout
		setContentView(R.layout.activity_first);

		// Load instance state from bundle
		if (savedInstanceState != null) {
			this.a = savedInstanceState.getInt("a");
			this.b = savedInstanceState.getFloat("b");
			this.c = savedInstanceState.getString("c");
			this.d = savedInstanceState.getIntArray("d");
			this.position = savedInstanceState.getInt("position");
		}

        // Create and show master fragment
        listFragment= new ListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.list_view, listFragment, "List_Fragment_1");
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();

		// Create detail fragment and show it if the device is in the landscape mode
        detailFragment = new DetailFragment();
        detailFragment.setContent(position);
        if (findViewById(R.id.detail_view) != null) {
			landscape = true;
			getFragmentManager().popBackStack();
            ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.detail_view, detailFragment, "Detail_Fragment_1");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}
	}

	// onStart method is a lifecycle method called after onCreate (or after onRestart when the
	// activity had been stopped, but is now again being displayed to the user)
	@Override
	protected void onStart() {

		super.onStart();

		// Shows a toast message (a pop-up message)
		Toast.makeText(getBaseContext(), "FirstActivity.onStart()", Toast.LENGTH_SHORT).show();
	}

	// onRestart method is a lifecycle method called after onStop when the current activity is
	// being re-displayed to the user
	@Override
    protected void onRestart() {

		super.onRestart();

		// Shows a toast message (a pop-up message)
		Toast.makeText(getBaseContext(), "FirstActivity.onRestart()", Toast.LENGTH_SHORT).show();
    }

	// onResume method is a lifecycle method called after onRestoreInstanceState, onRestart, or
	// onPause, for your activity to start interacting with the user
	@Override
	protected void onResume() {

		super.onResume();

		// Shows a toast message (a pop-up message)
		Toast.makeText(getBaseContext(), "FirstActivity.onResume()", Toast.LENGTH_SHORT).show();
	}

	// onPause method is a lifecycle method called when an activity is going into the background,
	// but has not (yet) been killed
	@Override
	protected void onPause() {

		super.onPause();

		// Shows a toast message (a pop-up message)
		Toast.makeText(getBaseContext(), "FirstActivity.onPause()", Toast.LENGTH_SHORT).show();
	}

	// onStop method is a lifecycle method called when the activity are no longer visible to the user
	@Override
	protected void onStop() {

		super.onStop();

		// Shows a toast message (a pop-up message)
		Toast.makeText(getBaseContext(), "FirstActivity.onStop()", Toast.LENGTH_SHORT).show();
	}

	// onDestroy method is a lifecycle method that perform any final cleanup before an activity is destroyed
	@Override
	protected void onDestroy() {

		super.onDestroy();

		// Shows a toast message (a pop-up message)
		Toast.makeText(getBaseContext(), "FirstActivity.onDestroy()", Toast.LENGTH_SHORT).show();
	}

	public void btnSelectContactClicked(View view) {
		// This is an implicit intent
		Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
		// Show user only contacts w/ phone numbers
		intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
		// startActivity method starts an activity
		startActivityForResult(intent, PICK_CONTACT_REQUEST);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request it is that we're responding to
		if (requestCode == PICK_CONTACT_REQUEST) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
				// Get the URI that points to the selected contact
				Uri contactUri = data.getData();

				// Shows contact URI
				Toast.makeText(this, "Contact URI: " + contactUri, Toast.LENGTH_SHORT).show();
			}
		}
	}
    // onSaveInstanceState method is a life-cycle method that is called to ask the fragment to save its current dynamic state, so it can later be reconstructed in a new instance of its process is restarted.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        // Shows a toast message (a pop-up message)
        Toast.makeText(this, "FirstActivity.onSaveInstanceState()", Toast.LENGTH_SHORT).show();

        savedInstanceState.putInt("a", a);
		savedInstanceState.putFloat("b", b);
		savedInstanceState.putString("c", c);
		savedInstanceState.putIntArray("d", d);
        savedInstanceState.putInt("position", position);
    }

	@Override
	public void onItemSelected(int position) {

	    // Set position
		this.position = position;

		// Shows a toast message (a pop-up message)
		Toast.makeText(getBaseContext(), "FirstActivity.onItemSelected()", Toast.LENGTH_SHORT).show();

		if (landscape) {
			// If the device is in the landscape mode updates detail fragment's content.
			detailFragment.updateContent(position);
		} else {
			// If the device is in the portrait mode sets detail fragment's content and replaces master fragment with detail fragment in a fragment transaction.
			detailFragment.setContent(position);
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.list_view, detailFragment, "Detail_Fragment_1");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			ft.commit();
		}
	}
}