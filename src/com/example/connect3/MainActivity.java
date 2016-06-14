package com.example.connect3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect3.R;

public class MainActivity extends ActionBarActivity {
	boolean gameIsActive = true;
	int activePlayer = 0;
	int[] gameState = {2,2,2,2,2,2,2,2,2};
	int[][] winningPositions = {{0,1,2},{3,4,5,},{6,7,8}, {0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
	public void dropIn(View view)
	{
		ImageView counter = (ImageView) view;
		int tappedCounter = Integer.parseInt(counter.getTag().toString());
		if (gameState[tappedCounter]==2 && gameIsActive)
		{
			gameState[tappedCounter]=activePlayer;
		
		counter.setTranslationY(-1000f);
		if(activePlayer == 0) 
		{
			activePlayer = 1;
			counter.setImageResource(R.drawable.blue);
			
		}
		else
		{
			activePlayer = 0;
			counter.setImageResource(R.drawable.red);
		}
		counter.animate().translationYBy(1000f).rotation(360f).setDuration(500);

		for (int[] winningPosition : winningPositions)
		{
			if((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] == gameState[winningPosition[2]]) && (gameState[winningPosition[0]]!=2))
			{
				gameIsActive = false;
				String winner = "Red";
				if(gameState[winningPosition[0]]==0)
				{
					winner="Blue";
				}
				
				LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		        linearLayout.setVisibility(View.VISIBLE); 
		        TextView winnerField = (TextView) findViewById(R.id.textView1);
		        winnerField.setText(winner + " is the winner");
		        
			}
			else 
			{
             boolean gameIsOver = true;
             for(int i =0; i<gameState.length;i++)
             {
            	 if (gameState[i] == 2)
            		 gameIsOver = false;
             }
				if (gameIsOver)
				{
				LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		        linearLayout.setVisibility(View.VISIBLE); 
		        TextView winnerField = (TextView) findViewById(R.id.textView1);
		        winnerField.setText("It's a draw!");
				}
			}
		}
		}
		}
	
	public void playAgain(View view){
		gameIsActive = true;
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.INVISIBLE); 
		activePlayer = 0;
		for(int i =0;i<gameState.length;i++){
			gameState[i] = 2;
			
		}
		GridLayout  gridLayout = (GridLayout) findViewById(R.id.gridLayout);
		
		for (int i =0; i<gridLayout.getChildCount();i++){
			ImageView child = (ImageView) gridLayout.getChildAt(i);
			child.setImageResource(0);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(getApplicationContext(), "Developer : Mohammed Suhail", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
