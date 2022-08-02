package com.example.readtext;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    // our default file name
    private static final String FILE_NAME = "example.txt";

    // to input data to be written inside text file
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditText referred here
        mEditText = findViewById(R.id.edit_text);
    }

    public void save(View v) {
        // To take input from our EditText
        String text = mEditText.getText().toString();
        //To save to storage
        FileOutputStream fileOutputStream = null;

        try {
            //Assign file name nad its privacy mode
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            //Write text into file
            fileOutputStream.write(text.getBytes());

            mEditText.getText().clear();
            //Toast when done
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    //close output stream
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(View view) {

        FileInputStream fileInputStream=null;

        try{

            fileInputStream=openFileInput(FILE_NAME);

            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader =new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder=new StringBuilder();

            String text;

            while((text=bufferedReader.readLine())!=null){

                stringBuilder.append(text).append("\n");

            }

            mEditText.setText(stringBuilder.toString());

        }

        catch(FileNotFoundException e)

        {

            e.printStackTrace();

        }

        catch(IOException e)

        {

            e.printStackTrace();

        }

        finally{

            if(fileInputStream!=null){

                try{

                    fileInputStream.close();

                }

                catch(IOException e){

                    e.printStackTrace();

                }

            }

        }

    }

}
