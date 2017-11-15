#### This library will soon be upgrated to support only Java 8 and above , so Java 7 and below will not be compatible any more

##### Only releases until [2.1](https://github.com/goxr3plus/java-google-speech-api/releases/tag/V2.1) Supports Java 7 and below :) 

###### For Java 1.5 ++ compatible version look here -> https://github.com/goxr3plus/java-speech-api

###### This project is a completely upgraded fork of Java-Speech-API -> https://github.com/goxr3plus/java-speech-api
---

### J.A.R.V.I.S. For Java 8 (Java-GOOGLE-Speech-API)

J.A.R.V.I.S. Java Speech API: Just A Reliable Vocal Interpreter & Synthesizer. 
This is a project for the Java Speech API. The program interprets vocal inputs into text and synthesizes voices from text input.
The program supports dozens of languages and even has the ability to auto-detect languages! 

---



#### Create your own Google_API_KEY , watch the tutorial below 

| Create Google Cloud Account | Generate Speech Recognition API Keys |
|:-:|:-:|
| [![First](http://img.youtube.com/vi/1sdLrUfMBdI/0.jpg)](https://www.youtube.com/watch?v=1sdLrUfMBdI)  | [![Second](http://img.youtube.com/vi/8MbomlmxKXU/0.jpg)](https://www.youtube.com/watch?v=8MbomlmxKXU) |


#### Java Swing Example Using the Library for Speech Recognition

```Java

package Try_Google_Speech_Recognition_Simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class TryGoogleSpeechRecognitionSimple implements GSpeechResponseListener {
	
	public static void main(String[] args) throws IOException {
		final Microphone mic = new Microphone(FLACFileWriter.FLAC);
		// You have to make your own GOOGLE_API_KEY 
		GSpeechDuplex duplex = new GSpeechDuplex("GOOGLE_API_KEY");
		
		duplex.setLanguage("en");
		
		JFrame frame = new JFrame("Jarvis Speech API DEMO");
		frame.setDefaultCloseOperation(3);
		JTextArea response = new JTextArea();
		response.setEditable(false);
		response.setWrapStyleWord(true);
		response.setLineWrap(true);
		
		final JButton record = new JButton("Record");
		final JButton stop = new JButton("Stop");
		stop.setEnabled(false);
		
		record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Thread(() -> {
					try {
						duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}).start();
				record.setEnabled(false);
				stop.setEnabled(true);
			}
		});
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				record.setEnabled(true);
				stop.setEnabled(false);
			}
		});
		JLabel infoText = new JLabel(
				"<html><div style=\"text-align: center;\">Just hit record and watch your voice be translated into text.\n<br>Only English is supported by this demo, but the full API supports dozens of languages.<center></html>",
				
				0);
		frame.getContentPane().add(infoText);
		infoText.setAlignmentX(0.5F);
		JScrollPane scroll = new JScrollPane(response);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), 1));
		frame.getContentPane().add(scroll);
		JPanel recordBar = new JPanel();
		frame.getContentPane().add(recordBar);
		recordBar.setLayout(new BoxLayout(recordBar, 0));
		recordBar.add(record);
		recordBar.add(stop);
		frame.setVisible(true);
		frame.pack();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		
		duplex.addResponseListener(new GSpeechResponseListener() {
			String old_text = "";
			
			public void onResponse(GoogleResponse gr) {
				String output = "";
				output = gr.getResponse();
				if (gr.getResponse() == null) {
					this.old_text = response.getText();
					if (this.old_text.contains("(")) {
						this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
					}
					System.out.println("Paragraph Line Added");
					this.old_text = ( response.getText() + "\n" );
					this.old_text = this.old_text.replace(")", "").replace("( ", "");
					response.setText(this.old_text);
					return;
				}
				if (output.contains("(")) {
					output = output.substring(0, output.indexOf('('));
				}
				if (!gr.getOtherPossibleResponses().isEmpty()) {
					output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
				}
				System.out.println(output);
				response.setText("");
				response.append(this.old_text);
				response.append(output);
			}
		});
	}
	
	@Override
	public void onResponse(GoogleResponse paramGoogleResponse) {
		// TODO Auto-generated method stub
		
	}
}


```

### Description
The J.A.R.V.I.S. Speech API is designed to be simple and efficient, using the speech engines created by Google to provide functionality for parts of the API. Essentially, it is an API written in Java, including a recognizer, synthesizer, and a microphone capture utility. The project uses Google services for the synthesizer and recognizer.  While this requires an Internet connection, it provides a complete, modern, and fully functional speech API in Java.

### Features
- **The API currently provides the following functionality,**
  - Microphone Capture API (Wrapped around the current Java API for simplicity)
  - **A speech recognizer using Google's recognizer service**
      - Converts WAVE files from microphone input to FLAC (using existing API, see CREDITS)
      - Retrieves Response from Google, including confidence score and text
  - **A speech synthesiser using Google's synthesizer service**
      - Retrieves synthesized text in an InputStream (MP3 data ready to be played)
  - Wave to FLAC API (Wrapped around the used API in the project, javaFlacEncoder, see CREDITS)
  - A translator using Google Translate (courtesy of Skylion's Google Toolkit)

## --Maven Build--

### Maven Clean Package [ With Javadocs produced ]

```mvn clean package``` 

### Maven Clean Package [ No Javadocs produced ]

```mvn -Dmaven.javadoc.skip=true clean package``` 

### Add it to your project using JitPack :

https://jitpack.io/private#goxr3plus/java-google-speech-api

### Step 1. Add the JitPack repository to your build file
``` XML
<repositories>
	<repository>
	   <id>jitpack.io</id>
	   <url>https://jitpack.io</url>
        </repository>
</repositories>
```

###  Step 2. Add the dependency
``` XML
<dependency>
   <groupId>com.github.goxr3plus</groupId>
   <artifactId>java-google-speech-api</artifactId>
   <version>V2.1</version> 
</dependency>
```


----
