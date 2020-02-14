THIS LIBRARY IS NOT SUPPORTED ACTIVELY ANYMORE 

---

<h3 align="center" > Java Google Speech Api ( Library )</h3>
<p align="center">
🎤
</p>
<p align="center">
<sup>
<b> This project is designed to be simple and efficient, using the speech engines created by Google to provide functionality for parts of the API. Essentially, it is an API written in Java, including a recognizer, synthesizer, and a microphone capture utility. The project uses Google services for the synthesizer and recognizer.  While this requires an Internet connection, it provides a complete, modern, and fully functional speech API in Java.  </b> 
</sup>
</p>

---

[![Latest Version](https://img.shields.io/github/release/goxr3plus/java-google-speech-api.svg?style=flat-square)](https://github.com/goxr3plus/java-google-speech-api/releases)
[![GitHub contributors][contributors-image]][contributors-url]
[![HitCount](http://hits.dwyl.io/goxr3plus/java-google-speech-api.svg)](http://hits.dwyl.io/goxr3plus/java-google-speech-api)
[![Total Downloads](https://img.shields.io/github/downloads/goxr3plus/java-google-speech-api/total.svg)](https://github.com/goxr3plus/java-google-speech-api/releases)


[contributors-url]: https://github.com/goxr3plus/java-google-speech-api/graphs/contributors
[contributors-image]: https://img.shields.io/github/contributors/goxr3plus/java-google-speech-api.svg


## Google has released it's official library for [Google Speech Recognition](https://github.com/googleapis/java-speech) . Check this issue for Official Google Speech Library code solution -> [#4](https://github.com/goxr3plus/java-google-speech-api/issues/4)


### Add it to your project using JitPack :

https://jitpack.io/private#goxr3plus/java-google-speech-api

>Step 1. Add the JitPack repository to your build file
``` XML
<repositories>
	<repository>
	   <id>jitpack.io</id>
	   <url>https://jitpack.io</url>
        </repository>
</repositories>
```

>Step 2. Add the dependency
``` XML
<dependency>
   <groupId>com.github.goxr3plus</groupId>
   <artifactId>java-google-speech-api</artifactId>
   <version>8.0.0</version> 
</dependency>
```


# Java Google Speech API

##### Warning : The default secret key i was using is not working anymore (because ... i have to pay lol ) , you have to make your own , check tutorials :)

### Description
This project is designed to be simple and efficient, using the speech engines created by Google to provide functionality for parts of the API. Essentially, it is an API written in Java, including a recognizer, synthesizer, and a microphone capture utility. The project uses Google services for the synthesizer and recognizer.  While this requires an Internet connection, it provides a complete, modern, and fully functional speech API in Java.


### Features
This project is separated on 3 parts :

**1)** Google Speech Recognition based on Chromium Speech API (which is free with restrictions for commercial applications) through [GSpeechDuplex.java]( java-google-speech-api/src/main/java/com/darkprograms/speech/recognizer/GSpeechDuplex.java)

     - Microphone Capture API is used (Wrapped around the current Java API for simplicity)
     - Converts WAVE files from microphone input to FLAC (using existing API, see CREDITS)
     - Retrieves Response from Google, including confidence score and text
     
   ##### Keep in mind that:
   >It doesn't currently support the new official [Google Cloud Speech API](https://cloud.google.com/speech/)(which is also free but for a certain amount of words) 
   
   ### Update 2/7/2018
   **Check this issue for Official Google Speech Library code solution -> [#4](https://github.com/goxr3plus/java-google-speech-api/issues/4)**

   
   >The new Google Cloud Speech API is not supported yet but you can see [here](https://cloud.google.com/speech/docs/reference/libraries#client-libraries-usage-java) the official Alpha Library from supported by Google

| Create Google Cloud Account | Generate Speech Recognition Private API Keys |
|:-:|:-:|
| [![First](http://img.youtube.com/vi/1sdLrUfMBdI/0.jpg)](https://www.youtube.com/watch?v=1sdLrUfMBdI)  | [![Second](http://img.youtube.com/vi/ZUHqMn6NacY/0.jpg)](https://www.youtube.com/watch?v=ZUHqMn6NacY) |


**2)** Google translate full support through [GoogleTranslate.java](https://github.com/goxr3plus/java-google-speech-api/blob/master/src/main/java/com/darkprograms/speech/translator/GoogleTranslate.java)

    - A translator using Google Translate (courtesy of Skylion's Google Toolkit)

| Tutorial 1 | Tutorial 2 |
|:-:|:-:|
| [![First](http://img.youtube.com/vi/H9G02EkohtU/0.jpg)](https://www.youtube.com/watch?v=H9G02EkohtU)  | [![Second](http://img.youtube.com/vi/-AMoR_WPV_M/0.jpg)](https://www.youtube.com/watch?v=-AMoR_WPV_M) |

**3)** Text to Speech , Audio Synthesizer through [SynthesiserV2.java](https://github.com/goxr3plus/java-google-speech-api/blob/master/src/main/java/com/darkprograms/speech/synthesiser/SynthesiserV2.java)

    - Retrieves synthesized text in an InputStream (MP3 data ready to be played)

| Tutorial 1 | Tutorial 2 |
|:-:|:-:|
| [![First](http://img.youtube.com/vi/42-ZqfPYmVw/0.jpg)](https://www.youtube.com/watch?v=42-ZqfPYmVw)  | [![Second](http://img.youtube.com/vi/ckjURDx1JGw/0.jpg)](https://www.youtube.com/watch?v=ckjURDx1JGw) |


>The program supports dozens of languages and even has the ability to auto-detect languages! 

# Maven Build

> Maven Clean Package [ With Javadocs produced ]

```mvn clean package``` 

> Maven Clean Package [ No Javadocs produced ]

```mvn -Dmaven.javadoc.skip=true clean package``` 



## Java Swing speech recognition example using [GSpeechDuplex.java](https://github.com/goxr3plus/java-google-speech-api/blob/master/src/main/java/com/darkprograms/speech/recognizer/GSpeechDuplex.java)

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


