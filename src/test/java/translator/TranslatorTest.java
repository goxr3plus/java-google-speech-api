package translator;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.darkprograms.speech.translator.GoogleTranslate;

public class TranslatorTest {
	
	@Test
	public void testString() throws IOException {
		
		String translatedText = GoogleTranslate.translate("Hola . Buenos días");
		String expecetedText = "Hi . Good Morning";
		
		System.out.println(translatedText);
		assertTrue(translatedText.equals(expecetedText));
	}
}
