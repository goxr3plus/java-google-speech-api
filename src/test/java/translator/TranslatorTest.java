package translator;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.goxr3plus.speech.translator.GoogleTranslate;

public class TranslatorTest {
	
	@Test
	public void testString() throws IOException {
		
		String translatedText = GoogleTranslate.translate("Hola . Buenos días");
		String expecetedText = "Hello . Good morning";
		
		System.out.println(translatedText);
		assertTrue(translatedText.equals(expecetedText));
	}
}
